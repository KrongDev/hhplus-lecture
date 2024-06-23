package com.hhpluslecture.lecture.repository;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;
import com.hhpluslecture.lecture.repository.orm.LectureJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {
    //
    private final LectureJpaRepository lectureJpaRepository;

    @Override
    public Lecture findById(String id) {
        Optional<LectureEntity> lectureEntityOptional = lectureJpaRepository.findById(id);
        return lectureEntityOptional.map(LectureEntity::toDomain).orElse(null);
    }

    @Override
    public List<Lecture> findAllByUserId(String userId) {
        List<LectureEntity> lectures = this.lectureJpaRepository.findAllByOrderByStartTimeAsc();
        return lectures.stream().map(LectureEntity::toDomain).toList();
    }

    @Override
    public void save(Lecture lecture) {
        //
        this.lectureJpaRepository.save(new LectureEntity(lecture));
    }
}
