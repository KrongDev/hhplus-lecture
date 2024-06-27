package com.hhpluslecture.lecture.repository.orm;


import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {
    //
    Optional<LectureEntity> findById(long id);
    List<LectureEntity> findAllByOrderByStartAtAsc();
}
