package com.conferencebooking.conferenceroombooking.dao;

import com.conferencebooking.conferenceroombooking.dao.user.UserDAO;
import com.conferencebooking.conferenceroombooking.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserDAOImplTest {


    @Autowired
    private UserDAO userDAO;


    @Test
    public void whenInsert_thenFindUser(){

        User user1=new User("Jack","Moore","jamore","123ert" );
        User user2=new User("Anna","Jans","anjans","534212dfs" );
        User user3=new User("Rolf","Anderson","rolf.ander","hgurjsq" );


        userDAO.saveTheUser(user1);
        userDAO.saveTheUser(user2);
        userDAO.saveTheUser(user3);

        User found1= userDAO.getUser(user1.getLogin());
        User found2= userDAO.getUser(user2.getLogin());
        User found3= userDAO.getUser(user3.getLogin());

        assertThat(found1.getName()).isEqualTo(user1.getName());
        assertThat(found2.getName()).isEqualTo(user2.getName());
        assertThat(found3.getName()).isEqualTo(user3.getName());

    }

    @Test
    public void whenInsert_thenDelete(){

        User user1=new User("Jack","Moore","jamore","123ert" );
        User user2=new User("Anna","Jans","anjans","534212dfs" );
        User user3=new User("Rolf","Anderson","rolf.ander","hgurjsq" );

        userDAO.saveTheUser(user1);
        userDAO.saveTheUser(user2);
        userDAO.saveTheUser(user3);

        userDAO.deleteTheUser(user3.getLogin());

        User found1= userDAO.getUser(user1.getLogin());
        User found2= userDAO.getUser(user2.getLogin());
        User found3= userDAO.getUser(user3.getLogin());

        assertThat(found1).isNotNull();
        assertThat(found2).isNotNull();
        assertThat(found3).isNull();

    }

}
