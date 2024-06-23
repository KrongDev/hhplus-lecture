package com.hhpluslecture.error;

import lombok.Getter;

@Getter
public class GlobalCustomException extends RuntimeException{
    //
    private final ErrorCode errorCode;

    public GlobalCustomException(ErrorCode errorCode) {
        //
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }
}
