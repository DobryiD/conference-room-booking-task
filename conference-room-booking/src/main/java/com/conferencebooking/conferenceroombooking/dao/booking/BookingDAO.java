package com.conferencebooking.conferenceroombooking.dao.booking;

import com.conferencebooking.conferenceroombooking.entity.Booking;
import com.conferencebooking.conferenceroombooking.entity.Room;
import com.conferencebooking.conferenceroombooking.entity.User;


import java.time.LocalDateTime;
import java.util.List;

public interface BookingDAO {

    void bookRoom(Booking booking);

    List<Booking> bookingsForTimeFrame(LocalDateTime fromDate, LocalDateTime toDate);

    List<Booking> bookingsForSingleRoom(Room room, LocalDateTime fromDate, LocalDateTime toDate);

    List<Booking> bookingsForTheUser(User user, LocalDateTime fromDate, LocalDateTime toDate);

    List<Booking> getAllBookings(LocalDateTime from, LocalDateTime to);

    List<Booking> getAllBookingsForUser(User user,LocalDateTime fromDate, LocalDateTime toDate);

    List<Booking> getAllBookingsForRoom(Room room,LocalDateTime fromDate, LocalDateTime toDate);


}
