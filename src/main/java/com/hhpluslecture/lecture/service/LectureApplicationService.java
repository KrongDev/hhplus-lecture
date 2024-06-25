package com.hhpluslecture.lecture.service;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;

public interface LectureApplicationService {
    void apply(long lectureId, String userId, Lecture lecture);
    boolean isApplyComplete(long lectureId, String userId);
}
