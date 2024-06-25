package com.hhpluslecture.lecture.service;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureService {
    long createLecture(String title, int capacity, LocalDateTime startAt);
    void applyLecture(long lectureId, String userId);
    Lecture loadLecture(long lectureId);
    List<Lecture> loadLectures();
}
