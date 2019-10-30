package com.conferencebooking.conferenceroombooking.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RequestRoom {

    private String adminKey;

    @Size(max = 50)
    @NotNull
    private String name;

    @Size(max = 256)
    private String location;

    @Max(value = 100)
    @NotNull
    private Integer numberOfSeats;


    private Boolean haveProjector;

    @Size(max = 100)
    private String phoneNumber;

    public RequestRoom() {
    }

    public RequestRoom(String adminKey, String name, String location, Integer numberOfSeats, Boolean haveProjector, String phoneNumber) {
        this.adminKey=adminKey;
        this.name = name;
        this.location = location;
        this.numberOfSeats = numberOfSeats;
        this.haveProjector = haveProjector;
        this.phoneNumber = phoneNumber;
    }

    public String getAdminKey() {
        return adminKey;
    }

    public void setAdminKey(String adminKey) {
        this.adminKey = adminKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Boolean getHaveProjector() {
        return haveProjector;
    }

    public void setHaveProjector(Boolean haveProjector) {
        this.haveProjector = haveProjector;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
