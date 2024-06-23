package com.hhpluslecture.lecture.aggregate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lecture {
    private String lectureId;
    private int version;
    private String title;
    private String description;
    private int headCount;
    private int capacity;
    private long startTime;
    private long createdAt;

    public void addCount() {
        this.headCount++;
    }

    public boolean isAtCapacity() {
        //
        return this.headCount == this.capacity;
    }

    public boolean isEnrollmentStarted() {
        //
        long now = System.currentTimeMillis();
        return now >= this.startTime;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}
