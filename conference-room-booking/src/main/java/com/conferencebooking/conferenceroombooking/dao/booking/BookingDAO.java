package com.conferencebooking.conferenceroombooking.dao.booking;

import com.conferencebooking.conferenceroombooking.entity.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingDAO {

    void bookRoom(Booking booking);

    List<Booking> bookingsForTimeFrame(LocalDate from, LocalDate to);

    List<Booking> bookingsForSingleRoom(String name, LocalDate from, LocalDate to);

    List<Booking> bookingsForTheUser(String login, LocalDate from, LocalDate to);


}
