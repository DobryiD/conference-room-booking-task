package com.conferencebooking.conferenceroombooking.exceptions.exceptionhandler;

import com.conferencebooking.conferenceroombooking.error.ErrorResponse;
import com.conferencebooking.conferenceroombooking.exceptions.AccessDeniedException;
import com.conferencebooking.conferenceroombooking.exceptions.NotUniqueLoginException;
import com.conferencebooking.conferenceroombooking.exceptions.NotUniqueRoomNameException;
import com.conferencebooking.conferenceroombooking.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotUniqueLoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleNotUniqueLoginException(NotUniqueLoginException ex) {
        return new ErrorResponse("This login is already taken!");
    }

    @ExceptionHandler(NotUniqueRoomNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleNotUniqueRoomNameException(NotUniqueRoomNameException ex) {
        return new ErrorResponse("This room name is already!");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleAccessDeniedException(AccessDeniedException ex) {
        return new ErrorResponse("Access denied!");
    }

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleRecordNotFoundException(RecordNotFoundException ex) {
        return new ErrorResponse("Record not found!");
    }


}
