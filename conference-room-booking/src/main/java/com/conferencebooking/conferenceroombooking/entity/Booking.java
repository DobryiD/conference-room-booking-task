package com.conferencebooking.conferenceroombooking.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User theUser;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room theRoom;

    private LocalDate startDate;

    private LocalDate endDate;

    public Booking() {
    }

    public Booking(User theUser, Room theRoom, LocalDate startDate, LocalDate endDate) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
