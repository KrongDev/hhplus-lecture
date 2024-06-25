package com.hhpluslecture.lecture.aggregate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LectureApplication {
    private String id;
    private String userId;
    private Lecture lecture;
    private LocalDateTime createAt;

    public static LectureApplication newApplication(long lectureId, String userId, Lecture lecture) {
        return new LectureApplication(
                genId(lectureId, userId),
                userId,
                lecture,
                LocalDateTime.now(ZoneId.of("Asia/Seoul"))
        );
    }

    public static String genId(long lectureId, String userId) {
        //
        return String.format("%s|%s", lectureId, userId);
    }
}
