package com.hhpluslecture.lecture.aggregate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LectureApplication {
    private String lectureId;
    private String userId;
    private long application_time;

    public static LectureApplication newApplication(String lectureId, String userId) {
        return new LectureApplication(
                lectureId,
                userId,
                System.currentTimeMillis()
        );
    }
}
