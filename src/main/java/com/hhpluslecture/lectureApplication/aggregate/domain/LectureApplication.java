package com.hhpluslecture.lectureApplication.aggregate.domain;

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
    private long lectureId;
    private LocalDateTime createAt;

    public static LectureApplication newApplication(long lectureId, String userId) {
        return new LectureApplication(
                genId(lectureId, userId),
                userId,
                lectureId,
                LocalDateTime.now(ZoneId.of("Asia/Seoul"))
        );
    }

    public static String genId(long lectureId, String userId) {
        //
        return String.format("%s|%s", lectureId, userId);
    }
}
