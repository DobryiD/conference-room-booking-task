package com.conferencebooking.conferenceroombooking.service.booking;

import com.conferencebooking.conferenceroombooking.dao.booking.BookingDAO;
import com.conferencebooking.conferenceroombooking.dao.room.RoomDAO;
import com.conferencebooking.conferenceroombooking.dao.user.UserDAO;
import com.conferencebooking.conferenceroombooking.entity.Booking;
import com.conferencebooking.conferenceroombooking.entity.Room;
import com.conferencebooking.conferenceroombooking.entity.User;
import com.conferencebooking.conferenceroombooking.exceptions.AccessDeniedException;
import com.conferencebooking.conferenceroombooking.exceptions.IncorrectDataException;
import com.conferencebooking.conferenceroombooking.exceptions.RecordNotFoundException;
import com.conferencebooking.conferenceroombooking.model.BookingDTO;
import com.conferencebooking.conferenceroombooking.model.RequestBooking;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private UserDAO userDAO;
    private RoomDAO roomDAO;
    private BookingDAO bookingDAO;

    private ModelMapper modelMapper;

    @Autowired
    public BookingServiceImpl(UserDAO userDAO, RoomDAO roomDAO, BookingDAO bookingDAO) {
        this.userDAO = userDAO;
        this.roomDAO = roomDAO;
        this.bookingDAO = bookingDAO;
        modelMapper = new ModelMapper();

    }

    @Override
    @Transactional
    public Booking bookTheRoom(RequestBooking booking) {

        Booking theBooking=checkBookingForCorrectness(booking);
        bookingDAO.bookRoom(theBooking);
        return theBooking;

    }

    private Booking checkBookingForCorrectness(RequestBooking booking) {
        User checkUser = userDAO.getUser(booking.getLogin());
        if (checkUser != null && booking.getPassword().equals(checkUser.getPassword())) {
            Room checkRoom = roomDAO.getRoom(booking.getRoomName());
            if (checkRoom != null) {
                if (booking.isDateTimeCorrect()) {
                    List<Booking> bookingCheck = bookingDAO.bookingsForSingleRoom(checkRoom, booking.getStartDate(), booking.getEndDate());
                    if (bookingCheck.isEmpty()) {
                        return new Booking(checkUser, checkRoom, booking.getStartDate(), booking.getEndDate());
                    } else {
                        throw new AccessDeniedException("This room is already booked for this period!");
                    }
                } else {
                    throw new IncorrectDataException("End date can not be before start date!");
                }
            } else {
                throw new RecordNotFoundException("Such room does not exist!");
            }
        } else {
            throw new AccessDeniedException("Login or password is incorrect!");
        }
    }



    @Override
    public List<BookingDTO> getBookingScheduleForTimeFrame(RequestBooking booking) {

        List<Booking> storedBookings;
        if (booking.getStartDate() != null && booking.getEndDate() != null) {

            if (booking.isDateTimeCorrect()) {
                storedBookings = bookingDAO.bookingsForTimeFrame(booking.getStartDate(), booking.getEndDate());
            } else {
                throw new IncorrectDataException("End date can not be before start date!");
            }
        } else {
            storedBookings = bookingDAO.getAllBookings(booking.getStartDate(), booking.getEndDate());
        }

        return mapBookingToDTO(storedBookings);
    }

    @Override
    public List<BookingDTO> getBookingScheduleForSingleRoom(RequestBooking booking) {

        Room theRoom = roomDAO.getRoom(booking.getRoomName());
        if (theRoom != null) {
            List<Booking> storedBookings;
            if (booking.getStartDate() != null && booking.getEndDate() != null) {
                if (booking.isDateTimeCorrect()) {
                    storedBookings = bookingDAO.bookingsForSingleRoom(theRoom, booking.getStartDate(), booking.getEndDate());
                } else {
                    throw new IncorrectDataException("End date can not be before start date!");
                }
            } else {
                storedBookings = bookingDAO.getAllBookingsForRoom(theRoom, booking.getStartDate(), booking.getEndDate());
            }
            return mapBookingToDTO(storedBookings);
        } else {
            throw new RecordNotFoundException("Such room does not exist!");
        }

    }

    @Override
    public List<BookingDTO> getBookingScheduleForUser(RequestBooking booking) {

        User theUser = userDAO.getUser(booking.getLogin());
        if (theUser != null) {
            List<Booking> storedBookings;
            if (booking.getStartDate() != null && booking.getEndDate() != null) {
                if (booking.isDateTimeCorrect()) {
                    storedBookings = bookingDAO.bookingsForTheUser(theUser, booking.getStartDate(), booking.getEndDate());
                } else {
                    throw new IncorrectDataException("End date can not be before start date!");
                }
            } else {
                storedBookings = bookingDAO.getAllBookingsForUser(theUser, booking.getStartDate(), booking.getEndDate());
            }
            return mapBookingToDTO(storedBookings);
        } else {
            throw new RecordNotFoundException("Such user does not exist!");
        }
    }


    private List<BookingDTO> mapBookingToDTO(List<Booking> bookingList) {
        List<BookingDTO> bookingDTOList = new ArrayList<>();

        for (Booking book : bookingList) {
            BookingDTO mappedBooking = modelMapper.map(book, BookingDTO.class);
            bookingDTOList.add(mappedBooking);
        }
        return bookingDTOList;
    }
}
