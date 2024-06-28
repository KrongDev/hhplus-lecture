package com.hhpluslecture.lecture.service.mapper;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import com.hhpluslecture.lecture.aggregate.entity.LectureEntity;

import java.util.Objects;

public class LectureMapper {

    public static Lecture convertToDomain(LectureEntity lectureEntity) {
        if(Objects.isNull(lectureEntity)) return null;
        Lecture lecture = new Lecture();
        lecture.setId(lectureEntity.getId());
        lecture.setTitle(lectureEntity.getTitle());
        lecture.setHeadCount(lectureEntity.getHeadCount());
        lecture.setCapacity(lectureEntity.getCapacity());
        lecture.setStartAt(lectureEntity.getStartAt());
        lecture.setCreateAt(lectureEntity.getCreateAt());
        return lecture;
    }

    public static LectureEntity convertToEntity(Lecture lecture) {
        if(Objects.isNull(lecture)) return null;
        LectureEntity entity = new LectureEntity();
        entity.setId(lecture.getId());
        entity.setTitle(lecture.getTitle());
        entity.setHeadCount(lecture.getHeadCount());
        entity.setCapacity(lecture.getCapacity());
        entity.setStartAt(lecture.getStartAt());
        entity.setCreateAt(lecture.getCreateAt());
        return entity;
    }
}
