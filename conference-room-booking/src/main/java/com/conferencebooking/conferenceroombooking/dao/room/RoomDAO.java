package com.conferencebooking.conferenceroombooking.dao.room;

import com.conferencebooking.conferenceroombooking.entity.Room;

import java.util.List;

public interface RoomDAO {
    List<Room> getAvailableRooms();

    void saveTheRoom(Room room);

    void deleteTheRoom(String name);

    Room getRoom(String name);
}
