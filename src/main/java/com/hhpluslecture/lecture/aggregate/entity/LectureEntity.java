package com.hhpluslecture.lecture.aggregate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lecture")
public class LectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private int capacity;
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"lecture"})
    private List<LectureApplicationEntity> lectureApplications = new ArrayList<>();

    private long start_at;
    private long create_at;

    public static LectureEntity of(String title, int capacity, long startAt) {
        LectureEntity entity = new LectureEntity();
        entity.setTitle(title);
        entity.setCapacity(capacity);
        entity.setStart_at(startAt);
        entity.setCreate_at(System.currentTimeMillis());

        return entity;
    }

    public void addLectureApplication(LectureApplicationEntity lectureApplication) {
        this.lectureApplications.add(lectureApplication);
        lectureApplication.setLecture(this);
    }
}
