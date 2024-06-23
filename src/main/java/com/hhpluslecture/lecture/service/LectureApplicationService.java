package com.hhpluslecture.lecture.service;

public interface LectureApplicationService {
    void create(String lectureId, String userId);
    boolean isEnrolledInSpecialLecture(String lectureId, String userId);
}
