package com.conferencebooking.conferenceroombooking.service;

import com.conferencebooking.conferenceroombooking.dao.ApplicationDAO;
import com.conferencebooking.conferenceroombooking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class UserServiceImpl implements UserService {




    private ApplicationDAO applicationDAO;

    @Autowired
    public UserServiceImpl(ApplicationDAO theApplicationDAO){
        applicationDAO=theApplicationDAO;
    }

    @Override
    @Transactional
    public List<User> getAvailableUsers() {
        return applicationDAO.getAvailableUsers();
    }

    @Override
    @Transactional
    public void saveTheUser(User theUser) {
        applicationDAO.saveTheUser(theUser);
    }

    @Override
    @Transactional
    public void deleteTheUser(String login) {
        applicationDAO.deleteTheUser(login);
    }


    @Override
    @Transactional
    public void updateTheUser(String login, String name, String surname, String password) {
        User storedUser=applicationDAO.getUser(login);
        if(storedUser==null){
            throw new RuntimeException("User is not found!");
        }
        if(name!=null){
            storedUser.setName(name);
        }
        if(surname!=null){
            storedUser.setSurname(surname);
        }
        if(password!=null){
            storedUser.setPassword(password);
        }
        applicationDAO.saveTheUser(storedUser);
    }

    @Override
    public User getUser(String login) {
        return applicationDAO.getUser(login);
    }


}
