package com.conferencebooking.conferenceroombooking.service.room;


import com.conferencebooking.conferenceroombooking.entity.Room;

import java.util.List;

public interface RoomService {

    List<Room> getAvailableRooms();

    void saveTheRoom(Room room);

    void deleteTheRoom(String name);

    void updateTheRoom(Room room);

    Room getRoom(String name);
}
