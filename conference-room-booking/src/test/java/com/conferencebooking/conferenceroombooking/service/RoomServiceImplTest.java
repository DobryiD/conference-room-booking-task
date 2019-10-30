package com.conferencebooking.conferenceroombooking.service;


import com.conferencebooking.conferenceroombooking.dao.room.RoomDAO;
import com.conferencebooking.conferenceroombooking.entity.Room;

import com.conferencebooking.conferenceroombooking.exceptions.AccessDeniedException;
import com.conferencebooking.conferenceroombooking.exceptions.NotUniqueRoomNameException;
import com.conferencebooking.conferenceroombooking.exceptions.RecordNotFoundException;
import com.conferencebooking.conferenceroombooking.model.RequestRoom;
import com.conferencebooking.conferenceroombooking.service.room.RoomServiceImpl;
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

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@PropertySource("classpath:admin-file.properties")
public class RoomServiceImplTest {

    @Value("${adminkey}")
    private String adminKey;



    @Autowired
    private RoomServiceImpl roomService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveTheRoom() {
        RequestRoom room=new RequestRoom(adminKey,"Small","1st floor",20,false,"412354574");
        roomService.saveTheRoom(room);

        assertThat(roomService.getRoom(room.getName())).isNotNull();


    }
    @Test(expected = NotUniqueRoomNameException.class )
    public  void whenSaveThanNotUnique(){
        RequestRoom room=new RequestRoom(adminKey,"Small","1st floor",20,false,"412354574");
        roomService.saveTheRoom(room);

        RequestRoom room2=new RequestRoom(adminKey,"Small","2st floor",80,true,"49502142");
        roomService.saveTheRoom(room2);
    }

    @Test(expected = AccessDeniedException.class )
    public  void whenSaveThanAccessDenied(){
        RequestRoom room=new RequestRoom(adminKey,"Small","1st floor",20,false,"412354574");
        roomService.saveTheRoom(room);



        RequestRoom room2=new RequestRoom("","Small","2st floor",80,true,"49502142");
        roomService.saveTheRoom(room2);
    }
    @Test
    public void whenSaveThenCheckContent(){
        RequestRoom room=new RequestRoom(adminKey,"Small","1st floor",20,false,"412354574");
        roomService.saveTheRoom(room);

        Room storedRoom=roomService.getRoom(room.getName());

        assertThat(storedRoom).isNotNull();
        assertThat(storedRoom.getLocation()).isEqualTo(room.getLocation());
        assertThat(storedRoom.getNumberOfSeats()).isEqualTo(room.getNumberOfSeats());
        assertThat(storedRoom.getHaveProjector()).isEqualTo(room.getHaveProjector());

    }


    @Test(expected = RecordNotFoundException.class)
    public void whenSearchThenRecordNotFound() {
        RequestRoom room=new RequestRoom(adminKey,"Small","1st floor",20,false,"412354574");
        roomService.saveTheRoom(room);

        Room foundRoom1 =roomService.getRoom("sMall");
        Room foundRoom2=roomService.getRoom(room.getName());

        assertThat(foundRoom1).isNull();
        assertThat(foundRoom2).isNotNull();

    }

    @Test
    public void updateTheRoom() {

        String name="small";
        String loc="1st floor";
        int seats =20;
        String number="412354574";
        RequestRoom initialRoom=new RequestRoom(adminKey,name,loc,seats,true,number);

        roomService.saveTheRoom(initialRoom);

        assertThat(roomService.getRoom(name)).isNotNull();

        RequestRoom room2=new RequestRoom(adminKey,"small","2st floor",null,null,"49502142");

        roomService.updateTheRoom(room2);

        Room changedRoom=roomService.getRoom(name);
        assertThat(changedRoom.getLocation()).isNotEqualTo(loc);
        assertThat(changedRoom.getNumberOfSeats()).isEqualTo(seats);
        assertThat(changedRoom.getHaveProjector()).isEqualTo(true);
        assertThat(changedRoom.getPhoneNumber()).isNotEqualTo(number);

        loc=changedRoom.getLocation();
        seats=changedRoom.getNumberOfSeats();
        boolean projector=changedRoom.getHaveProjector();
        number=changedRoom.getPhoneNumber();

        RequestRoom room3=new RequestRoom(adminKey,"small",null,50,false,null);
        roomService.updateTheRoom(room3);

        changedRoom=roomService.getRoom(name);
        assertThat(changedRoom.getLocation()).isEqualTo(loc);
        assertThat(changedRoom.getNumberOfSeats()).isNotEqualTo(seats);
        assertThat(changedRoom.getHaveProjector()).isNotEqualTo(projector);
        assertThat(changedRoom.getPhoneNumber()).isEqualTo(number);

    }
}
