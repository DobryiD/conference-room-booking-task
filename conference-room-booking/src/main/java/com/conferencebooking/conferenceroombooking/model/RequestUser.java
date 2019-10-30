package com.conferencebooking.conferenceroombooking.model;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RequestUser {

    private String adminKey;

    @NotEmpty(message = "is required")
    @Size(min = 1, max = 50)
    private String name;

    @NotEmpty(message = "is required")
    @Size(min = 1, max = 100)
    private String surname;

    @NotEmpty(message = "is required")
    @Size(min = 1, max = 100)
    private String login;

    @NotEmpty(message = "is required")
    @Size(min = 6, max = 100)
    private String password;




    public RequestUser() {
    }

    public RequestUser(String adminKey, String name, String surname, String login, String password) {
        this.adminKey = adminKey;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public String getAdminKey() {
        return adminKey;
    }

    public void setAdminKey(String adminKey) {
        this.adminKey = adminKey;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
