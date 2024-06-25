package com.hhpluslecture.lecture.repository;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;

import java.util.List;

public interface LectureRepository {
    long create(Lecture lecture);
    Lecture findById(long id);
    Lecture findByIdForUpdate(long id);
    List<Lecture> findAll();
}
