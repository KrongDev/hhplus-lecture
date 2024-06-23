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

    private long lectureId = 0;
    private String title = "testCode";
    private String userId = "test";
    private int capacity = 10;
    private long start_at= System.currentTimeMillis();

    @Test
    @DisplayName("특강 신청 테스트 - 성공")
    @Description("lecture와 lectureApplication은 양방향 참조로 정상적으로 데이터가 저장되고 조회되는지 확인하는 테스트")
    void apply() {
        //Given
        LectureEntity lectureEntity = LectureEntity.of(title, capacity, start_at);
        lectureEntity = lectureJpaRepository.save(lectureEntity);

        //When
        String lectureApplicationId = String.format("%s-%s", lectureId, userId);
        LectureApplicationEntity lectureApplicationEntity = LectureApplicationEntity.of(lectureApplicationId, userId);
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
