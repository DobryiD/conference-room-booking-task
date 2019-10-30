package com.conferencebooking.conferenceroombooking.exceptions;

public class IncorrectDataException extends RuntimeException {
    public IncorrectDataException() {
    }

    public IncorrectDataException(String message) {
        super(message);
    }

    public IncorrectDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDataException(Throwable cause) {
        super(cause);
    }
}
