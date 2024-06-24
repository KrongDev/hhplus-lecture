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
    private String id;
    private String userId;
    private Lecture lecture;


    public static LectureApplication newApplication(String lectureId, String userId, Lecture lecture) {
        return new LectureApplication(
                String.format("%s|%s", lectureId, userId),
                userId,
                lecture
        );
    }
}
