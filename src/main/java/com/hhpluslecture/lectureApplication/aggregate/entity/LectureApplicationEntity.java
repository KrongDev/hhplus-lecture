package com.hhpluslecture.lectureApplication.aggregate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private long lectureId;

    private LocalDateTime createAt;
}
