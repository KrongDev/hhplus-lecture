package com.hhpluslecture.lecture.repository.impl;

import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;
import com.hhpluslecture.lecture.repository.LectureRepository;
import com.hhpluslecture.lecture.repository.orm.LectureJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {
    //
    private final LectureJpaRepository lectureJpaRepository;

    @Override
    public long create(LectureEntity lectureEntity) {
        //
        lectureJpaRepository.save(lectureEntity);
        return lectureEntity.getId();
    }

    @Override
    public void update(LectureEntity lectureEntity) {
        lectureJpaRepository.save(lectureEntity);
    }

    @Override
    public LectureEntity findById(long id) {
        Optional<LectureEntity> res =  lectureJpaRepository.findById(id);
        if(res.isEmpty())
            throw new NoSuchElementException("Lecture with id " + id + " not found");
        return res.get();
    }

    @Override
    public List<LectureEntity> findAll() {
        return lectureJpaRepository.findAllByOrderByStartAtAsc();
    }
}
