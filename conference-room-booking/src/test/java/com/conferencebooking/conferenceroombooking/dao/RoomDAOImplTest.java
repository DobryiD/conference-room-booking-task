package com.conferencebooking.conferenceroombooking.dao;

import com.conferencebooking.conferenceroombooking.dao.room.RoomDAO;
import com.conferencebooking.conferenceroombooking.entity.Room;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class RoomDAOImplTest {

    @Autowired
    private RoomDAO roomDAO;



    @Test
    public void whenInsert_thenGetAvailableRooms() {

        Room room1=new Room("Small","1st floor",20,false,"412354574");
        Room room2=new Room("hall","1st floor",80,true,"49502142");
        Room room3=new Room("medium","3st floor",35,true,"59346342");

        List<Room> roomList=new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);

        roomDAO.saveTheRoom(room1);
        roomDAO.saveTheRoom(room2);
        roomDAO.saveTheRoom(room3);

        List<Room>storedRooms=roomDAO.getAvailableRooms();

        assertThat(storedRooms).isNotNull().isNotEmpty();
        Assert.assertArrayEquals(storedRooms.toArray(),roomList.toArray());


    }

    @Test
    public void getRoom() {
        Room room1=new Room("Small","1st floor",20,false,"412354574");
        Room room2=new Room("hall","1st floor",80,true,"49502142");
        Room room3=new Room("medium","3st floor",35,true,"59346342");

        roomDAO.saveTheRoom(room1);
        roomDAO.saveTheRoom(room2);
        roomDAO.saveTheRoom(room3);

        Room checkRoom1=roomDAO.getRoom(room1.getName());
        Room checkRoom2=roomDAO.getRoom(room2.getName()+"sd");
        Room checkRoom3=roomDAO.getRoom(room3.getName());

        assertThat(checkRoom1).isNotNull();
        assertThat(checkRoom2).isNull();
        assertThat(checkRoom3).isNotNull();

    }

    @Test
    public void deleteTheRoom() {
        Room room1=new Room("Small","1st floor",20,false,"412354574");
        Room room2=new Room("hall","1st floor",80,true,"49502142");
        Room room3=new Room("medium","3st floor",35,true,"59346342");

        roomDAO.saveTheRoom(room1);
        roomDAO.saveTheRoom(room2);
        roomDAO.saveTheRoom(room3);

        roomDAO.deleteTheRoom(room2.getName());

        Room checkRoom1=roomDAO.getRoom(room1.getName());
        Room checkRoom2=roomDAO.getRoom(room2.getName());
        Room checkRoom3=roomDAO.getRoom(room3.getName());


        assertThat(checkRoom1).isNotNull();
        assertThat(checkRoom2).isNull();
        assertThat(checkRoom3).isNotNull();
    }


}
