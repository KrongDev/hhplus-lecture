package com.hhpluslecture.lecture.repository;

import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;

import java.util.List;

public interface LectureRepository {
    long create(LectureEntity lectureEntity);
    void update(LectureEntity lectureEntity);
    LectureEntity findById(long id);
    List<LectureEntity> findAll();
}
