package com.hhpluslecture.lecture.repository;

import com.hhpluslecture.lecture.aggregate.domain.LectureApplication;
import com.hhpluslecture.lecture.aggregate.entity.LectureApplicationEntity;
import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;
import com.hhpluslecture.lecture.repository.orm.LectureApplicationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LectureApplicationRepositoryImpl implements LectureApplicationRepository {
    //
//    private final LectureJpaRepository lectureJpaRepository;
    private final LectureApplicationJpaRepository lectureApplicationJpaRepository;

    @Override
    public String create(LectureApplication lectureApplication) {
        LectureEntity lectureEntity = LectureEntity.fromDomain(lectureApplication.getLecture());
        LectureApplicationEntity lectureApplicationEntity = LectureApplicationEntity.from(lectureApplication);
        lectureEntity.addLectureApplication(lectureApplicationEntity);
        lectureApplicationJpaRepository.save(lectureApplicationEntity);
        return lectureApplicationEntity.getId();
    }
}
