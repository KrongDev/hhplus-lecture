package com.hhpluslecture.lecture.service;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;

import java.util.List;

public interface LectureService {
    void lectureApply(String lectureId, String userId);
    List<Lecture> loadLectures(String userId);
}
