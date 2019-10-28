package com.conferencebooking.conferenceroombooking.exceptions;

public class NotUniqueLoginException extends RuntimeException {

    public NotUniqueLoginException() {
    }

    public NotUniqueLoginException(String message) {
        super(message);
    }

    public NotUniqueLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotUniqueLoginException(Throwable cause) {
        super(cause);
    }

    public NotUniqueLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
