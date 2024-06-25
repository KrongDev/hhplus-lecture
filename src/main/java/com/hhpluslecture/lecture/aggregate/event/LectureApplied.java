package com.hhpluslecture.lecture.aggregate.event;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
public class LectureApplied extends ApplicationEvent {
    //
    private long lectureId;
    private String userId;
    private LocalDateTime application_time;

    public LectureApplied(long lectureId, String userId, Lecture lecture) {
        super(lecture);
        this.lectureId = lectureId;
        this.userId = userId;
        this.application_time = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
