package com.hhpluslecture.lecture.service.impl;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import com.hhpluslecture.lecture.service.LectureService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.yml")
class LectureServiceImplTest {

    @Autowired
    private LectureService lectureService;

    private final String title = "test";
    private final int capacity = 5;
    private final LocalDateTime startAt = LocalDateTime.of(2024, 6, 1, 12, 0);
    private long lectureId = 1;

    @Test
    @Order(1)
    @DisplayName("정상적으로 특강이 생성되는지 검증")
    void createLecture() {
        //Given
        lectureId = lectureService.createLecture(title, capacity, startAt);

        assertNotEquals(0, lectureId);
    }

    @Test
    @Order(2)
    @DisplayName("정상적으로 특강이 신청되는지 검증")
    void applyLecture() {
        //When
        String userId = "1";
        lectureService.applyLecture(lectureId, userId);
    }

    @Test
    @Order(3)
    @DisplayName("정상적으로 특강을 가져오는지 검증")
    void loadLecture() {
        //WHEN
        Lecture lecture = lectureService.loadLecture(lectureId);
        //THEN
        assertNotNull(lecture);
        assertEquals(title, lecture.getTitle());
        assertEquals(capacity, lecture.getCapacity());
        assertEquals(startAt, lecture.getStartAt());
        assertEquals(lectureId, lecture.getId());
    }

    @Test
    @Order(4)
    @DisplayName("정상적으로 리스트 조회되는지 검증 - query문 한개만 발행하도록")
    void loadLectures() {
        //When
        List<Lecture> lectures = lectureService.loadLectures("");
        //Then
        assertNotNull(lectures);
        assertNotEquals(0, lectures.size());
        Lecture lecture = lectures.stream().filter(l -> l.getId() == lectureId).findFirst().orElse(null);
        assertNotNull(lecture);
    }

    @Test
    @Order(5)
    @DisplayName("특강 신청 동시성 테스트")
    void usePoint() throws InterruptedException {
        //When
        int memberCount = 6;
        ExecutorService executorsService = Executors.newFixedThreadPool(memberCount);
        CountDownLatch latch = new CountDownLatch(memberCount);

        AtomicInteger successCount = new AtomicInteger();
        sleep(1000);

        for (int i = 0; i < memberCount; i++) {
            executorsService.submit(() -> {
                try {
                    lectureService.applyLecture(lectureId, UUID.randomUUID().toString());
                    successCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        //Then
        assertAll(
                () -> assertEquals(successCount.get(), 4)
        );
    }
}
