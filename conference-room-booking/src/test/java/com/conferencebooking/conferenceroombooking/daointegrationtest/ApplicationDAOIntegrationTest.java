package com.conferencebooking.conferenceroombooking.daointegrationtest;

import com.conferencebooking.conferenceroombooking.dao.ApplicationDAO;
import com.conferencebooking.conferenceroombooking.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ApplicationDAOIntegrationTest {


    @Autowired
    private ApplicationDAO applicationDAO;


    @Test
    public void whenInsert_thenFindUser(){

        User user1=new User("Jack","Moore","jamore","123ert" );
        User user2=new User("Anna","Jans","anjans","534212dfs" );
        User user3=new User("Rolf","Anderson","rolf.ander","hgurjsq" );


        applicationDAO.saveTheUser(user1);
        applicationDAO.saveTheUser(user2);
        applicationDAO.saveTheUser(user3);

        User found1=applicationDAO.getUser(user1.getLogin());
        User found2=applicationDAO.getUser(user2.getLogin());
        User found3=applicationDAO.getUser(user3.getLogin());

        assertThat(found1.getName()).isEqualTo(user1.getName());
        assertThat(found2.getName()).isEqualTo(user2.getName());
        assertThat(found3.getName()).isEqualTo(user3.getName());

    }

    @Test
    public void whenInsert_thenDelete(){

        User user1=new User("Jack","Moore","jamore","123ert" );
        User user2=new User("Anna","Jans","anjans","534212dfs" );
        User user3=new User("Rolf","Anderson","rolf.ander","hgurjsq" );

        applicationDAO.saveTheUser(user1);
        applicationDAO.saveTheUser(user2);
        applicationDAO.saveTheUser(user3);

        applicationDAO.deleteTheUser(user3.getLogin());

        User found1=applicationDAO.getUser(user1.getLogin());
        User found2=applicationDAO.getUser(user2.getLogin());
        User found3=applicationDAO.getUser(user3.getLogin());

        assertThat(found1).isNotNull();
        assertThat(found2).isNotNull();
        assertThat(found3).isNull();

    }
}
