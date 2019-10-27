package com.conferencebooking.conferenceroombooking.restcontroller;

import com.conferencebooking.conferenceroombooking.entity.User;

import com.conferencebooking.conferenceroombooking.service.UserService;
import org.hibernate.MappingException;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService theUserService ){
        userService=theUserService;
    }

    @GetMapping("/users")
    public List<User> getAvailableUsers(){
        return userService.getAvailableUsers();
    }

    @PutMapping("/user/{login}")
    public String updateUser(@PathVariable("login") String login,
                             @RequestParam(name = "name",required = false)String name,
                             @RequestParam(name = "surname",required = false)String surname,
                             @RequestParam(name = "password",required = false)String pass ){


        userService.updateTheUser(login, name, surname, pass);
        return "User has been updated!";
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User theUser){

        theUser.setId(0);
        userService.saveTheUser(theUser);

        return theUser;

    }

    @DeleteMapping("/user/{login}")
    public void deleteUser(@PathVariable String login){
        userService.deleteTheUser(login);
    }


}
