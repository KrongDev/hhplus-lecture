package com.hhpluslecture.lecture.repository;

import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;

import java.util.List;

public interface LectureRepository {
    long create(LectureEntity lectureEntity);
    void update(LectureEntity lectureEntity);
    LectureEntity findByIdForUpdate(long id);
    LectureEntity findById(long id);
    List<LectureEntity> findAll();
}
