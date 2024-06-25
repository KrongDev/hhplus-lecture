package com.hhpluslecture.lecture.aggregate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
public class Lecture {
    private long id;
    private String title;
    private int capacity;
    private List<LectureApplication> lectureApplications;
    private LocalDateTime startAt;
    private LocalDateTime createAt;

    public Lecture() {
        //
        this.lectureApplications = new ArrayList<>();
    }

    public Lecture(String title, int capacity, LocalDateTime startAt) {
        //
        this();
        this.title = title;
        this.capacity = capacity;
        this.startAt = startAt;
        this.createAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    public int getHeadCount() {
        //
        return this.lectureApplications.size();
    }

    public boolean isCapacityExceeded() {
        //
        return getHeadCount() == this.capacity;
    }

    public boolean isEnrollmentStarted() {
        //
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        return this.startAt.isBefore(now);
    }

    public boolean isAlreadyApplied(String userId) {
        if(Objects.isNull(this.lectureApplications) || this.lectureApplications.isEmpty()) return false;
        return lectureApplications.stream().anyMatch(lectureApplication -> userId.equals(lectureApplication.getUserId()));
    }
}
