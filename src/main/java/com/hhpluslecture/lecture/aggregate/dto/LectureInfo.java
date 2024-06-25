package com.hhpluslecture.lecture.aggregate.dto;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LectureInfo {
    private long id;
    private String title;
    private int capacity;
    private int headCount;
    private LocalDateTime startAt;
    private LocalDateTime createAt;

    public static List<LectureInfo> fromDomains(List<Lecture> lectures) {
        return lectures.stream().map(LectureInfo::fromDomain).toList();
    }

    public static LectureInfo fromDomain(Lecture lecture) {
        return LectureInfo.builder()
                .id(lecture.getId())
                .title(lecture.getTitle())
                .capacity(lecture.getCapacity())
                .headCount(lecture.getHeadCount())
                .startAt(lecture.getStartAt())
                .createAt(lecture.getCreateAt())
                .build();
    }
}
