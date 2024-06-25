package com.hhpluslecture.lecture.service.impl;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import com.hhpluslecture.lecture.aggregate.event.LectureApplied;
import com.hhpluslecture.lecture.repository.LectureRepository;
import com.hhpluslecture.lecture.service.LectureService;
import com.hhpluslecture.lecture.service.error.CapacityExceededException;
import com.hhpluslecture.lecture.service.error.RegistrationNotOpenException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {
    //
    private final LectureRepository lectureRepository;
    private final ApplicationEventPublisher eventPublisher;


    @Override
    @Transactional
    public long createLecture(String title, int capacity, LocalDateTime startAt) {
        Lecture lecture = new Lecture(title, capacity, startAt);
        return this.lectureRepository.create(lecture);
    }

    @Override
    @Transactional
    public void applyLecture(long lectureId, String userId) {
        Lecture lecture = lectureRepository.findByIdForUpdate(lectureId);
        if(lecture == null)
            throw new NoSuchElementException("해당 강의가 존재하지 않습니다.");
        if(!lecture.isEnrollmentStarted())
            throw new RegistrationNotOpenException("강의 신청 가능 시간이 아닙니다.");
        if(lecture.isAlreadyApplied(userId))
            throw new IllegalArgumentException("이미 신청한 강의입니다.");
        if(lecture.isCapacityExceeded())
            throw new CapacityExceededException("수용가능한 인원을 초과하였습니다.");
        eventPublisher.publishEvent(new LectureApplied(lecture.getId(), userId, lecture));
    }

    @Override
    @Transactional(readOnly = true)
    public Lecture loadLecture(long lectureId) {
        //
        return this.lectureRepository.findById(lectureId);
    }

    /**
     * 이미 신청한 특강은 목록에 보여주지 않습니다.
     * @param userId 유저아이디
     * @return 특강 목록
     */
    @Override
    @Transactional(readOnly = true)
    public List<Lecture> loadLectures(String userId) {
        //
        List<Lecture> lectures = this.lectureRepository.findAll();
        if(Strings.isNotEmpty(userId) || Strings.isNotBlank(userId)) {
            return lectures.stream().filter(lecture->!lecture.isAlreadyApplied(userId)).toList();
        }
        return lectures;
    }
}
