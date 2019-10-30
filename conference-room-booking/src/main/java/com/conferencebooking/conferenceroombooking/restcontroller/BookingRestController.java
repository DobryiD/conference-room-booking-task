package com.conferencebooking.conferenceroombooking.restcontroller;

import com.conferencebooking.conferenceroombooking.dao.booking.BookingDAO;
import com.conferencebooking.conferenceroombooking.entity.Booking;
import com.conferencebooking.conferenceroombooking.model.BookingDTO;
import com.conferencebooking.conferenceroombooking.model.RequestBooking;
import com.conferencebooking.conferenceroombooking.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingRestController {

    private BookingService bookingService;

    @Autowired
    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }



    @PostMapping("/booking")
    public ResponseEntity<?> bookTheRoom(@RequestBody RequestBooking booking) {
        bookingService.bookTheRoom(booking);

        return new ResponseEntity<>("Booking added!", HttpStatus.CREATED);
    }

    @GetMapping("/booking/singleRoom")
    public ResponseEntity<?> getBookingScheduleForSingleRoom(@RequestBody RequestBooking booking) {

        List<BookingDTO> list = bookingService.getBookingScheduleForSingleRoom(booking);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/booking/timeFrame")
    public ResponseEntity<?> getBookingScheduleForTimeFrame(@RequestBody RequestBooking booking) {

        List<BookingDTO> list = bookingService.getBookingScheduleForAllRooms(booking);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/booking/user")
    public ResponseEntity<?> getBookingScheduleForUser(@RequestBody RequestBooking booking) {

        List<BookingDTO> list = bookingService.getBookingScheduleForUser(booking);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
