package com.hhpluslecture.lecture.aggregate.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LectureTest {

    private Lecture lecture;
    private final long id = 1;
    private final String title = "title";
    private final int capacity = 30;
    private final LocalDateTime start_at = LocalDateTime.of(2024, 6, 1, 12, 0);

    @BeforeEach
    public void setUp() {
         lecture = new Lecture(title, capacity, start_at);
         lecture.setId(id);
    }

    @Test
    @DisplayName("인원수 체크 기능 검증 - 만석일경우 true")
    public void isAtCapacity() {

        assertFalse(lecture.isAtCapacity());
    }

    @Test
    @DisplayName("특강 신청기간 확인 기능 검증 - 시작 전일경우 false")
    public void isEnrollmentStarted() {

        assertTrue(lecture.isEnrollmentStarted());
    }

}
