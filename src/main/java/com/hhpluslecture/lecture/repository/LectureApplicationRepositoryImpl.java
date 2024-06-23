package com.hhpluslecture.lecture.repository;

import com.hhpluslecture.lecture.aggregate.entity.LectureApplicationEntity;
import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;
import com.hhpluslecture.lecture.repository.orm.LectureApplicationJpaRepository;
import com.hhpluslecture.lecture.repository.orm.LectureJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LectureApplicationRepositoryImpl implements LectureApplicationRepository {
    //
    private final LectureJpaRepository lectureJpaRepository;
    private final LectureApplicationJpaRepository lectureApplicationJpaRepository;

    @Override
    public String create(long lectureId, String userId) {
        LectureEntity lectureEntity = this.lectureJpaRepository.findById(lectureId)
                .orElseThrow(() -> new RuntimeException("Lecture with ID " + lectureId + " not found"));

        String applicationId = lectureId + "-" + userId;
        LectureApplicationEntity lectureApplicationEntity = LectureApplicationEntity.of(applicationId, userId);
        lectureEntity.addLectureApplication(lectureApplicationEntity);
        lectureApplicationJpaRepository.save(lectureApplicationEntity);
        return applicationId;
    }
}
