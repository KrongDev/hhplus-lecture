package com.hhpluslecture.lectureApplication.repository;

import com.hhpluslecture.lectureApplication.aggregate.entity.LectureApplicationEntity;

import java.util.List;

public interface LectureApplicationRepository {
    String create(LectureApplicationEntity lectureApplicationEntity);
    LectureApplicationEntity findById(String lectureApplicationId);
    List<LectureApplicationEntity> findByUserId(String userId);
    int countByLectureId(long lectureId);
}
