package com.conferencebooking.conferenceroombooking.service.room;


import com.conferencebooking.conferenceroombooking.entity.Room;
import com.conferencebooking.conferenceroombooking.model.RequestRoom;

import java.util.List;

public interface RoomService {

    List<Room> getAvailableRooms();

    void saveTheRoom(RequestRoom room);

    void deleteTheRoom(RequestRoom name);

    void updateTheRoom(RequestRoom room);

    Room getRoom(String name);
}
