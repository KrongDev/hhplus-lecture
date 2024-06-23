package com.hhpluslecture.lecture.repository;

import com.hhpluslecture.lecture.aggregate.domain.LectureApplication;

public interface LectureApplicationRepository {
    void save(LectureApplication lectureApplication);
    boolean hasLectureApplication(String lectureId, String userId);
}
