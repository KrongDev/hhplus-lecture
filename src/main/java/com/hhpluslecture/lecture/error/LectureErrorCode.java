package com.hhpluslecture.lecture.error;

import com.hhpluslecture.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LectureErrorCode implements ErrorCode {
    NOTFOUND_LECTURE(500, "LECTURE-001", "신청하려는 특강을 찾을 수 없습니다."),
    NOT_START_LECTURE(500, "LECTURE-002", "해당 특강이 아직 오픈되지 않았습니다."),
    EXCEEDED_LECTURE(500, "LECTURE-003", "특강 신청 가능인원이 다 찼습니다."),
    ALREADY_APPLIED(500, "LECTURE-004", "이미 신청한 특강입니다."),
    ;
    private final int status;
    private final String code;
    private final String description;
}
