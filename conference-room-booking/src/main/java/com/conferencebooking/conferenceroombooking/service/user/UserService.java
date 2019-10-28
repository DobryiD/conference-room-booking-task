package com.conferencebooking.conferenceroombooking.service.user;

import com.conferencebooking.conferenceroombooking.entity.User;
import com.conferencebooking.conferenceroombooking.model.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAvailableUsers();

    void saveTheUser(User theUser);

    void deleteTheUser(String login);

    void updateTheUser(User theUser);

    User getUser(String login);

}
