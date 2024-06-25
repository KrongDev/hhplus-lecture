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
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "lecture")
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(name="Lecture.lectureApplications",
    attributeNodes = @NamedAttributeNode("lectureApplications")
)
public class LectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private int capacity;
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"lecture"})
    private List<LectureApplicationEntity> lectureApplications = new ArrayList<>();

    private LocalDateTime startAt;
    private LocalDateTime createAt;

    public static LectureEntity fromDomain(Lecture lecture) {
        LectureEntity entity = new LectureEntity();
        entity.setId(lecture.getId());
        entity.setTitle(lecture.getTitle());
        entity.setCapacity(lecture.getCapacity());
        entity.setStartAt(lecture.getStartAt());
        entity.setCreateAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")));

        return entity;
    }

    public void addLectureApplication(LectureApplicationEntity lectureApplication) {
        this.lectureApplications.add(lectureApplication);
        lectureApplication.setLecture(this);
    }

    public Lecture toDomain() {
        Lecture lecture = new Lecture();
        lecture.setId(this.id);
        lecture.setTitle(this.title);
        lecture.setCapacity(this.capacity);
        if(!Objects.isNull(this.lectureApplications))
            lecture.setLectureApplications(this.lectureApplications.stream().map(LectureApplicationEntity::toDto).toList());
        lecture.setStartAt(this.startAt);
        lecture.setCreateAt(this.createAt);
        return lecture;
    }

    public Lecture toDto() {
        Lecture lecture = new Lecture();
        lecture.setId(this.id);
        lecture.setTitle(this.title);
        lecture.setCapacity(this.capacity);
        lecture.setStartAt(this.startAt);
        lecture.setCreateAt(this.createAt);
        return lecture;
    }
}
