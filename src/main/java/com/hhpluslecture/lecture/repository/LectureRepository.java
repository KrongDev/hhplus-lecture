package com.hhpluslecture.lecture.repository;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;

import java.util.List;

public interface LectureRepository {
    Lecture findById(String id);
    List<Lecture> findAllByUserId(String userId);
    void save(Lecture lecture);
}
