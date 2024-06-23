package com.hhpluslecture.lecture.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhpluslecture.lecture.aggregate.event.LectureApplied;
import com.hhpluslecture.lecture.service.LectureApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LectureApplicationHandler {
    //
    private final LectureApplicationService lectureApplicationService;

    @EventListener
    public void on(LectureApplied lectureApplied) {
        String lectureId = lectureApplied.getLectureId();
        String userId = lectureApplied.getUserId();
        this.lectureApplicationService.create(lectureId, userId);
    }
}
