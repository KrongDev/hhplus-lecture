package com.hhpluslecture.lectureApplication.repository.orm;

import com.hhpluslecture.lectureApplication.aggregate.entity.LectureApplicationEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LectureApplicationJpaRepository extends JpaRepository<LectureApplicationEntity, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select count(l) from LectureApplicationEntity l where l.lectureId = :lectureId")
    int countAllBy(long lectureId);
    List<LectureApplicationEntity> findAllByUserId(String userId);
}
