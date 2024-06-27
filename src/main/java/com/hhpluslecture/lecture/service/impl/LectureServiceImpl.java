package com.hhpluslecture.lecture.service.impl;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import com.hhpluslecture.lectureApplication.aggregate.domain.LectureApplication;
import com.hhpluslecture.lectureApplication.repository.LectureApplicationRepository;
import com.hhpluslecture.lecture.repository.LectureRepository;
import com.hhpluslecture.lecture.service.LectureService;
import com.hhpluslecture.lecture.service.error.CapacityExceededException;
import com.hhpluslecture.lecture.service.error.RegistrationNotOpenException;
import com.hhpluslecture.lecture.service.mapper.LectureMapper;
import com.hhpluslecture.lectureApplication.service.mapper.LectureApplicationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {
    //
    private final LectureRepository lectureRepository;
    private final LectureApplicationRepository lectureApplicationRepository;


    @Override
    @Transactional
    public long createLecture(String title, int capacity, LocalDateTime startAt) {
        Lecture lecture = new Lecture(title, capacity, startAt);
        return this.lectureRepository.create(LectureMapper.convertToEntity(lecture));
    }

    @Override
    @Transactional
    public void applyLecture(long lectureId, String userId) {
        Lecture lecture = LectureMapper.convertToDomain(lectureRepository.findByIdForUpdate(lectureId));
        if(lecture == null)
            throw new NoSuchElementException("해당 강의가 존재하지 않습니다.");
        if(!lecture.isEnrollmentStarted())
            throw new RegistrationNotOpenException("강의 신청 가능 시간이 아닙니다.");
        if(lecture.isCapacityExceeded())
            throw new CapacityExceededException("수용가능한 인원을 초과하였습니다.");
        LectureApplication lectureApplication = loadLectureApplication(lectureId, userId);
        if(!Objects.isNull(lectureApplication))
            throw new IllegalArgumentException("이미 신청한 강의입니다.");
        lecture.apply();
        lectureRepository.update(LectureMapper.convertToEntity(lecture));
        log.info("Applying lecture capacity {}, headCount {}", lecture.getCapacity(), lecture.getHeadCount());
        lectureApplicationCreate(lectureId, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isApplyComplete(long lectureId, String userId) {
        LectureApplication lectureApplication = loadLectureApplication(lectureId, userId);
        return !Objects.isNull(lectureApplication);
    }

    private LectureApplication loadLectureApplication(long lectureId, String userId) {
        //
        return LectureApplicationMapper.convertToDomain(lectureApplicationRepository.findById(LectureApplication.genId(lectureId, userId)));
    }

    private void lectureApplicationCreate(long lectureId, String userId) {
        LectureApplication lectureApplication = LectureApplication.newApplication(lectureId, userId);
        this.lectureApplicationRepository.create(LectureApplicationMapper.convertToEntity(lectureApplication));
    }

    @Override
    @Transactional(readOnly = true)
    public Lecture loadLecture(long lectureId) {
        //
        return LectureMapper.convertToDomain(this.lectureRepository.findById(lectureId));
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
        List<Lecture> lectures = this.lectureRepository.findAll().stream().map(LectureMapper::convertToDomain).toList();
        Map<Long, List<LectureApplication>> longLectureApplicationMap = this.lectureApplicationRepository.findByUserId(userId).stream().map(LectureApplicationMapper::convertToDomain).collect(Collectors.groupingBy(res->res.getLectureId()));
        if(Strings.isNotEmpty(userId) || Strings.isNotBlank(userId)) {
            return lectures.stream().filter(lecture->longLectureApplicationMap.getOrDefault(lecture.getId(), null ) == null).toList();
        }
        return lectures;
    }
}
