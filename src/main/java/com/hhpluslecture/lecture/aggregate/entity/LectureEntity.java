package com.hhpluslecture.lecture.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "lecture")
@AllArgsConstructor
@NoArgsConstructor
public class LectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Version
    private int version;
    private int headCount;
    private int capacity;

    private LocalDateTime startAt;
    private LocalDateTime createAt;
}
