package com.hhpluslecture.lecture.service.error;

public class CapacityExceededException extends RuntimeException {

    public CapacityExceededException(String message) {
        //
        super(message);
    }
}
