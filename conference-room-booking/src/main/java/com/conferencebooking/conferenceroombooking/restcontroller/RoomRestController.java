package com.conferencebooking.conferenceroombooking.restcontroller;

import com.conferencebooking.conferenceroombooking.entity.Room;
import com.conferencebooking.conferenceroombooking.model.RequestRoom;
import com.conferencebooking.conferenceroombooking.service.room.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Booking Room Management REST API",description = "Operations pertaining to room")
public class RoomRestController {



    private RoomService roomService;

    @Autowired
    public RoomRestController(RoomService theRoomService) {
        roomService = theRoomService;
    }

    @GetMapping("/rooms")
    @ApiOperation(value = "View a list of available rooms",response = List.class)
    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    @PostMapping("/room")
    @ApiOperation(value = "Add a new room",response = ResponseEntity.class)
    public ResponseEntity<?> addUser(@Validated @RequestBody RequestRoom theRoom) {

        roomService.saveTheRoom(theRoom);
        return new ResponseEntity<>(theRoom, HttpStatus.CREATED);
    }


    @PutMapping("/room")
    @ApiOperation(value = "Update information of the existing room",response = ResponseEntity.class)
    public ResponseEntity<?> updateRoom(@RequestBody RequestRoom room) {

        roomService.updateTheRoom(room);
        return new ResponseEntity<>("Update has been completed!", HttpStatus.OK);

    }

    @DeleteMapping("/room")
    @ApiOperation(value = "Delete an existing user",response = ResponseEntity.class)
    public ResponseEntity<?> deleteRoom(@RequestBody RequestRoom room) {

        roomService.deleteTheRoom(room);
        return new ResponseEntity<>("Room has been deleted!", HttpStatus.OK);

    }


}
