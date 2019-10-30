package com.conferencebooking.conferenceroombooking.dao;


import com.conferencebooking.conferenceroombooking.dao.booking.BookingDAO;
import com.conferencebooking.conferenceroombooking.dao.room.RoomDAO;
import com.conferencebooking.conferenceroombooking.dao.user.UserDAO;
import com.conferencebooking.conferenceroombooking.entity.Booking;
import com.conferencebooking.conferenceroombooking.entity.Room;
import com.conferencebooking.conferenceroombooking.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class BookingDAOImplTest {

    @Autowired
    private BookingDAO bookingDAO;
    @Autowired
    private RoomDAO roomDAO;
    @Autowired
    private UserDAO userDAO;

    @Test
    public void bookRoom() {


        User user=userDAO.getUser("jsmith");
        Room room=roomDAO.getRoom("Medium Room");

        LocalDateTime start=LocalDateTime.of(2013,9,13,13,00,00);
        LocalDateTime end=LocalDateTime.of(2014,9,13,13,00,00);
        Booking booking=new Booking(user,room,start, end);

        bookingDAO.bookRoom(booking);

        List<Booking> bookingList=bookingDAO.bookingsForSingleRoom(room,start,end);
        assertThat(bookingList.size()).isNotEqualTo(0);
    }

    @Test
    public void bookingsForTimeFrame() {
        User user=userDAO.getUser("jsmith");
        Room room=roomDAO.getRoom("Medium Room");


        Booking booking=new Booking(user,room,LocalDateTime.of(2013,9,13,13,00,00),
                LocalDateTime.of(2014,9,13,13,00,00));
        bookingDAO.bookRoom(booking);

        booking =new Booking(user,room,LocalDateTime.of(2000,4,10,00,00,00),
                LocalDateTime.of(2001,4,10,00,00,00));
        bookingDAO.bookRoom(booking);



        List<Booking> bookingList=bookingDAO.bookingsForTimeFrame(LocalDateTime.of(2013,9,13,13,00,00),
                LocalDateTime.of(2014,9,13,13,00,00));
        assertThat(bookingList.size()).isEqualTo(1);

        bookingList=bookingDAO.bookingsForTimeFrame(LocalDateTime.of(2000,4,10,00,00,00),
                LocalDateTime.of(2014,9,13,13,00,00));
        assertThat(bookingList.size()).isEqualTo(2);

        bookingList=bookingDAO.bookingsForTimeFrame(LocalDateTime.of(2003,4,10,00,00,00),
                LocalDateTime.of(205,9,13,13,00,00));
        assertThat(bookingList.size()).isEqualTo(0);

    }

    @Test
    public void bookingsForSingleRoom() {
        User user=userDAO.getUser("jsmith");
        Room room=roomDAO.getRoom("Medium Room");


        Booking booking=new Booking(user,room,LocalDateTime.of(2013,9,13,13,00,00),
                LocalDateTime.of(2014,9,13,13,00,00));
        bookingDAO.bookRoom(booking);

        booking =new Booking(user,room,LocalDateTime.of(2000,4,10,00,00,00),
                LocalDateTime.of(2001,4,10,00,00,00));
        bookingDAO.bookRoom(booking);



        List<Booking> bookingList=bookingDAO.bookingsForSingleRoom(room,LocalDateTime.of(2013,9,13,13,00,00),
                LocalDateTime.of(2014,9,13,13,00,00));

        assertThat(bookingList.size()).isEqualTo(1);

        bookingList=bookingDAO.bookingsForSingleRoom(room,null,
                null);
        assertThat(bookingList.size()).isEqualTo(0);

        bookingList=bookingDAO.bookingsForSingleRoom(room,LocalDateTime.of(2000,2,13,13,00,00),
                LocalDateTime.of(2016,9,13,13,00,00));

        assertThat(bookingList.size()).isEqualTo(2);

        bookingList=bookingDAO.bookingsForSingleRoom(room,LocalDateTime.of(2005,9,13,13,00,00),
                LocalDateTime.of(2006,9,13,13,00,00));

        assertThat(bookingList.size()).isEqualTo(0);

    }

    @Test
    public void bookingsForTheUser() {
        User user=userDAO.getUser("jsmith");
        Room room=roomDAO.getRoom("Medium Room");


        Booking booking=new Booking(user,room,LocalDateTime.of(2013,9,13,13,00,00),
                LocalDateTime.of(2014,9,13,13,00,00));
        bookingDAO.bookRoom(booking);

        booking =new Booking(user,room,LocalDateTime.of(2000,4,10,00,00,00),
                LocalDateTime.of(2001,4,10,00,00,00));
        bookingDAO.bookRoom(booking);


        List<Booking> bookingList=bookingDAO.bookingsForTheUser(user,LocalDateTime.of(2013,9,13,13,00,00),
                LocalDateTime.of(2014,9,13,13,00,00));

        assertThat(bookingList.size()).isEqualTo(1);

        bookingList=bookingDAO.bookingsForTheUser(user,null,
                null);
        assertThat(bookingList.size()).isEqualTo(0);

        bookingList=bookingDAO.bookingsForTheUser(user,LocalDateTime.of(2000,2,13,13,00,00),
                LocalDateTime.of(2016,9,13,13,00,00));

        assertThat(bookingList.size()).isEqualTo(2);

        bookingList=bookingDAO.bookingsForTheUser(user,LocalDateTime.of(2005,9,13,13,00,00),
                LocalDateTime.of(2006,9,13,13,00,00));

        assertThat(bookingList.size()).isEqualTo(0);
    }

    @Test
    public void getAllBookings() {
        User user=userDAO.getUser("jsmith");
        Room room=roomDAO.getRoom("Medium Room");

        Booking booking=new Booking(user,room,LocalDateTime.of(2013,9,13,13,00,00),
                LocalDateTime.of(2014,9,13,13,00,00));
        bookingDAO.bookRoom(booking);

        booking =new Booking(user,room,LocalDateTime.of(2000,4,10,00,00,00),
                LocalDateTime.of(2001,4,10,00,00,00));
        bookingDAO.bookRoom(booking);

        List<Booking> bookingList=bookingDAO.getAllBookings(null,null);
        assertThat(bookingList.size()).isEqualTo(2);

    }

    @Test
    public void getAllBookingsForUser() {
        User user=userDAO.getUser("jsmith");
        Room room=roomDAO.getRoom("Medium Room");

        Booking booking=new Booking(user,room,LocalDateTime.of(2013,9,13,13,00,00),
                LocalDateTime.of(2014,9,13,13,00,00));
        bookingDAO.bookRoom(booking);

        booking =new Booking(user,room,LocalDateTime.of(2000,4,10,00,00,00),
                LocalDateTime.of(2001,4,10,00,00,00));
        bookingDAO.bookRoom(booking);

        List<Booking> bookingList=bookingDAO.getAllBookingsForUser(user ,null,null);
        assertThat(bookingList.size()).isEqualTo(2);
    }

    @Test
    public void getAllBookingsForRoom() {
        User user=userDAO.getUser("jsmith");
        Room room=roomDAO.getRoom("Medium Room");

        Booking booking=new Booking(user,room,LocalDateTime.of(2013,9,13,13,00,00),
                LocalDateTime.of(2014,9,13,13,00,00));
        bookingDAO.bookRoom(booking);

        booking =new Booking(user,room,LocalDateTime.of(2000,4,10,00,00,00),
                LocalDateTime.of(2001,4,10,00,00,00));
        bookingDAO.bookRoom(booking);

        List<Booking> bookingList=bookingDAO.getAllBookingsForRoom(room,null,null);
        assertThat(bookingList.size()).isEqualTo(2);
    }
}
