package com.conferencebooking.conferenceroombooking.service;

import com.conferencebooking.conferenceroombooking.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAvailableUsers();
    void saveTheUser(User theUser);
    void deleteTheUser(String login);
    void updateTheUser(String login,String name,String surname,String password);
    User getUser(String login);

}
