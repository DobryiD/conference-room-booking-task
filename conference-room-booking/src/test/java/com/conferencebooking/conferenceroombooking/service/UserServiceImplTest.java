package com.conferencebooking.conferenceroombooking.service;


import com.conferencebooking.conferenceroombooking.dao.user.UserDAO;
import com.conferencebooking.conferenceroombooking.entity.User;
import com.conferencebooking.conferenceroombooking.exceptions.NotUniqueLoginException;
import com.conferencebooking.conferenceroombooking.exceptions.RecordNotFoundException;
import com.conferencebooking.conferenceroombooking.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserServiceImplTest {


    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserServiceImpl userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = NotUniqueLoginException.class)
    public void saveTheUser(){
        User user1=new User("Mo","shang","mosh","534212dfs" );
        userService.saveTheUser(user1);

        User user2=new User("Monica","Shanel","mosh","53sdfsf" );
        userService.saveTheUser(user2);
    }

    @Test(expected = RecordNotFoundException.class)
    public void getUser(){
        User user1=new User("Mo","shang","mosh","534212dfs" );
        userService.saveTheUser(user1);

        User foundUser1 =userService.getUser("Mosh");
        User foundUser2=userService.getUser(user1.getLogin());
        assertThat(foundUser1).isNull();
        assertThat(foundUser2).isNotNull();
    }

    @Test
    public void updateTheUser() {

        String initialName="Jack";
        String initialSurname="Moore";
        String initialLogin="jamore";
        String initialPass="123ert";
        User initialUser=new User(initialName,initialSurname,initialLogin,initialPass);
        userService.saveTheUser(initialUser);

        assertThat(userService.getUser(initialUser.getLogin())).isNotNull();

        User userUpdate2=new User("Mo",null,"jamore","534212dfs" );

        userService.updateTheUser(userUpdate2);
        User changedUser= userService.getUser(initialUser.getLogin());
        assertThat(changedUser.getLogin()).isEqualTo(initialLogin);
        assertThat(changedUser.getName()).isNotEqualTo(initialName);
        assertThat(changedUser.getSurname()).isEqualTo(initialSurname);
        assertThat(changedUser.getPassword()).isNotEqualTo(initialPass);


        User userUpdate3=new User("Jack",null,"jamore",null );
        userService.updateTheUser(userUpdate3);

        changedUser= userService.getUser(initialUser.getLogin());
        assertThat(changedUser.getLogin()).isEqualTo(initialLogin);
        assertThat(changedUser.getName()).isEqualTo(initialName);
        assertThat(changedUser.getSurname()).isEqualTo(initialSurname);
        assertThat(changedUser.getPassword()).isNotEqualTo(initialPass);
    }



}
