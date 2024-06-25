package com.hhpluslecture.lecture.repository.impl;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;
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
    public long create(Lecture lecture) {
        //
        LectureEntity lectureEntity = LectureEntity.fromDomain(lecture);
        lectureJpaRepository.save(lectureEntity);
        return lectureEntity.getId();
    }

    @Override
    public Lecture findById(long id) {
        Optional<LectureEntity> res =  lectureJpaRepository.findById(id);
        if(res.isEmpty())
            throw new NoSuchElementException("Lecture with id " + id + " not found");
        return res.get().toDomain();
    }

    /**
     * 특강을 조회하는 메소드
     * 쓰기전용 JPA 비관적 락이 걸려있는 조회로 해당 메소드를 사용할 시 Lock에 걸린다.
     * @param id lecture 아이디
     * @return Lecture
     */
    @Override
    public Lecture findByIdForUpdate(long id) {
        Optional<LectureEntity> res =  lectureJpaRepository.findByIdForUpdate(id);
        if(res.isEmpty())
            throw new NoSuchElementException("Lecture with id " + id + " not found");
        return res.get().toDomain();
    }

    @Override
    public List<Lecture> findAll() {
        List<LectureEntity> lectureEntities = lectureJpaRepository.findAllByOrderByStartAtAsc();
        return lectureEntities.stream().map(LectureEntity::toDomain).toList();
    }
}
