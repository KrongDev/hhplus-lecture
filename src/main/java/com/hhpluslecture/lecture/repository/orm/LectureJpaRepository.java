package com.hhpluslecture.lecture.repository.orm;


import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {
    //
    Optional<LectureEntity> findById(long id);
    List<LectureEntity> findAllByOrderByStartAtAsc();
}
