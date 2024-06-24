package com.hhpluslecture.lecture.aggregate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hhpluslecture.lecture.aggregate.domain.LectureApplication;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="lecture_application")
@AllArgsConstructor
@NoArgsConstructor
public class LectureApplicationEntity {
    @Id
    private String id;
    private String userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"lectureApplications"})
    @JoinColumn(name = "lecture_id", referencedColumnName = "id", nullable = false)
    private LectureEntity lecture;

    public static LectureApplicationEntity from(LectureApplication lectureApplication) {
        LectureApplicationEntity lectureApplicationEntity = new LectureApplicationEntity();
        lectureApplicationEntity.setId(lectureApplication.getId());
        lectureApplicationEntity.setUserId(lectureApplication.getUserId());
        return lectureApplicationEntity;
    }
}
