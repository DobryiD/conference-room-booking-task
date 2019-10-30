package com.conferencebooking.conferenceroombooking.service.booking;

import com.conferencebooking.conferenceroombooking.entity.Booking;
import com.conferencebooking.conferenceroombooking.model.BookingDTO;
import com.conferencebooking.conferenceroombooking.model.RequestBooking;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    void bookTheRoom(RequestBooking booking);

    List<BookingDTO> getBookingScheduleForAllRooms(RequestBooking booking);

    List<BookingDTO> getBookingScheduleForSingleRoom(RequestBooking booking);

    List<BookingDTO> getBookingScheduleForUser(RequestBooking booking);

    //public List<BookingDTO> getAllBookings();

}
