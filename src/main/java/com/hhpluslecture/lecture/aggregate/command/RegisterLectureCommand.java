package com.hhpluslecture.lecture.aggregate.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterLectureCommand {
    private String title;
    private int capacity;
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startAt;

    public void validate() {
        Assert.hasLength(title, "Title cannot be empty");
        Assert.isTrue(capacity > 0, "Capacity must be greater than 0");
        Assert.notNull(startAt, "Start date cannot be null");
    }
}
