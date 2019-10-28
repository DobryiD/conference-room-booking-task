package com.conferencebooking.conferenceroombooking.exceptions;

public class NotUniqueRoomNameException extends RuntimeException {
    public NotUniqueRoomNameException() {
    }

    public NotUniqueRoomNameException(String message) {
        super(message);
    }

    public NotUniqueRoomNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotUniqueRoomNameException(Throwable cause) {
        super(cause);
    }
}
