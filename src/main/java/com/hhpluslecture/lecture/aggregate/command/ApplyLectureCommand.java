package com.hhpluslecture.lecture.aggregate.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplyLectureCommand {
    //
    private long lectureId;
    private String userId;

    public void validate() {
        Assert.notNull(lectureId, "Lecture ID cannot be null");
        Assert.hasText(userId, "User ID cannot be null");
    }
}
