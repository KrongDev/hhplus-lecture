package com.hhpluslecture.error;

public interface ErrorCode {
    //
    int status = 0;
    String code = null;
    String description = null;

    public int getStatus();
    public String getCode();
    public String getDescription();
}
