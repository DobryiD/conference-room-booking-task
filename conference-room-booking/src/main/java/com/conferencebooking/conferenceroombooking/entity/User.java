package com.conferencebooking.conferenceroombooking.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "system_user")
@ApiModel(description = "All details about the User. ")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated user ID")
    private int id;


    @NotEmpty(message = "is required")
    @Size(min = 1, max = 50)
    @ApiModelProperty(notes = "The user  name")
    private String name;


    @NotEmpty(message = "is required")
    @Size(min = 1, max = 100)
    @ApiModelProperty(notes = "The user surname")
    private String surname;


    @NotEmpty(message = "is required")
    @Size(min = 1, max = 100)
    @Column(unique = true)
    @ApiModelProperty(notes = "The users login")
    private String login;


    @NotEmpty(message = "is required")
    @Size(min = 6, max = 100)
    @ApiModelProperty(notes = "The users password")
    private String password;


    @OneToMany(mappedBy = "theUser", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Booking> bookings;

    public User() {
    }

    public User(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
