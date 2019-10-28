package com.conferencebooking.conferenceroombooking.model;

import java.time.LocalDateTime;

public class BookingDTO {

    String roomName;
    String userName;
    String userSurname;
    LocalDateTime startDate;
    LocalDateTime endDate;

    public BookingDTO() {
    }

    public BookingDTO(String roomName, String userName, String userSurname, LocalDateTime startDate, LocalDateTime endDate) {
        this.roomName = roomName;
        this.userName = userName;
        this.userSurname = userSurname;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
