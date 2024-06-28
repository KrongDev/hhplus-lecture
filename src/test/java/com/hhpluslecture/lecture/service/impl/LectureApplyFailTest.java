package com.hhpluslecture.lecture.service.impl;


import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;
import com.hhpluslecture.lecture.repository.LectureRepository;
import com.hhpluslecture.lecture.service.error.CapacityExceededException;
import com.hhpluslecture.lecture.service.error.RegistrationNotOpenException;
import com.hhpluslecture.lectureApplication.aggregate.entity.LectureApplicationEntity;
import com.hhpluslecture.lectureApplication.repository.LectureApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LectureApplyFailTest {
    //
    @InjectMocks
    private LectureServiceImpl lectureServiceimpl;

    @Mock
    private LectureRepository lectureRepository;
    @Mock
    private LectureApplicationRepository lectureApplicationRepository;

    private final int lectureId = 1;
    private final String userId = "test";
    private final LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    private LectureEntity lectureEntity;

    @BeforeEach
    public void setUp() {
        lectureEntity = new LectureEntity(
                 lectureId,
                 "testTitle",
                 0,
                 1,
                 LocalDateTime.of(2024, 6, 1, 12, 0),
                 now
         );
    }

    @Test
    @DisplayName("특강신청 - 강의가 시작하지 않은 경우")
    public void applyLectureNotStarted() {
        //Given
        lectureEntity.setStartAt(LocalDateTime.of(2030, 6, 1, 12, 0));
        when(lectureRepository.findByIdForUpdate(lectureId)).thenReturn(lectureEntity);
        //When-Then
        assertThrows(RegistrationNotOpenException.class, () -> lectureServiceimpl.applyLecture(lectureId, userId));
    }

    @Test
    @DisplayName("특강신청 - 수용가능한 인원을 초과한 경우")
    public void applyCapacityExceeded() {
        //Given
        lectureEntity.setCapacity(0);
        when(lectureRepository.findByIdForUpdate(lectureId)).thenReturn(lectureEntity);
        //When-Then
        assertThrows(CapacityExceededException.class, () -> lectureServiceimpl.applyLecture(lectureId, userId));
    }

    @Test
    @DisplayName("특강신청 - 강의를 이미 신청한경우")
    public void applyAlreadyApplied() {
        //Given
        when(lectureRepository.findByIdForUpdate(lectureId)).thenReturn(lectureEntity);
        when(lectureApplicationRepository.findById(String.format("%d|%s", lectureId, userId))).thenReturn(new LectureApplicationEntity("", userId, lectureId, LocalDateTime.now()));
        //When-Then
        assertThrows(IllegalArgumentException.class, () -> lectureServiceimpl.applyLecture(lectureId, userId));
    }
}
