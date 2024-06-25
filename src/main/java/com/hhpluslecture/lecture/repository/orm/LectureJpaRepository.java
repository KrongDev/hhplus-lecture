package com.hhpluslecture.lecture.repository.orm;


import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select l from LectureEntity l where l.id = :id")
    @EntityGraph(value = "Lecture.lectureApplications")
    Optional<LectureEntity> findByIdForUpdate(long id);

    @EntityGraph(value = "Lecture.lectureApplications")
    Optional<LectureEntity> findById(long id);

    @EntityGraph(value = "Lecture.lectureApplications")
    List<LectureEntity> findAllByOrderByStartAtAsc();
}
