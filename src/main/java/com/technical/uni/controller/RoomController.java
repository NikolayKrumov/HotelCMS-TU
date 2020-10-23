package com.technical.uni.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.technical.uni.model.FreeRoomDTO;
import com.technical.uni.model.PetFriendlyRoomsDTO;
import com.technical.uni.model.Room;
import com.technical.uni.model.RoomStatus;
import com.technical.uni.repository.RoomDAO;
import com.technical.uni.repository.RoomStatusDAO;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {

    private final RoomDAO roomDAO;
    private final RoomStatusDAO roomStatusDAO;

    public RoomController(RoomDAO roomDAO, RoomStatusDAO roomStatusDAO) {this.roomDAO = roomDAO;
        this.roomStatusDAO = roomStatusDAO;
    }

    @PostMapping(value = "/rooms")
    public Room saveGuest(@RequestBody Room guest) {
        return roomDAO.save(guest);
    }

    @GetMapping(value = "/rooms")
    public Iterable<Room> getAllGuests() {
        return roomDAO.findAll();
    }

    @GetMapping(value = "/rooms/{id}")
    public Optional<Room> getGuest(@PathVariable Long id) {
        return roomDAO.findById(id);
    }


    @GetMapping(value = "/rooms/delete/{id}")
    public void deleteGuest(@PathVariable Long id) {
        roomDAO.deleteById(id);
    }

    @GetMapping(value = "/rooms/status")
    public Iterable<RoomStatus> getAllRoomStatuses() {
        return roomStatusDAO.findAll();
    }

    @GetMapping(value = "/rooms/free")
    public FreeRoomDTO getFreeRooms() {
        FreeRoomDTO freeRoomDTO = new FreeRoomDTO();
        freeRoomDTO.setStatus("FREE");
        freeRoomDTO.setFreeRooms(roomDAO.getFreeRoomsCount());

        return freeRoomDTO;
    }

//    @SneakyThrows
    @PostMapping(value = "/rooms/avg")
    public PetFriendlyRoomsDTO petFriendly() {
        ObjectMapper mapper = new ObjectMapper();
        Long petFriendlyCount = roomDAO.getPetFriendlyRoomsCount();
        Long roomsCount = roomDAO.getRoomsCount();
        Double avg = (petFriendlyCount.doubleValue()/roomsCount.doubleValue())*100;

        PetFriendlyRoomsDTO petFriendlyRoomsDTO = new PetFriendlyRoomsDTO();
        petFriendlyRoomsDTO.setPetFriendlyRooms(petFriendlyCount);
        petFriendlyRoomsDTO.setRoomsCount(roomsCount);
        petFriendlyRoomsDTO.setPetFriendlyPercent(avg.longValue());
        try {
            System.out.println(mapper.writeValueAsString(petFriendlyRoomsDTO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        return avg;
        return petFriendlyRoomsDTO;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}