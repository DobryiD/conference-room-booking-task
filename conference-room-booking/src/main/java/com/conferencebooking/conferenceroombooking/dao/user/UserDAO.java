package com.conferencebooking.conferenceroombooking.dao.user;

import com.conferencebooking.conferenceroombooking.entity.Room;
import com.conferencebooking.conferenceroombooking.entity.User;

import java.util.List;

public interface UserDAO {

    List<User> getAvailableUsers();

    void saveTheUser(User theUser);

    void deleteTheUser(String login);

    User getUser(String login);


}
