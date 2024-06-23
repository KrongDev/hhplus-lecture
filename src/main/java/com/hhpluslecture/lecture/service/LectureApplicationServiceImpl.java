package com.hhpluslecture.lecture.service;

import com.hhpluslecture.lecture.aggregate.domain.LectureApplication;
import com.hhpluslecture.lecture.repository.LectureApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureApplicationServiceImpl implements LectureApplicationService{
    //
    private final LectureApplicationRepository lectureApplicationRepository;

    /**
     * 이전 작업: 특강 신청
     * 특강 신청내역 생성
     * @param lectureId 특강 아이디
     * @param userId 유저아이디
     */
    @Override
    public void create(String lectureId, String userId) {
        //
        LectureApplication lectureApplication = LectureApplication.newApplication(lectureId, userId);
        this.lectureApplicationRepository.save(lectureApplication);
    }

    /**
     * 특강 신청되었는지
     * @param lectureId 특강 아이디
     * @param userId 유저 아이디
     * @return 특강 신청했을 경우 true or false
     */
    @Override
    public boolean isEnrolledInSpecialLecture(String lectureId, String userId) {
        //
        return this.lectureApplicationRepository.hasLectureApplication(lectureId, userId);
    }
}
