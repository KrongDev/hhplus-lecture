package com.hhpluslecture.lecture.stream;

import com.hhpluslecture.lecture.aggregate.domain.Lecture;
import com.hhpluslecture.lecture.aggregate.event.LectureApplied;
import com.hhpluslecture.lecture.service.LectureApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class LectureApplicationHandler {
    //
    private final LectureApplicationService lectureApplicationService;

    @TransactionalEventListener(phase=TransactionPhase.BEFORE_COMMIT)
    public void on(LectureApplied event) {
        long lectureId = event.getLectureId();
        String userId = event.getUserId();
        Lecture lecture = (Lecture) event.getSource();
        this.lectureApplicationService.apply(lectureId, userId, lecture);
    }
}
