package com.infore.common.exception;

@SuppressWarnings("serial")
public class ForbiddenException extends RuntimeException {

    private boolean hasMessage = false;

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
        if (message != null && !message.isEmpty()) {
            this.hasMessage = true;
        }
    }

    public boolean hasMessage() {
        return this.hasMessage;
    }

}
