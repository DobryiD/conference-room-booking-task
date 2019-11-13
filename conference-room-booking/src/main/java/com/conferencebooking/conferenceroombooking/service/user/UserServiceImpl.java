package com.conferencebooking.conferenceroombooking.service.user;

import com.conferencebooking.conferenceroombooking.dao.user.UserDAO;
import com.conferencebooking.conferenceroombooking.entity.User;
import com.conferencebooking.conferenceroombooking.exceptions.AccessDeniedException;
import com.conferencebooking.conferenceroombooking.exceptions.NotUniqueLoginException;
import com.conferencebooking.conferenceroombooking.exceptions.RecordNotFoundException;
import com.conferencebooking.conferenceroombooking.model.RequestUser;
import com.conferencebooking.conferenceroombooking.model.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:admin-file.properties")
public class UserServiceImpl implements UserService {

    @Value("${adminkey}")
    private String securityKey;

    private UserDAO userDAO;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserDAO theUserDAO) {
        userDAO = theUserDAO;
        modelMapper = new ModelMapper();
    }


    @Override

    public List<UserDTO> getAvailableUsers() {

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
    public void saveTheUser( RequestUser theUser) {
        if(securityKey.equals(theUser.getAdminKey())){
           User basicUser = modelMapper.map(theUser, User.class);

            User userForCheck = userDAO.getUser(basicUser.getLogin());
            if (userForCheck == null)
                userDAO.saveTheUser(basicUser);
            else
                throw new NotUniqueLoginException();
        } else {
            throw new AccessDeniedException("Access denied!");
        }


    }

    @Override
    @Transactional
    public void deleteTheUser(RequestUser theUser) {
        if(securityKey.equals(theUser.getAdminKey())){
            User userForCheck = getUser(theUser.getLogin());

            if (userForCheck != null)
                userDAO.deleteTheUser(theUser.getLogin());
        } else {
            throw new AccessDeniedException("Access denied!");
        }
    }


    @Override
    @Transactional
    public void updateTheUser(RequestUser theUser) {

        if(securityKey.equals(theUser.getAdminKey())){
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
        } else {
            throw new AccessDeniedException("Access denied!");
        }
    }

    @Override
    public User getUser(String login) {

        User user = userDAO.getUser(login);
        if (user != null) {
            return user;
        } else {
            throw new RecordNotFoundException("Such user has not been found!");
        }
    }

}
