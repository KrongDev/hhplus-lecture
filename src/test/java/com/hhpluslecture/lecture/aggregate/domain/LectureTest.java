package com.hhpluslecture.lecture.aggregate.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LectureTest {

    private Lecture lecture;
    private final LocalDateTime startAt = LocalDateTime.of(2024, 6, 1, 12, 0);

    @BeforeEach
    public void setUp() {
        String title = "title";
        int capacity = 30;
        lecture = new Lecture(title, capacity, startAt);
        long id = 1;
        lecture.setId(id);
    }

    @Test
    @DisplayName("인원수 체크 기능 검증 - 만석일경우 true")
    public void isAtCapacity() {

        assertFalse(lecture.isCapacityExceeded());
    }

    @Test
    @DisplayName("특강 신청기간 확인 기능 검증 - 시작 전일경우 false")
    public void isEnrollmentStarted() {

        assertTrue(lecture.isEnrollmentStarted());
    }

}
