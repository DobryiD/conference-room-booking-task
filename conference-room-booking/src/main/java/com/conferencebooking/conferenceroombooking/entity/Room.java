package com.conferencebooking.conferenceroombooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;



@Entity
@Table(name = "room")
@ApiModel(description = "All details about the Room. ")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated room ID")
    private int id;

    @Size(max = 50)
    @Column(unique = true)
    @NotNull
    @ApiModelProperty(notes = "The rooms name")
    private String name;

    @Size(max = 256)
    @ApiModelProperty(notes = "The rooms location")
    private String location;

    @Max(value = 100)
    @NotNull
    @ApiModelProperty(notes = "The rooms number of seats")
    private Integer numberOfSeats;

    @ApiModelProperty(notes = "Does the room has projector or not")
    private Boolean haveProjector;

    @Size(max = 100)
    @ApiModelProperty(notes = "The rooms phoneNumber")
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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
