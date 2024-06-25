package com.hhpluslecture.lecture.repository.impl;

import com.hhpluslecture.lecture.aggregate.domain.LectureApplication;
import com.hhpluslecture.lecture.aggregate.entity.LectureApplicationEntity;
import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;
import com.hhpluslecture.lecture.repository.LectureApplicationRepository;
import com.hhpluslecture.lecture.repository.orm.LectureApplicationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LectureApplicationRepositoryImpl implements LectureApplicationRepository {
    //
    private final LectureApplicationJpaRepository lectureApplicationJpaRepository;

    @Override
    public String create(LectureApplication lectureApplication) {
        LectureEntity lectureEntity = LectureEntity.fromDomain(lectureApplication.getLecture());
        LectureApplicationEntity lectureApplicationEntity = LectureApplicationEntity.from(lectureApplication);
        lectureEntity.addLectureApplication(lectureApplicationEntity);
        lectureApplicationJpaRepository.save(lectureApplicationEntity);
        return lectureApplicationEntity.getId();
    }
    @Override
    public LectureApplication findById(String lectureApplicationId) {
        Optional<LectureApplicationEntity> res = lectureApplicationJpaRepository.findById(lectureApplicationId);
        if (res.isEmpty())
            throw new NoSuchElementException("no such lecture application by id: " + lectureApplicationId);
        return res.get().toDomain();
    }
}
