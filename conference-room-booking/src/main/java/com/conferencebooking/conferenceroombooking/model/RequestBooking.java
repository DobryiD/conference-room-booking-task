package com.conferencebooking.conferenceroombooking.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RequestBooking {

    private String roomName;

    private String login;
    private String password;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public RequestBooking() {
    }

    public RequestBooking(String roomName, String login, String password, String startDate, String endDate) {
        this.roomName = roomName;
        this.login = login;
        this.password = password;

        if(startDate!=null) {
            this.startDate = LocalDateTime.parse(startDate, formatter);
        }
        if(endDate!=null) {
            this.endDate = LocalDateTime.parse(endDate, formatter);
        }
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        if(startDate!=null) {
            this.startDate = LocalDateTime.parse(startDate, formatter);
        }
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        if(endDate!=null) {
            this.endDate = LocalDateTime.parse(endDate, formatter);
        }
    }

    public boolean isDateTimeCorrect(){
        return startDate.isBefore(endDate);
    }
}
