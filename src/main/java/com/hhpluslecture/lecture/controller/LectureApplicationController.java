package com.hhpluslecture.lecture.controller;

import com.hhpluslecture.lecture.service.LectureApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lectures/application")
@RequiredArgsConstructor
public class LectureApplicationController {
    //
    private final LectureApplicationService lectureApplicationService;

    @GetMapping("/{userId}")
    public ResponseEntity<Boolean> isApplyComplete(@PathVariable String userId, @RequestParam long lectureId) {
        //
        return ResponseEntity.ok(lectureApplicationService.isApplyComplete(lectureId, userId));
    }
}
