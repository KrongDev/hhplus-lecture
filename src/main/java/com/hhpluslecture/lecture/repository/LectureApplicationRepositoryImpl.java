package com.hhpluslecture.lecture.repository;

import com.hhpluslecture.lecture.aggregate.domain.LectureApplication;
import com.hhpluslecture.lecture.aggregate.entity.LectureApplicationEntity;
import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;
import com.hhpluslecture.lecture.repository.orm.LectureApplicationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LectureApplicationRepositoryImpl implements LectureApplicationRepository{
    //
    private final LectureApplicationJpaRepository lectureApplicationJpaRepository;

    @Override
    public void save(LectureApplication lectureApplication) {
        //
        this.lectureApplicationJpaRepository.save(new LectureApplicationEntity(lectureApplication));
    }

    @Override
    public boolean hasLectureApplication(String lectureId, String userId) {
        // return this.lectureApplicationJpaRepository.existsById(new LectureApplicationEntity.ApplicationKey(lectureId, userId));
        Optional<LectureApplicationEntity> lectureApplication =  this.lectureApplicationJpaRepository.findById(new LectureApplicationEntity.ApplicationKey(lectureId, userId));
        return lectureApplication.isPresent();
    }
}
