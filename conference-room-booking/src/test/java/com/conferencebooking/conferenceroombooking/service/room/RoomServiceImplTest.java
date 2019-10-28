package com.conferencebooking.conferenceroombooking.service.room;


import com.conferencebooking.conferenceroombooking.dao.room.RoomDAO;
import com.conferencebooking.conferenceroombooking.entity.Room;

import com.conferencebooking.conferenceroombooking.exceptions.NotUniqueRoomNameException;
import com.conferencebooking.conferenceroombooking.exceptions.RecordNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class RoomServiceImplTest {


    @Autowired
    private RoomDAO roomDAO;

    @Autowired
    private RoomServiceImpl roomService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = NotUniqueRoomNameException.class)
    public void saveTheRoom() {
        Room room1=new Room("Small","1st floor",20,false,"412354574");
        roomService.saveTheRoom(room1);

        Room room2=new Room("Small","2st floor",80,true,"49502142");
        roomService.saveTheRoom(room2);
    }

    @Test(expected = RecordNotFoundException.class)
    public void getTheRoom() {
        Room room1=new Room("Small","1st floor",20,false,"412354574");
        roomService.saveTheRoom(room1);

        Room foundRoom1 =roomService.getRoom("sMall");
        Room foundRoom2=roomService.getRoom(room1.getName());

        assertThat(foundRoom1).isNull();
        assertThat(foundRoom2).isNotNull();

    }

    @Test
    public void updateTheRoom() {

        String name="small";
        String loc="1st floor";
        int seats =20;
        String number="412354574";
        Room initialRoom=new Room(name,loc,seats,false,number);

        roomService.saveTheRoom(initialRoom);

        assertThat(roomService.getRoom(name)).isNotNull();

        Room room2=new Room("small","2st floor",null,null,"49502142");

        roomService.updateTheRoom(room2);

        Room changedRoom=roomService.getRoom(name);
        assertThat(changedRoom.getLocation()).isNotEqualTo(loc);
        assertThat(changedRoom.getNumberOfSeats()).isEqualTo(seats);
        assertThat(changedRoom.isHaveProjector()).isEqualTo(false);
        assertThat(changedRoom.getPhoneNumber()).isNotEqualTo(number);

        loc=changedRoom.getLocation();
        seats=changedRoom.getNumberOfSeats();
        boolean projector=changedRoom.isHaveProjector();
        number=changedRoom.getPhoneNumber();

        Room room3=new Room("small",null,50,true,null);
        roomService.updateTheRoom(room3);

        changedRoom=roomService.getRoom(name);
        assertThat(changedRoom.getLocation()).isEqualTo(loc);
        assertThat(changedRoom.getNumberOfSeats()).isNotEqualTo(seats);
        assertThat(changedRoom.isHaveProjector()).isNotEqualTo(projector);
        assertThat(changedRoom.getPhoneNumber()).isEqualTo(number);

    }
}
