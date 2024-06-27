package com.hhpluslecture.lectureApplication.repository.orm;

import com.hhpluslecture.lectureApplication.aggregate.entity.LectureApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureApplicationJpaRepository extends JpaRepository<LectureApplicationEntity, String> {
    List<LectureApplicationEntity> findAllByUserId(String userId);
}
