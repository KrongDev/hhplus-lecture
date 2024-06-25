package com.hhpluslecture.lecture.aggregate.dto;

import com.hhpluslecture.lecture.aggregate.domain.LectureApplication;
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
    private String lectureTitle;
    private LocalDateTime createAt;

    public static LectureApplicationInfo from(LectureApplication lectureApplication) {
        return LectureApplicationInfo.builder()
                .userId(lectureApplication.getUserId())
                .lectureId(lectureApplication.getLecture().getId())
                .lectureTitle(lectureApplication.getLecture().getTitle())
                .createAt(lectureApplication.getCreateAt())
                .build();
    }
}
