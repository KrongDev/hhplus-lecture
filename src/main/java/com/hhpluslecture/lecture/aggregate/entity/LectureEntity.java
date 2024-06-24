package com.hhpluslecture.lecture.aggregate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lecture")
@AllArgsConstructor
@NoArgsConstructor
public class LectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private int capacity;
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"lecture"})
    private List<LectureApplicationEntity> lectureApplications = new ArrayList<>();

    private LocalDateTime start_at;
    private LocalDateTime create_at;

    public static LectureEntity fromDomain(Lecture lecture) {
        LectureEntity entity = new LectureEntity();
        entity.setTitle(lecture.getTitle());
        entity.setCapacity(lecture.getCapacity());
        entity.setStart_at(lecture.getStart_at());
        entity.setCreate_at(LocalDateTime.now(ZoneId.of("Asia/Seoul")));

        return entity;
    }

    public void addLectureApplication(LectureApplicationEntity lectureApplication) {
        this.lectureApplications.add(lectureApplication);
        lectureApplication.setLecture(this);
    }
}
