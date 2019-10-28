package com.conferencebooking.conferenceroombooking.restcontroller;

import com.conferencebooking.conferenceroombooking.entity.User;
import com.conferencebooking.conferenceroombooking.exceptions.AccessDeniedException;
import com.conferencebooking.conferenceroombooking.model.UserDTO;
import com.conferencebooking.conferenceroombooking.service.user.UserService;
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
public class UserRestController {

    private UserService userService;

    @Value("${adminkey}")
    private String securityKey;

    @Autowired
    public UserRestController(UserService theUserService) {
        userService = theUserService;
    }

    @GetMapping("/users")
    public List<UserDTO> getAvailableUsers() {
        return userService.getAvailableUsers();
    }


    @PutMapping("/user/{adminkey}")
    public ResponseEntity<?>updateUser(@PathVariable("adminkey") String adminKey, @RequestBody User user){
        if (adminKey.equals(securityKey)) {
            userService.updateTheUser(user);
            return new ResponseEntity<>("Update has been completed!", HttpStatus.OK);
        } else {
            throw new AccessDeniedException();
        }
    }

    @PostMapping("/user/{adminkey}")
    public ResponseEntity<?> addUser(@PathVariable("adminkey") String adminKey, @Validated @RequestBody User theUser) {

        if (adminKey.equals(securityKey)) {

            userService.saveTheUser(theUser);
            return new ResponseEntity<>(theUser, HttpStatus.OK);

        } else {
            throw new AccessDeniedException();
        }

    }

    @DeleteMapping("/user/{adminkey}/{login}")
    public ResponseEntity<?> deleteUser(@PathVariable("adminkey") String adminKey, @PathVariable("login") String login) {

        if (adminKey.equals(securityKey)) {

            userService.deleteTheUser(login);
            return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);

        } else {
            throw new AccessDeniedException();
        }
    }


}
