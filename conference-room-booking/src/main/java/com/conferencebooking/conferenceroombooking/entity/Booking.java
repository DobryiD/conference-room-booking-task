package com.conferencebooking.conferenceroombooking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "booking")
@ApiModel(description = "All details about the Booking. ")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated booking ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ApiModelProperty(notes = "The booking reference for user who have done booking")
    private User theUser;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @ApiModelProperty(notes = "The booking reference for booked room")
    private Room theRoom;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(notes = "The start date of booking")
    private LocalDateTime startDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(notes = "The end date of booking")
    private LocalDateTime endDate;

    public Booking() {
    }

    public Booking(User theUser, Room theRoom, LocalDateTime startDate, LocalDateTime endDate) {
        this.theUser = theUser;
        this.theRoom = theRoom;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getTheUser() {
        return theUser;
    }

    public void setTheUser(User theUser) {
        this.theUser = theUser;
    }

    public Room getTheRoom() {
        return theRoom;
    }

    public void setTheRoom(Room theRoom) {
        this.theRoom = theRoom;
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
