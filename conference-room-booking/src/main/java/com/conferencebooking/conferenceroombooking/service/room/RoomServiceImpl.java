package com.conferencebooking.conferenceroombooking.service.room;

import com.conferencebooking.conferenceroombooking.dao.room.RoomDAO;
import com.conferencebooking.conferenceroombooking.entity.Room;
import com.conferencebooking.conferenceroombooking.exceptions.AccessDeniedException;
import com.conferencebooking.conferenceroombooking.exceptions.NotUniqueRoomNameException;
import com.conferencebooking.conferenceroombooking.exceptions.RecordNotFoundException;
import com.conferencebooking.conferenceroombooking.model.RequestRoom;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@PropertySource("classpath:admin-file.properties")
public class RoomServiceImpl implements RoomService {

    @Value("${adminkey}")
    private String securityKey;

    private RoomDAO roomDAO;

    private ModelMapper modelMapper;

    @Autowired
    public RoomServiceImpl(RoomDAO theRoomDAO) {
        roomDAO = theRoomDAO;
        modelMapper = new ModelMapper();
    }

    @Override

    public List<Room> getAvailableRooms() {
        return roomDAO.getAvailableRooms();
    }

    @Override
    @Transactional
    public void saveTheRoom(RequestRoom room) {

        if (securityKey.equals(room.getAdminKey())) {

            Room basicRoom = modelMapper.map(room, Room.class);

            Room roomForCheck = roomDAO.getRoom(room.getName());

            if (roomForCheck == null) {

                if (basicRoom.getHaveProjector() == null)
                    basicRoom.setHaveProjector(false);

                roomDAO.saveTheRoom(basicRoom);
            } else
                throw new NotUniqueRoomNameException();
        } else {
            throw new AccessDeniedException("Access denied!");
        }
    }

    @Override
    @Transactional
    public void deleteTheRoom(RequestRoom room) {
        if (securityKey.equals(room.getAdminKey())) {

            Room storedRoom = getRoom(room.getName());

            if (storedRoom != null)
                roomDAO.deleteTheRoom(room.getName());

        } else {
            throw new AccessDeniedException("Access denied!");
        }

    }

    @Override
    @Transactional
    public void updateTheRoom(RequestRoom room) {
        if (securityKey.equals(room.getAdminKey())) {

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
            if (room.getHaveProjector() != null) {
                storedRoom.setHaveProjector(room.getHaveProjector());
            }

            roomDAO.saveTheRoom(storedRoom);

        } else {
            throw new AccessDeniedException("Access denied!");
        }

    }

    @Override
    public Room getRoom(String name) {

        Room storedRoom = roomDAO.getRoom(name);
        if (storedRoom != null) {
            return storedRoom;
        } else {
            throw new RecordNotFoundException("Such room has not been found!");
        }
    }
}
