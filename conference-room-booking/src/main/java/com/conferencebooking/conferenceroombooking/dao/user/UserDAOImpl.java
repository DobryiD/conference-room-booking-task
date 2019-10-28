package com.conferencebooking.conferenceroombooking.dao.user;

import com.conferencebooking.conferenceroombooking.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<User> getAvailableUsers() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery(" from User", User.class);
        return (List<User>) theQuery.getResultList();
    }

    @Override
    @Transactional
    public void saveTheUser(User theUser) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(theUser);
    }

    @Override
    @Transactional
    public void deleteTheUser(String login) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from User where login=:userLogin");
        theQuery.setParameter("userLogin", login);

        theQuery.executeUpdate();
    }


    @Override
    @Transactional
    public User getUser(String login) {
        Session theSession = entityManager.unwrap(Session.class);

        Query theQuery = theSession.createQuery("from User where login=:userLogin");
        theQuery.setParameter("userLogin", login);

        List<User> users = (List<User>) theQuery.getResultList();
        if (users.size() <= 0)
            return null;
        else
            return users.get(0);

    }


}
