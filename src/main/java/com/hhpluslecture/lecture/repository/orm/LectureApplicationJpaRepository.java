package com.hhpluslecture.lecture.repository.orm;

import com.hhpluslecture.lecture.aggregate.entity.LectureApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureApplicationJpaRepository extends JpaRepository<LectureApplicationEntity, String> {
}
