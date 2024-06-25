package com.hhpluslecture.lecture.service.impl;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import com.hhpluslecture.lecture.aggregate.domain.LectureApplication;
import com.hhpluslecture.lecture.repository.LectureApplicationRepository;
import com.hhpluslecture.lecture.service.LectureApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

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

    @Override
    @Transactional(readOnly = true)
    public boolean isApplyComplete(long lectureId, String userId) {
        String lectureApplicationId = LectureApplication.genId(lectureId, userId);
        try {
            this.lectureApplicationRepository.findById(lectureApplicationId);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
