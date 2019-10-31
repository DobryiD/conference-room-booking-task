package com.conferencebooking.conferenceroombooking.service.booking;


import com.conferencebooking.conferenceroombooking.entity.Booking;
import com.conferencebooking.conferenceroombooking.model.BookingDTO;
import com.conferencebooking.conferenceroombooking.model.RequestBooking;


import java.util.List;

public interface BookingService {

    Booking bookTheRoom(RequestBooking booking);

    List<BookingDTO> getBookingScheduleForTimeFrame(RequestBooking booking);

    List<BookingDTO> getBookingScheduleForSingleRoom(RequestBooking booking);

    List<BookingDTO> getBookingScheduleForUser(RequestBooking booking);



}
