package com.conferencebooking.conferenceroombooking.service.room;

import com.conferencebooking.conferenceroombooking.dao.room.RoomDAO;
import com.conferencebooking.conferenceroombooking.entity.Room;
import com.conferencebooking.conferenceroombooking.exceptions.NotUniqueRoomNameException;
import com.conferencebooking.conferenceroombooking.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomDAO roomDAO;

    @Autowired
    public RoomServiceImpl(RoomDAO theRoomDAO) {
        roomDAO = theRoomDAO;
    }

    @Override
    @Transactional
    public List<Room> getAvailableRooms() {
        return roomDAO.getAvailableRooms();
    }

    @Override
    @Transactional
    public void saveTheRoom(Room room) {
        Room roomForCheck = roomDAO.getRoom(room.getName());

        if (roomForCheck == null)
            roomDAO.saveTheRoom(room);
        else
            throw new NotUniqueRoomNameException();
    }

    @Override
    @Transactional
    public void deleteTheRoom(String name) {

        Room storedRoom = getRoom(name);
        if (storedRoom != null)
            roomDAO.deleteTheRoom(name);

    }

    @Override
    @Transactional
    public void updateTheRoom(Room room) {
        Room storedRoom = getRoom(room.getName());

        if (room.getLocation() != null) {
            storedRoom.setLocation(room.getLocation());
        }
        if (room.getNumberOfSeats() != null) {
            storedRoom.setNumberOfSeats(room.getNumberOfSeats());
        }
        if (room.getPhoneNumber() != null) {
            storedRoom.setPhoneNumber(room.getPhoneNumber());
        }
        if(room.isHaveProjector()!=null) {
            storedRoom.setHaveProjector(room.isHaveProjector());
        }

        roomDAO.saveTheRoom(storedRoom);
    }

    @Override
    @Transactional
    public Room getRoom(String name) {

        Room storedRoom = roomDAO.getRoom(name);
        if (storedRoom != null) {
            return storedRoom;
        } else {
            throw new RecordNotFoundException();
        }
    }
}
