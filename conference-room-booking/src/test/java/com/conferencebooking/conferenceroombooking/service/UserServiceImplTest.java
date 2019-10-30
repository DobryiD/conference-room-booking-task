package com.conferencebooking.conferenceroombooking.service;


import com.conferencebooking.conferenceroombooking.dao.user.UserDAO;
import com.conferencebooking.conferenceroombooking.entity.User;
import com.conferencebooking.conferenceroombooking.exceptions.AccessDeniedException;
import com.conferencebooking.conferenceroombooking.exceptions.NotUniqueLoginException;
import com.conferencebooking.conferenceroombooking.exceptions.RecordNotFoundException;
import com.conferencebooking.conferenceroombooking.model.RequestUser;
import com.conferencebooking.conferenceroombooking.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@PropertySource("classpath:admin-file.properties")
public class UserServiceImplTest {

    @Value("${adminkey}")
    private String adminKey;



    @Autowired
    private UserServiceImpl userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveTheUser(){
        RequestUser user=new RequestUser(adminKey,"Mo","shang","mosh","534212dfs" );
        userService.saveTheUser(user);

        assertThat(userService.getUser(user.getLogin())).isNotNull();

    }

    @Test(expected = NotUniqueLoginException.class )
    public  void whenSaveThanNotUnique(){
        RequestUser user=new RequestUser(adminKey,"Mo","shang","mosh","534212dfs" );
        userService.saveTheUser(user);

        RequestUser userWithNotUnique=new RequestUser(adminKey,"Monica","Shanel","mosh","53sdfsf" );
        userService.saveTheUser(userWithNotUnique);
    }

    @Test(expected = AccessDeniedException.class )
    public  void whenSaveThanAccessDenied(){
        RequestUser user=new RequestUser(adminKey,"Mo","shang","mosh","534212dfs" );
        userService.saveTheUser(user);



        RequestUser userWithNoAccess=new RequestUser(adminKey+"asd","Monica","Shanel","monel","53sdfsf" );
        userService.saveTheUser(userWithNoAccess);
    }

    @Test
    public void whenSaveThenCheckContent(){
        RequestUser user=new RequestUser(adminKey,"Mo","shang","mosh","534212dfs" );
        userService.saveTheUser(user);

        User storedUser=userService.getUser(user.getLogin());

        assertThat(storedUser.getName()).isEqualTo(user.getName());
        assertThat(storedUser.getSurname()).isEqualTo(user.getSurname());
        assertThat(storedUser.getPassword()).isEqualTo(user.getPassword());

    }

    @Test(expected = RecordNotFoundException.class)
    public void whenSearchThenRecordNotFound(){

        RequestUser user=new RequestUser(adminKey,"Mo","shang","mosh","534212dfs" );
        userService.saveTheUser(user);

        User foundUser1 =userService.getUser("Mosh");
        User foundUser2=userService.getUser(user.getLogin());
        assertThat(foundUser1).isNull();
        assertThat(foundUser2).isNotNull();
    }

    @Test
    public void updateTheUser() {

        String initialName="Jack";
        String initialSurname="Moore";
        String initialLogin="jamore";
        String initialPass="123ert";
        RequestUser initialUser=new RequestUser(adminKey,initialName,initialSurname,initialLogin,initialPass);
        userService.saveTheUser(initialUser);

        assertThat(userService.getUser(initialUser.getLogin())).isNotNull();

        RequestUser userUpdate2=new RequestUser(adminKey,"Mo",null,"jamore","534212dfs" );

        userService.updateTheUser(userUpdate2);
        User changedUser= userService.getUser(initialUser.getLogin());
        assertThat(changedUser.getLogin()).isEqualTo(initialLogin);
        assertThat(changedUser.getName()).isNotEqualTo(initialName);
        assertThat(changedUser.getSurname()).isEqualTo(initialSurname);
        assertThat(changedUser.getPassword()).isNotEqualTo(initialPass);


        RequestUser userUpdate3=new RequestUser(adminKey,"Jack",null,"jamore",null );
        userService.updateTheUser(userUpdate3);

        changedUser= userService.getUser(initialUser.getLogin());
        assertThat(changedUser.getLogin()).isEqualTo(initialLogin);
        assertThat(changedUser.getName()).isEqualTo(initialName);
        assertThat(changedUser.getSurname()).isEqualTo(initialSurname);
        assertThat(changedUser.getPassword()).isNotEqualTo(initialPass);
    }



}
