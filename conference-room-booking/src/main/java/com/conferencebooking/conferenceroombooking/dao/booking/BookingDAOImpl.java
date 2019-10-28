package com.conferencebooking.conferenceroombooking.dao.booking;

import com.conferencebooking.conferenceroombooking.entity.Booking;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
public class BookingDAOImpl implements BookingDAO {

    private EntityManager entityManager;

    @Autowired
    public BookingDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void bookRoom(Booking booking) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(booking);
    }

    @Override
    public List<Booking> bookingsForTimeFrame(LocalDate from, LocalDate to) {


        return null;
    }

    @Override
    public List<Booking> bookingsForSingleRoom(String name, LocalDate from, LocalDate to) {
        return null;
    }

    @Override
    public List<Booking> bookingsForTheUser(String login, LocalDate from, LocalDate to) {
        return null;
    }
}
