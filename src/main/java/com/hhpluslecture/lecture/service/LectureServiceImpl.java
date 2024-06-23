package com.hhpluslecture.lecture.service;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import com.hhpluslecture.lecture.aggregate.event.LectureApplied;
import com.hhpluslecture.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 특강 관련 담당 -
 * 특강들 조회
 */
@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements  LectureService {
    //
    private final LectureRepository lectureRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void lectureApply(String lectureId, String userId) {
        Lecture lecture = lectureRepository.findById(lectureId);
        if(lecture == null)
            throw new RuntimeException("해당 강의가 존재하지 않습니다.");
        if(!lecture.isEnrollmentStarted())
            throw new RuntimeException("강의 신청 가능 시간이 아직 되지 않았습니다");
        if(lecture.isAtCapacity())
            throw new RuntimeException("수용가능한 인원을 초과하였습니다.");
        lecture.addCount();
        this.lectureRepository.save(lecture);
        applicationEventPublisher.publishEvent(new LectureApplied(lecture.getLectureId(), userId));
    }

    @Override
    public List<Lecture> loadLectures(String userId) {
        //
        return this.lectureRepository.findAllByUserId(userId);
    }
}
