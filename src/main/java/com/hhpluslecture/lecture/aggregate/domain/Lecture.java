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

    /**
     * 특강 인원수 추가
     */
    public void addCount() {
        this.headCount++;
    }

    /**
     * 신청가능 인원수 확인
     * @return 제한보다 적을경우 true 반환 or false
     */
    public boolean isAtCapacity() {
        //
        return this.headCount == this.capacity;
    }

    /**
     * 특강 신청이 시작날짜가 되었는지 확인
     * @return 가능하면 true or false
     */
    public boolean isEnrollmentStarted() {
        //
        long now = System.currentTimeMillis();
        return now >= this.startTime;
    }
}
