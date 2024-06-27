package com.hhpluslecture.lectureApplication.aggregate.dto;

import com.hhpluslecture.lectureApplication.aggregate.domain.LectureApplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LectureApplicationInfo {
    private String userId;
    private long lectureId;
    private LocalDateTime createAt;

    public static LectureApplicationInfo from(LectureApplication lectureApplication) {
        return LectureApplicationInfo.builder()
                .userId(lectureApplication.getUserId())
                .lectureId(lectureApplication.getLectureId())
                .createAt(lectureApplication.getCreateAt())
                .build();
    }
}
