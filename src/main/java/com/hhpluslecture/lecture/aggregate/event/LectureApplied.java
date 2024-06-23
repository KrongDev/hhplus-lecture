package com.hhpluslecture.lecture.aggregate.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureApplied {
    //
    private String lectureId;
    private String userId;
    private long application_time;

    public LectureApplied(String lectureId, String userId) {
        this.lectureId = lectureId;
        this.userId = userId;
    }
}
