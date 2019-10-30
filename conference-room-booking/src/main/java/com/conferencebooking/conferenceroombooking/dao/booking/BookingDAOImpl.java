package com.conferencebooking.conferenceroombooking.dao.booking;

import com.conferencebooking.conferenceroombooking.entity.Booking;
import com.conferencebooking.conferenceroombooking.entity.Room;
import com.conferencebooking.conferenceroombooking.entity.User;
import com.conferencebooking.conferenceroombooking.model.RequestBooking;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
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
    public List<Booking> bookingsForTimeFrame(LocalDateTime fromDate, LocalDateTime toDate) {
        Session currentSession=entityManager.unwrap(Session.class);

        Query theQuery=currentSession.createQuery("from Booking where startDate>=:fromDate and endDate<=:toDate");
        theQuery.setParameter("fromDate", fromDate);
        theQuery.setParameter("toDate", toDate);

        return (List<Booking>)theQuery.getResultList();
    }

    @Override
    public List<Booking> bookingsForSingleRoom(Room room, LocalDateTime fromDate, LocalDateTime toDate) {
        Session currentSession=entityManager.unwrap(Session.class);

        Query theQuery=currentSession.createQuery("from Booking where theRoom.id=:room and startDate>=:fromDate and endDate<=:toDate");

        theQuery.setParameter("room",room.getId());
        theQuery.setParameter("fromDate", fromDate);
        theQuery.setParameter("toDate", toDate);

        return (List<Booking>)theQuery.getResultList();
    }

    @Override
    public List<Booking> bookingsForTheUser(User user, LocalDateTime fromDate, LocalDateTime toDate) {
        Session currentSession=entityManager.unwrap(Session.class);

        Query theQuery=currentSession.createQuery("from Booking where theUser.id=:user and startDate>=:fromDate and endDate<=:toDate");

        theQuery.setParameter("user",user.getId());
        theQuery.setParameter("fromDate", fromDate);
        theQuery.setParameter("toDate", toDate);

        return (List<Booking>)theQuery.getResultList();
    }


    @Override
    public List<Booking> getAllBookings(LocalDateTime fromDate, LocalDateTime toDate){
        Session currentSession = entityManager.unwrap(Session.class);
        if(fromDate==null&&toDate!=null){
            Query theQuery = currentSession.createQuery(" from Booking where endDate<=:toDate");
            theQuery.setParameter("toDate", toDate);
            return (List<Booking>) theQuery.getResultList();
        }
        else if(fromDate!=null&&toDate==null){
            Query theQuery = currentSession.createQuery(" from Booking where startDate>=:fromDate");
            theQuery.setParameter("fromDate", fromDate);
            return (List<Booking>) theQuery.getResultList();
        }
        else {
            Query theQuery = currentSession.createQuery(" from Booking", Booking.class);
            return (List<Booking>) theQuery.getResultList();
        }
    }

    @Override
    public List<Booking> getAllBookingsForUser(User user,LocalDateTime fromDate, LocalDateTime toDate) {
        Session currentSession=entityManager.unwrap(Session.class);
        if(fromDate==null&&toDate!=null) {
            Query theQuery = currentSession.createQuery("from Booking where theUser.id=:user and endDate<=:toDate");
            theQuery.setParameter("user", user.getId());
            theQuery.setParameter("toDate", toDate);
            return (List<Booking>) theQuery.getResultList();
        }
        else if(fromDate!=null&&toDate==null){
            Query theQuery = currentSession.createQuery("from Booking where theUser.id=:user and startDate>=:fromDate");
            theQuery.setParameter("user", user.getId());
            theQuery.setParameter("fromDate", fromDate);
            return (List<Booking>) theQuery.getResultList();
        }else{
            Query theQuery = currentSession.createQuery(" from Booking where theUser.id=:user");
            theQuery.setParameter("user", user.getId());
            return (List<Booking>) theQuery.getResultList();
        }


    }

    @Override
    public List<Booking> getAllBookingsForRoom(Room room,LocalDateTime fromDate, LocalDateTime toDate) {
        Session currentSession=entityManager.unwrap(Session.class);


        if(fromDate==null&&toDate!=null) {
            Query theQuery = currentSession.createQuery("from Booking where theRoom.id=:room and endDate<=:toDate");
            theQuery.setParameter("room",room.getId());
            theQuery.setParameter("toDate", toDate);
            return (List<Booking>) theQuery.getResultList();
        }
        else if(fromDate!=null&&toDate==null){
            Query theQuery = currentSession.createQuery("from Booking where theRoom.id=:room and startDate>=:fromDate");
            theQuery.setParameter("room",room.getId());
            theQuery.setParameter("fromDate", fromDate);
            return (List<Booking>) theQuery.getResultList();
        }else{
            Query theQuery = currentSession.createQuery(" from Booking where theRoom.id=:room");
            theQuery.setParameter("room",room.getId());
            return (List<Booking>) theQuery.getResultList();
        }


    }
}
