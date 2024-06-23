package com.hhpluslecture.lecture.service;

import com.hhpluslecture.error.GlobalCustomException;
import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import com.hhpluslecture.lecture.aggregate.event.LectureApplied;
import com.hhpluslecture.lecture.repository.LectureApplicationRepository;
import com.hhpluslecture.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hhpluslecture.lecture.error.LectureErrorCode.*;

/**
 * 특강 관련 담당 -
 * 특강들 조회
 */
@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements  LectureService {
    //
    private final LectureRepository lectureRepository;
    private final LectureApplicationRepository lectureApplicationRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * 특강 신청
     * 후처리: LectureApplied 발행
     * @param lectureId 특강 아이디
     * @param userId 유저 아이디
     */
    @Override
    public void lectureApply(String lectureId, String userId) {
        Lecture lecture = lectureRepository.findById(lectureId);

        if(lecture == null)
            throw new GlobalCustomException(NOTFOUND_LECTURE);
        if(!lecture.isEnrollmentStarted())
            throw new GlobalCustomException(NOT_START_LECTURE);
        if(lecture.isAtCapacity())
            throw new GlobalCustomException(EXCEEDED_LECTURE);
        if(hasLectureApplication(lectureId, userId))
            throw new GlobalCustomException(ALREADY_APPLIED);

        lecture.addCount();
        this.lectureRepository.save(lecture);
        applicationEventPublisher.publishEvent(new LectureApplied(lecture.getLectureId(), userId));
    }

    /**
     * 신청가능한 특강 리스트 조회
     * FIXME 신청했는지 Filter 로직 추가 필요
     * @param userId 유저아이디
     * @return 특강리스트
     */
    @Override
    public List<Lecture> loadLectures(String userId) {
        //
        return this.lectureRepository.findAllByUserId(userId);
    }

    private boolean hasLectureApplication(String lectureId, String userId) {
        //
        return this.lectureApplicationRepository.hasLectureApplication(lectureId, userId);
    }
}
