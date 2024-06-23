package com.hhpluslecture.lecture.controller;

import com.hhpluslecture.lecture.aggregate.command.LectureApplicationCommand;
import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import com.hhpluslecture.lecture.service.LectureApplicationService;
import com.hhpluslecture.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/lectures")
@RestController
@RequiredArgsConstructor
public class LectureController {
    //
    private final LectureService lectureService;
    private final LectureApplicationService lectureApplicationService;

    /**
     * 특강 신청
     * @param lectureApplicationCommand 특강 신청에 필요한 정보들
     */
    @PostMapping("/apply")
    public void lectureApply(@RequestBody LectureApplicationCommand lectureApplicationCommand) {
        //
        lectureApplicationCommand.validate();
        String lectureId = lectureApplicationCommand.getLectureId();
        String userId = lectureApplicationCommand.getUserId();
        this.lectureService.lectureApply(lectureId, userId);
    }

    /**
     * 특강 신청 여부 조회 신청되었으면 true 아님 false
     */
    @GetMapping("/application/{userId}")
    public boolean isEnrolledInSpecialLecture(@PathVariable String userId, @RequestParam String lectureId) {
        //
        return this.lectureApplicationService.isEnrolledInSpecialLecture(userId, lectureId);
    }

    /**
     * 특강 리스트 조회
     * userId가 존재할시 신청한 특강 제외 조회
     * @param userId 유저 아이디
     * @return 신청가능한 특강 리스트 조회
     */
    @GetMapping()
    public List<Lecture> loadLectures(@RequestParam(required = false) String userId) {
        //
        return lectureService.loadLectures(userId);
    }
}
