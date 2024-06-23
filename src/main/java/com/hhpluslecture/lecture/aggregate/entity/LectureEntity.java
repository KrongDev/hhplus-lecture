package com.hhpluslecture.lecture.aggregate.entity;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Entity
@Table(name="Lecture")
@AllArgsConstructor
@NoArgsConstructor
public class LectureEntity {
    @Id
    private String lectureId;
    @Version
    private int version;
    private String title;
    private String description;
    private int headCount;
    private int capacity;

    private long startTime;
    private long createdAt;

    public LectureEntity(Lecture lecture) {
        //
        BeanUtils.copyProperties(lecture, this);
    }

    public Lecture toDomain() {
        //
        Lecture lecture = new Lecture();
        BeanUtils.copyProperties(this, lecture);
        return lecture;
    }
}
