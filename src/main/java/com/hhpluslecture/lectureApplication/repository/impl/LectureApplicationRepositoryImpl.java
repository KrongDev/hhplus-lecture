package com.hhpluslecture.lectureApplication.repository.impl;

import com.hhpluslecture.lectureApplication.aggregate.entity.LectureApplicationEntity;
import com.hhpluslecture.lectureApplication.repository.LectureApplicationRepository;
import com.hhpluslecture.lectureApplication.repository.orm.LectureApplicationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LectureApplicationRepositoryImpl implements LectureApplicationRepository {
    //
    private final LectureApplicationJpaRepository lectureApplicationJpaRepository;

    @Override
    public String create(LectureApplicationEntity lectureApplicationEntity) {
        lectureApplicationJpaRepository.save(lectureApplicationEntity);
        return lectureApplicationEntity.getId();
    }
    @Override
    public LectureApplicationEntity findById(String lectureApplicationId) {
        Optional<LectureApplicationEntity> application =  lectureApplicationJpaRepository.findById(lectureApplicationId);
        return application.orElse(null);
    }

    @Override
    public List<LectureApplicationEntity> findByUserId(String userId) {
        return  lectureApplicationJpaRepository.findAllByUserId(userId);
    }

    @Override
    public int countByLectureId(long lectureId) {
        return lectureApplicationJpaRepository.countAllBy(lectureId);
    }
}
