package com.hhpluslecture.lecture.aggregate.domain;

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
@AllArgsConstructor
@NoArgsConstructor
public class Lecture {
    private long id;
    private String title;
    private int headCount;
    private int capacity;
    private LocalDateTime startAt;
    private LocalDateTime createAt;

    public Lecture(String title, int capacity, LocalDateTime startAt) {
        //
        this.title = title;
        this.capacity = capacity;
        this.startAt = startAt;
        this.createAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    public void apply() {
        this.headCount++;
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
}
