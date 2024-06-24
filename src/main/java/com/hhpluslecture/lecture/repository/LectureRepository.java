package com.hhpluslecture.lecture.repository;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;

public interface LectureRepository {
    long create(Lecture lecture);
    LectureEntity findByIdForUpdate(long id);
}
