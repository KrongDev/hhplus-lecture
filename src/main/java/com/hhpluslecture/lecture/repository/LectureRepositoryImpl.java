package com.hhpluslecture.lecture.repository;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;
import com.hhpluslecture.lecture.repository.orm.LectureJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

    /**
     * 특강을 조회하는 메소드
     * 쓰기전용 JPA 비관적 락이 걸려있는 조회로 해당 메소드를 사용할 시 Lock에 걸린다.
     * @param id lecture 아이디
     * @return Lecture
     */
    @Override
    public LectureEntity findByIdForUpdate(long id) {
        Optional<LectureEntity> res =  lectureJpaRepository.findById(id);
        if(res.isEmpty())
            throw new NoSuchElementException("Lecture with id " + id + " not found");
        return res.get();
    }
}
