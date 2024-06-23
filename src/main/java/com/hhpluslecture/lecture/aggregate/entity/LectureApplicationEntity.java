package com.hhpluslecture.lecture.aggregate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="lecture_application")
@NoArgsConstructor
public class LectureApplicationEntity {
    @Id
    private String id;
    private String userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"lectureApplications"})
    @JoinColumn(name = "lecture_id", referencedColumnName = "id", nullable = false)
    private LectureEntity lecture;

    public static LectureApplicationEntity of(String id, String userId) {
        LectureApplicationEntity lectureApplicationEntity = new LectureApplicationEntity();
        lectureApplicationEntity.setId(id);
        lectureApplicationEntity.setUserId(userId);
        return lectureApplicationEntity;
    }
}
