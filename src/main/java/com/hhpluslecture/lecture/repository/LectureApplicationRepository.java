package com.hhpluslecture.lecture.repository;

import com.hhpluslecture.lecture.aggregate.domain.LectureApplication;

public interface LectureApplicationRepository {
    String create(LectureApplication lectureApplication);
    LectureApplication findById(String lectureApplicationId);
}
