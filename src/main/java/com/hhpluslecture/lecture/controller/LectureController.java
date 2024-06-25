package com.hhpluslecture.lecture.controller;

import com.hhpluslecture.lecture.aggregate.command.ApplyLectureCommand;
import com.hhpluslecture.lecture.aggregate.command.RegisterLectureCommand;
import com.hhpluslecture.lecture.aggregate.dto.LectureInfo;
import com.hhpluslecture.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/lectures")
@RequiredArgsConstructor
public class LectureController {
    //
    private final LectureService lectureService;

    @PostMapping
    public ResponseEntity<Long> createLecture(@RequestBody RegisterLectureCommand registerLectureCommand) {
        registerLectureCommand.validate();
        String title = registerLectureCommand.getTitle();
        int capacity = registerLectureCommand.getCapacity();
        LocalDateTime startAt = registerLectureCommand.getStartAt();
        return ResponseEntity.ok(this.lectureService.createLecture(title, capacity, startAt));
    }

    @PostMapping("/apply")
    public void apply(@RequestBody ApplyLectureCommand command) {
        long lectureId = command.getLectureId();
        String userId = command.getUserId();
        lectureService.applyLecture(lectureId, userId);
    }

    @GetMapping()
    public ResponseEntity<List<LectureInfo>> loadLectureList(@RequestParam(required = false) String userId) {
        //
        return ResponseEntity.ok(LectureInfo.fromDomains(lectureService.loadLectures(userId)));
    }
}
