package com.hhpluslecture.lectureApplication.service.mapper;

import com.hhpluslecture.lectureApplication.aggregate.domain.LectureApplication;
import com.hhpluslecture.lectureApplication.aggregate.entity.LectureApplicationEntity;

import java.util.Objects;

public class LectureApplicationMapper {

    public static LectureApplication convertToDomain(LectureApplicationEntity lectureApplicationEntity) {
        if(Objects.isNull(lectureApplicationEntity)) return null;
        LectureApplication lectureApplication = new LectureApplication();
        lectureApplication.setId(lectureApplicationEntity.getId());
        lectureApplication.setUserId(lectureApplicationEntity.getUserId());
        lectureApplication.setLectureId(lectureApplicationEntity.getLectureId());
        lectureApplication.setCreateAt(lectureApplicationEntity.getCreateAt());
        return lectureApplication;
    }

    public static LectureApplicationEntity convertToEntity(LectureApplication lectureApplication) {
        if(Objects.isNull(lectureApplication)) return null;
        LectureApplicationEntity lectureApplicationEntity = new LectureApplicationEntity();
        lectureApplicationEntity.setId(lectureApplication.getId());
        lectureApplicationEntity.setUserId(lectureApplication.getUserId());
        lectureApplicationEntity.setLectureId(lectureApplication.getLectureId());
        lectureApplicationEntity.setCreateAt(lectureApplication.getCreateAt());
        return lectureApplicationEntity;
    }
}
