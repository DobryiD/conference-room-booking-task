package com.conferencebooking.conferenceroombooking.restcontroller;


import com.conferencebooking.conferenceroombooking.model.RequestUser;
import com.conferencebooking.conferenceroombooking.model.UserDTO;
import com.conferencebooking.conferenceroombooking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;


    @Autowired
    public UserRestController(UserService theUserService) {
        userService = theUserService;
    }

    @GetMapping("/users")
    public List<UserDTO> getAvailableUsers() {
        return userService.getAvailableUsers();
    }


    @PutMapping("/user")
    public ResponseEntity<?> updateUser( @RequestBody RequestUser user) {
        userService.updateTheUser(user);
        return new ResponseEntity<>("Update has been completed!", HttpStatus.OK);

    }

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@Validated @RequestBody RequestUser theUser) {
        userService.saveTheUser(theUser);
        return new ResponseEntity<>(theUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(@RequestBody RequestUser theUser) {
        userService.deleteTheUser(theUser);
        return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
    }


}
