package com.conferencebooking.conferenceroombooking.service;


import com.conferencebooking.conferenceroombooking.entity.Booking;
import com.conferencebooking.conferenceroombooking.entity.Room;
import com.conferencebooking.conferenceroombooking.entity.User;
import com.conferencebooking.conferenceroombooking.exceptions.AccessDeniedException;
import com.conferencebooking.conferenceroombooking.exceptions.IncorrectDataException;
import com.conferencebooking.conferenceroombooking.exceptions.RecordNotFoundException;
import com.conferencebooking.conferenceroombooking.model.RequestBooking;
import com.conferencebooking.conferenceroombooking.service.booking.BookingServiceImpl;
import com.conferencebooking.conferenceroombooking.service.room.RoomServiceImpl;
import com.conferencebooking.conferenceroombooking.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@PropertySource("classpath:admin-file.properties")
public class BookingServiceImplTest {

    @Value("${adminkey}")
    private String adminKey;

    @Autowired
    private BookingServiceImpl bookingService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoomServiceImpl roomService;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void bookTheRoom() {

        User user=userService.getUser("jsmith");
        Room room=roomService.getRoom("Medium Room");
        bookingService.bookTheRoom(new RequestBooking(room.getName(),user.getLogin(),user.getPassword(),"2013-09-13 13:00:00", "2014-09-13 13:00:00"));


        RequestBooking bookingWithIncorrectDate=new RequestBooking(room.getName(),user.getLogin(),user.getPassword(),"2013-09-13 13:00:00", "2012-09-13 13:00:00");
        RequestBooking bookingThatIsAlreadyBooked=new RequestBooking(room.getName(),user.getLogin(),user.getPassword(),"2013-09-13 13:00:00", "2014-09-13 13:00:00");
        RequestBooking bookingWithNoExistingRoom=new RequestBooking(room.getName()+"sd",user.getLogin(),user.getPassword(),
                "2013-09-13 13:00:00", "2014-09-13 13:00:00");
        RequestBooking bookingWithWrongLogin=new RequestBooking(room.getName(),user.getLogin()+" asd",user.getPassword(),
                "2013-09-13 13:00:00", "2014-09-13 13:00:00");


        assertThatThrownBy(()->bookingService.bookTheRoom(bookingWithIncorrectDate)).isInstanceOf(IncorrectDataException.class);
        assertThatThrownBy(()->bookingService.bookTheRoom(bookingThatIsAlreadyBooked)).isInstanceOf(AccessDeniedException.class);
        assertThatThrownBy(()->bookingService.bookTheRoom(bookingWithNoExistingRoom)).isInstanceOf(RecordNotFoundException.class);
        assertThatThrownBy(()->bookingService.bookTheRoom(bookingWithWrongLogin)).isInstanceOf(AccessDeniedException.class);


    }

    @Test
    public void getBookingScheduleForTimeFrame() {
        User user=userService.getUser("jsmith");
        Room room=roomService.getRoom("Medium Room");
        bookingService.bookTheRoom(new RequestBooking(room.getName(),user.getLogin(),user.getPassword(),"2013-09-13 13:00:00", "2014-09-13 13:00:00"));
        bookingService.bookTheRoom(new RequestBooking(room.getName(),user.getLogin(),user.getPassword(),"2007-09-13 13:00:00", "2008-09-13 13:00:00"));

        RequestBooking bookingWithNoDate=new RequestBooking(null,null,null,null, null);
        assertThat(bookingService.getBookingScheduleForTimeFrame(bookingWithNoDate).size()).isEqualTo(2);

        RequestBooking bookingWithDate=new RequestBooking(null,null,null,"2013-09-13 13:00:00", "2014-09-13 13:00:00");
        assertThat(bookingService.getBookingScheduleForTimeFrame(bookingWithDate).size()).isEqualTo(1);

    }

    @Test
    public void getBookingScheduleForSingleRoom() {
        User user=userService.getUser("jsmith");
        Room room=roomService.getRoom("Medium Room");
        bookingService.bookTheRoom(new RequestBooking(room.getName(),user.getLogin(),user.getPassword(),"2013-09-13 13:00:00", "2014-09-13 13:00:00"));
        bookingService.bookTheRoom(new RequestBooking(room.getName(),user.getLogin(),user.getPassword(),"2007-09-13 13:00:00", "2008-09-13 13:00:00"));

        RequestBooking bookingWithNoDate=new RequestBooking(room.getName(),null,null,null, null);
        assertThat(bookingService.getBookingScheduleForSingleRoom(bookingWithNoDate).size()).isEqualTo(2);

        RequestBooking bookingWithDate=new RequestBooking(room.getName(),null,null,"2013-09-13 13:00:00", "2014-09-13 13:00:00");
        assertThat(bookingService.getBookingScheduleForSingleRoom(bookingWithDate).size()).isEqualTo(1);

        RequestBooking bookingWithOneDate=new RequestBooking(room.getName(),null,null,"2007-09-13 13:00:00", null);
        assertThat(bookingService.getBookingScheduleForSingleRoom(bookingWithOneDate).size()).isEqualTo(2);

        RequestBooking bookingWithNoExistRoom=new RequestBooking(room.getName()+"asd",null,null,"2007-09-13 13:00:00", null);
        assertThatThrownBy(()->bookingService.getBookingScheduleForSingleRoom(bookingWithNoExistRoom)).isInstanceOf(RecordNotFoundException.class);

    }

    @Test
    public void getBookingScheduleForUser() {
        User user=userService.getUser("jsmith");
        Room room=roomService.getRoom("Medium Room");
        bookingService.bookTheRoom(new RequestBooking(room.getName(),user.getLogin(),user.getPassword(),"2013-09-13 13:00:00", "2014-09-13 13:00:00"));
        bookingService.bookTheRoom(new RequestBooking(room.getName(),user.getLogin(),user.getPassword(),"2007-09-13 13:00:00", "2008-09-13 13:00:00"));

        RequestBooking bookingWithNoDate=new RequestBooking(null,user.getLogin(),null,null, null);
        assertThat(bookingService.getBookingScheduleForUser(bookingWithNoDate).size()).isEqualTo(2);

        RequestBooking bookingWithDate=new RequestBooking(null,user.getLogin(),null,"2013-09-13 13:00:00", "2014-09-13 13:00:00");
        assertThat(bookingService.getBookingScheduleForUser(bookingWithDate).size()).isEqualTo(1);

        RequestBooking bookingWithOneDate=new RequestBooking(null,user.getLogin(),null,"2007-09-13 13:00:00", null);
        assertThat(bookingService.getBookingScheduleForUser(bookingWithOneDate).size()).isEqualTo(2);

        RequestBooking bookingWithNoExistUser=new RequestBooking(null,user.getLogin()+" ",null,"2007-09-13 13:00:00", null);
        assertThatThrownBy(()->bookingService.getBookingScheduleForSingleRoom(bookingWithNoExistUser)).isInstanceOf(RecordNotFoundException.class);
    }


}
