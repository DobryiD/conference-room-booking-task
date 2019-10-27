package com.conferencebooking.conferenceroombooking.dao;

import com.conferencebooking.conferenceroombooking.entity.User;

import java.util.List;

public interface ApplicationDAO {

    List<User> getAvailableUsers();
    void saveTheUser(User theUser);
    void deleteTheUser(String login);
    User getUser(String login);
}
