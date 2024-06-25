package com.hhpluslecture.lecture.repository;

import com.hhpluslecture.lecture.aggregate.entity.LectureApplicationEntity;
import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;
import com.hhpluslecture.lecture.repository.orm.LectureApplicationJpaRepository;
import com.hhpluslecture.lecture.repository.orm.LectureJpaRepository;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
class LectureApplicationRepositoryImplTest {

    @Autowired
    private LectureApplicationJpaRepository lectureApplicationJpaRepository;
    @Autowired
    private LectureJpaRepository lectureJpaRepository;

    @BeforeEach
    public void setUp() {
        lectureJpaRepository.deleteAll();
        lectureApplicationJpaRepository.deleteAll();
    }

    private final LocalDateTime startAt= LocalDateTime.of(2024, 6, 1, 12, 0);

    @Test
    @DisplayName("특강 신청 테스트 - 성공")
    @Description("lecture와 lectureApplication은 양방향 참조로 정상적으로 데이터가 저장되고 조회되는지 확인하는 테스트")
    void apply() {
        //Given
        long lectureId = 0;
        String title = "testCode";
        int capacity = 10;
        LectureEntity lectureEntity = new LectureEntity(lectureId, title, capacity, new ArrayList<>(), startAt, LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        lectureEntity = lectureJpaRepository.save(lectureEntity);

        //When
        String userId = "test";
        String lectureApplicationId = String.format("%s|%s", lectureId, userId);
        LectureApplicationEntity lectureApplicationEntity = new LectureApplicationEntity(lectureApplicationId, userId, null ,LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        lectureEntity.addLectureApplication(lectureApplicationEntity);
        lectureApplicationJpaRepository.save(lectureApplicationEntity);

        //Then
        LectureApplicationEntity newLectureApplicationEntity = lectureApplicationJpaRepository.findById(lectureApplicationId).get();
        LectureEntity newLectureEntity = lectureJpaRepository.findById(lectureEntity.getId()).get();
//        ObjectMapper om = new ObjectMapper();
//        System.out.println(om.writeValueAsString(newLectureApplicationEntity));
//        System.out.println(om.writeValueAsString(newLectureEntity));

        assertEquals(1, newLectureEntity.getLectureApplications().size());
        assertEquals(newLectureApplicationEntity.getId(), newLectureEntity.getLectureApplications().get(0).getId());
    }
}
