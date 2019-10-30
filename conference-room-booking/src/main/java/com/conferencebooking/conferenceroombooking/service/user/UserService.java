package com.conferencebooking.conferenceroombooking.service.user;

import com.conferencebooking.conferenceroombooking.entity.User;
import com.conferencebooking.conferenceroombooking.model.RequestUser;
import com.conferencebooking.conferenceroombooking.model.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAvailableUsers();

    void saveTheUser(RequestUser theUser);

    void deleteTheUser(RequestUser theUser);

    void updateTheUser(RequestUser theUser);

    User getUser(String login);

}
