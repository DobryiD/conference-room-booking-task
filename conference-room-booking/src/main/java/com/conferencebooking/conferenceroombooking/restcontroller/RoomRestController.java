package com.conferencebooking.conferenceroombooking.restcontroller;

import com.conferencebooking.conferenceroombooking.entity.Room;
import com.conferencebooking.conferenceroombooking.exceptions.AccessDeniedException;
import com.conferencebooking.conferenceroombooking.service.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@PropertySource("classpath:admin-file.properties")
public class RoomRestController {

    @Value("${adminkey}")
    private String securityKey;

    private RoomService roomService;

    @Autowired
    public RoomRestController(RoomService theRoomService) {
        roomService = theRoomService;
    }

    @GetMapping("/rooms")
    public List<Room> getAvailableUsers() {
        return roomService.getAvailableRooms();
    }

    @PostMapping("/room/{adminkey}")
    public ResponseEntity<?> addUser(@PathVariable("adminkey") String adminKey, @Validated @RequestBody Room theRoom) {
        if (adminKey.equals(securityKey)) {

            roomService.saveTheRoom(theRoom);

            return new ResponseEntity<>(theRoom, HttpStatus.OK);
        } else {
            throw new AccessDeniedException();
        }
    }

    @PutMapping("/room/{adminkey}")
    public ResponseEntity<?> updateRoom(@PathVariable("adminkey") String adminKey, @RequestBody Room room) {
        if (adminKey.equals(securityKey)) {

            roomService.updateTheRoom(room);
            return new ResponseEntity<>("Update has been completed!", HttpStatus.OK);
        } else {
            throw new AccessDeniedException();
        }
    }

    @DeleteMapping("/room/{adminkey}/{name}")
    public ResponseEntity<?> deleteUser(@PathVariable("adminkey") String adminKey, @PathVariable String name) {
        if (adminKey.equals(securityKey)) {

            roomService.deleteTheRoom(name);
            return new ResponseEntity<>("Room has been deleted!", HttpStatus.OK);
        } else {
            throw new AccessDeniedException();
        }
    }


}
