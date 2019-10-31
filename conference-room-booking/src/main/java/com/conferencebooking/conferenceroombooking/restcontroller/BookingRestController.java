package com.conferencebooking.conferenceroombooking.restcontroller;

import com.conferencebooking.conferenceroombooking.dao.booking.BookingDAO;
import com.conferencebooking.conferenceroombooking.entity.Booking;
import com.conferencebooking.conferenceroombooking.model.BookingDTO;
import com.conferencebooking.conferenceroombooking.model.RequestBooking;
import com.conferencebooking.conferenceroombooking.service.booking.BookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Booking Room Management REST API",description = "Operations pertaining to booking")
public class BookingRestController {

    private BookingService bookingService;

    @Autowired
    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }



    @PostMapping("/booking")
    @ApiOperation(value = "Book a room",response = ResponseEntity.class)
    public ResponseEntity<Booking> bookTheRoom(@RequestBody RequestBooking booking) {

        return new ResponseEntity<>(bookingService.bookTheRoom(booking), HttpStatus.CREATED);
    }

    @GetMapping("/booking/singleRoom")
    @ApiOperation(value = "Get Booking schedule within time frame for single room",response = ResponseEntity.class)
    public ResponseEntity<?> getBookingScheduleForSingleRoom(@RequestBody RequestBooking booking) {

        List<BookingDTO> list = bookingService.getBookingScheduleForSingleRoom(booking);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/booking/timeFrame")
    @ApiOperation(value = "Get Booking schedule within time frame ",response = ResponseEntity.class)
    public ResponseEntity<?> getBookingScheduleForTimeFrame(@RequestBody RequestBooking booking) {

        List<BookingDTO> list = bookingService.getBookingScheduleForTimeFrame(booking);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/booking/user")
    @ApiOperation(value = "Get Booking schedule within time frame for certain user",response = ResponseEntity.class)
    public ResponseEntity<?> getBookingScheduleForUser(@RequestBody RequestBooking booking) {

        List<BookingDTO> list = bookingService.getBookingScheduleForUser(booking);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
