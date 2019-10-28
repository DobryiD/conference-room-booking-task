package com.conferencebooking.conferenceroombooking.service.user;

import com.conferencebooking.conferenceroombooking.dao.user.UserDAO;
import com.conferencebooking.conferenceroombooking.entity.User;
import com.conferencebooking.conferenceroombooking.exceptions.NotUniqueLoginException;
import com.conferencebooking.conferenceroombooking.exceptions.RecordNotFoundException;
import com.conferencebooking.conferenceroombooking.model.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO theUserDAO) {
        userDAO = theUserDAO;

    }


    @Override
    @Transactional
    public List<UserDTO> getAvailableUsers() {

        ModelMapper modelMapper = new ModelMapper();

        List<User> storedUsers = userDAO.getAvailableUsers();
        List<UserDTO> mappedUsers = new ArrayList<>();

        for (User user : storedUsers) {
            UserDTO returnUser = modelMapper.map(user, UserDTO.class);
            mappedUsers.add(returnUser);
        }

        return mappedUsers;
    }

    @Override
    @Transactional
    public void saveTheUser(User theUser) {
        User userForCheck = userDAO.getUser(theUser.getLogin());
        if (userForCheck == null)
            userDAO.saveTheUser(theUser);
        else
            throw new NotUniqueLoginException();

    }

    @Override
    @Transactional
    public void deleteTheUser(String login) {
        User userForCheck = getUser(login);

        if (userForCheck != null)
            userDAO.deleteTheUser(login);
    }


    @Override
    @Transactional
    public void updateTheUser(User theUser) {

        User userForCheck = getUser(theUser.getLogin());

        if (theUser.getName() != null) {
            userForCheck.setName(theUser.getName());
        }
        if (theUser.getSurname() != null) {
            userForCheck.setSurname(theUser.getSurname());
        }
        if (theUser.getPassword() != null) {
            userForCheck.setPassword(theUser.getPassword());
        }

        userDAO.saveTheUser(userForCheck);
    }

    @Override
    public User getUser(String login) {

        User user = userDAO.getUser(login);
        if (user != null) {
            return user;
        } else {
            throw new RecordNotFoundException();
        }

    }


}
