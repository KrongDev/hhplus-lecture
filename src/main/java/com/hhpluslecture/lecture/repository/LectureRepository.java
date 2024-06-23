package com.hhpluslecture.lecture.repository;

import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;

public interface LectureRepository {
    long create(String title, int capacity, long startAt);
    LectureEntity findByIdForUpdate(long id);
}
