package com.conferencebooking.conferenceroombooking.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "is required")
    @Size(min = 1, max = 50)
    private String name;

    @NotNull(message = "is required")
    @Size(min = 1,max = 100)
    private String surname;

    @NotNull(message = "is required")
    @Size(min = 1,max = 100)
    @Column(unique = true)
    private String login;

    @NotNull(message = "is required")
    @Size(min = 6,max = 100)
    private String password;

    public User() {
    }

    public User( String name,  String surname,  String login,  String password) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
