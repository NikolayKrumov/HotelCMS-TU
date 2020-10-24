package com.technical.uni.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.technical.uni.model.FreeRoomDTO;
import com.technical.uni.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomDAO extends CrudRepository<Room, Long> {

    @Query(value = "SELECT COUNT(*) as free_rooms, status FROM rooms r inner join rooms_statuses rs on r.room_status_id = rs.id and rs.status"
        + " = 'FREE' GROUP BY status", nativeQuery = true)
    Integer getFreeRoomsCount();


    @Query(value = "SELECT COUNT(pet_friendly) FROM rooms WHERE pet_friendly is true", nativeQuery = true)
    Long getPetFriendlyRoomsCount();

    @Query(value = "SELECT COUNT(*) FROM rooms", nativeQuery = true)
    Long getRoomsCount();

}
