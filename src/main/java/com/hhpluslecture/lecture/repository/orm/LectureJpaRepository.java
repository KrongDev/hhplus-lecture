package com.hhpluslecture.lecture.repository.orm;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, String> {
    List<LectureEntity> findAllByOrderByStartTimeAsc();
}
