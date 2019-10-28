package com.conferencebooking.conferenceroombooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(max = 50)
    @Column(unique = true)
    @NotNull
    private String name;

    @Size(max = 256)
    private String location;

    @Max(value = 100)
    @NotNull
    private Integer numberOfSeats;

    private Boolean haveProjector=false;

    @Size(max = 100)
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "theRoom",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Booking> bookings;


    public Room() {

    }

    public Room(String name, String location, Integer numberOfSeats, Boolean haveProjector, String phoneNumber) {
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

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Boolean isHaveProjector() {
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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
