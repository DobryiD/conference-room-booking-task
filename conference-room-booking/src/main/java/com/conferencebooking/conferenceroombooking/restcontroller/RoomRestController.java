package com.conferencebooking.conferenceroombooking.restcontroller;

import com.conferencebooking.conferenceroombooking.entity.Room;
import com.conferencebooking.conferenceroombooking.model.RequestRoom;
import com.conferencebooking.conferenceroombooking.service.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomRestController {



    private RoomService roomService;

    @Autowired
    public RoomRestController(RoomService theRoomService) {
        roomService = theRoomService;
    }

    @GetMapping("/rooms")
    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    @PostMapping("/room")
    public ResponseEntity<?> addUser(@Validated @RequestBody RequestRoom theRoom) {

        roomService.saveTheRoom(theRoom);
        return new ResponseEntity<>(theRoom, HttpStatus.CREATED);
    }


    @PutMapping("/room")
    public ResponseEntity<?> updateRoom(@RequestBody RequestRoom room) {

        roomService.updateTheRoom(room);
        return new ResponseEntity<>("Update has been completed!", HttpStatus.OK);

    }

    @DeleteMapping("/room")
    public ResponseEntity<?> deleteRoom(@RequestBody RequestRoom room) {

        roomService.deleteTheRoom(room);
        return new ResponseEntity<>("Room has been deleted!", HttpStatus.OK);

    }


}
