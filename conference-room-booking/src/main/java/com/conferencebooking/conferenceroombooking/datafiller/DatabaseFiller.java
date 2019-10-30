package com.conferencebooking.conferenceroombooking.datafiller;


import com.conferencebooking.conferenceroombooking.entity.Room;
import com.conferencebooking.conferenceroombooking.model.RequestRoom;
import com.conferencebooking.conferenceroombooking.model.RequestUser;
import com.conferencebooking.conferenceroombooking.service.room.RoomService;
import com.conferencebooking.conferenceroombooking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:admin-file.properties")
public class DatabaseFiller implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${adminkey}")
    private String adminKey;
    private UserService userService;

    private RoomService roomService;

    @Autowired
    public DatabaseFiller(UserService userService, RoomService roomService) {
        this.userService = userService;
        this.roomService = roomService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        userService.saveTheUser(new RequestUser(adminKey,"John","Smith","jsmith","qwerty"));
        userService.saveTheUser(new RequestUser(adminKey,"Jane","Doe","jdoe","mySecret"));

        roomService.saveTheRoom(new RequestRoom(adminKey,"Large Room","1st floor",10,true,"22-22-22-22"));
        roomService.saveTheRoom(new RequestRoom(adminKey,"Medium Room","1st floor",6,true,""));
        roomService.saveTheRoom(new RequestRoom(adminKey,"Small Room","2st floor",4,false,""));
    }

}
