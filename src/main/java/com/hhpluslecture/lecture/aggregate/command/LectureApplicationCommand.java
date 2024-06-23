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
public class LectureApplicationCommand {
    //
    private String lectureId;
    private String userId;

    public void validate() {
        Assert.hasText(this.lectureId, "Lecture ID is required");
        Assert.hasText(this.userId, "User ID is required");
    }
}
