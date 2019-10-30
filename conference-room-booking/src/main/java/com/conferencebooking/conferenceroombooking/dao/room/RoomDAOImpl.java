package com.conferencebooking.conferenceroombooking.dao.room;

import com.conferencebooking.conferenceroombooking.entity.Room;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoomDAOImpl implements RoomDAO {

    private EntityManager entityManager;

    @Autowired
    public RoomDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Room> getAvailableRooms() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery(" from Room", Room.class);
        return (List<Room>) theQuery.getResultList();
    }

    @Override
    public void saveTheRoom(Room room) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(room);
    }

    @Override
    public void deleteTheRoom(String name) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from Room where name=:roomName");
        theQuery.setParameter("roomName", name);

        theQuery.executeUpdate();

    }

    @Override
    public Room getRoom(String name) {

        Session theSession = entityManager.unwrap(Session.class);

        Query theQuery = theSession.createQuery("from Room where name=:roomName");
        theQuery.setParameter("roomName", name);

        List<Room> rooms = (List<Room>) theQuery.getResultList();
        if (rooms.isEmpty())
            return null;
        else
            return rooms.get(0);
    }
}
