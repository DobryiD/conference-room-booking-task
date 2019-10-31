package com.conferencebooking.conferenceroombooking.restcontroller;


import com.conferencebooking.conferenceroombooking.model.RequestUser;
import com.conferencebooking.conferenceroombooking.model.UserDTO;
import com.conferencebooking.conferenceroombooking.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@Api(value = "Booking Room Management REST API",description = "Operations pertaining to user")
@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;


    @Autowired
    public UserRestController(UserService theUserService) {
        userService = theUserService;
    }

    @ApiOperation(value = "View a list of available users",response = List.class)
    @GetMapping("/users")
    public List<UserDTO> getAvailableUsers() {
        return userService.getAvailableUsers();
    }

    @ApiOperation(value = "Update information of the existing users",response = ResponseEntity.class)
    @PutMapping("/user")
    public ResponseEntity<?> updateUser(
            @ApiParam(value = "Requested user object to update it in database", required = true)@RequestBody RequestUser user) {
        userService.updateTheUser(user);
        return new ResponseEntity<>("Update has been completed!", HttpStatus.OK);

    }
    @ApiOperation(value = "Add a new user",response = ResponseEntity.class)
    @PostMapping("/user")
    public ResponseEntity<?> addUser(
            @ApiParam(value = "Requested user object to store it in database", required = true) @Validated @RequestBody RequestUser theUser) {
        userService.saveTheUser(theUser);
        return new ResponseEntity<>(theUser, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete an existing user",response = ResponseEntity.class)
    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(
            @ApiParam(value = "Requested user object to store it in database", required = true) @RequestBody RequestUser theUser) {
        userService.deleteTheUser(theUser);
        return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
    }

}
