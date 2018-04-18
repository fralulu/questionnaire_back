package com.infore.common.exception;

public class ForbiddenResponse {

    private int code;
    private String message;

    public ForbiddenResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
