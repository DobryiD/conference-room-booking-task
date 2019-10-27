package com.conferencebooking.conferenceroombooking.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(max =50)
    @Column(unique = true)
    @NotNull()
    private String name;

    @Size(max =256)
    private String location;

    @Max(value=100)
    @NotNull()
    private int numberOfSeats;

    @Column(columnDefinition = "boolean default false")
    private boolean haveProjector;
    @Size(max =100)
    private String phoneNumber;

    public Room() {
    }

    public Room( String name,  String location,  int numberOfSeats, boolean haveProjector,  String phoneNumber) {
        this.name = name;
        this.location = location;
        this.numberOfSeats = numberOfSeats;
        this.haveProjector = haveProjector;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public boolean isHaveProjector() {
        return haveProjector;
    }

    public void setHaveProjector(boolean haveProjector) {
        this.haveProjector = haveProjector;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", haveProjector=" + haveProjector +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
