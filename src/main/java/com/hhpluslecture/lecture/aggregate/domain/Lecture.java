package com.hhpluslecture.lecture.aggregate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class Lecture {
    private long id;
    private String title;
    private int capacity;
    private List<LectureApplication> lectureApplications;
    private LocalDateTime start_at;
    private LocalDateTime create_at;

    public Lecture() {
        //
        this.lectureApplications = new ArrayList<>();
    }

    public Lecture(String title, int capacity, LocalDateTime start_at) {
        //
        this();
        this.title = title;
        this.capacity = capacity;
        this.start_at = start_at;
        this.create_at = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    public int getHeadCount() {
        //
        return this.lectureApplications.size();
    }

    public boolean isAtCapacity() {
        //
        return getHeadCount() == this.capacity;
    }

    public boolean isEnrollmentStarted() {
        //
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        return this.start_at.isBefore(now);
    }
}
