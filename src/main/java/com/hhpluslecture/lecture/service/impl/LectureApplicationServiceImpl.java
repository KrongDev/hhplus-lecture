package com.hhpluslecture.lecture.service.impl;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import com.hhpluslecture.lecture.aggregate.domain.LectureApplication;
import com.hhpluslecture.lecture.repository.LectureApplicationRepository;
import com.hhpluslecture.lecture.service.LectureApplicationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureApplicationServiceImpl implements LectureApplicationService {
    //
    private final LectureApplicationRepository lectureApplicationRepository;

    @Override
    @Transactional
    public void apply(long lectureId, String userId, Lecture lecture) {
        LectureApplication lectureApplication = LectureApplication.newApplication(lectureId, userId, lecture);
        this.lectureApplicationRepository.create(lectureApplication);
    }
}
