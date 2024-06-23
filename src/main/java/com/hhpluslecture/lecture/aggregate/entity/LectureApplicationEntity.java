package com.hhpluslecture.lecture.aggregate.entity;

import com.hhpluslecture.lecture.aggregate.domain.LectureApplication;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="lecture_application")
@AllArgsConstructor
@NoArgsConstructor
public class LectureApplicationEntity {
    @EmbeddedId
    private ApplicationKey key;
    private long application_time;

    public LectureApplicationEntity(LectureApplication lectureApplication) {
        //
        BeanUtils.copyProperties(lectureApplication, this);
        this.key = new ApplicationKey(lectureApplication.getLectureId(), lectureApplication.getUserId());
    }

    public LectureApplication toDomain() {
        LectureApplication lectureApplication = new LectureApplication();
        BeanUtils.copyProperties(this, lectureApplication);
        lectureApplication.setLectureId(key.getLectureId());
        lectureApplication.setUserId(key.getUserId());
        return lectureApplication;
    }

    @Data
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApplicationKey implements Serializable {
        private String lectureId;
        private String userId;
    }
}
