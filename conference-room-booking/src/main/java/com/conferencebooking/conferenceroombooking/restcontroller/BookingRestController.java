package com.conferencebooking.conferenceroombooking.restcontroller;

import com.conferencebooking.conferenceroombooking.dao.booking.BookingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookingRestController {

    private BookingDAO bookingDAO;

    @Autowired
    public BookingRestController(BookingDAO bookingDAO){
        this.bookingDAO=bookingDAO;
    }



}
