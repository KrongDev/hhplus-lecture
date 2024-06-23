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

    @Override
    public void create(String lectureId, String userId) {
        //
        LectureApplication lectureApplication = LectureApplication.newApplication(lectureId, userId);
        this.lectureApplicationRepository.save(lectureApplication);
    }

    @Override
    public boolean isEnrolledInSpecialLecture(String lectureId, String userId) {
        //
        return this.lectureApplicationRepository.hasLectureApplication(lectureId, userId);
    }
}
