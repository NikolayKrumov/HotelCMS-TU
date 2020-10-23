package com.technical.uni.controller;

import com.technical.uni.model.Hotel;
import com.technical.uni.model.HotelRooms;
import com.technical.uni.repository.HotelDAO;
import com.technical.uni.repository.HotelRoomsDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class HotelRoomController {

    private final HotelRoomsDAO hotelRoomsDAO;
    private final HotelDAO hotelDAO;

    public HotelRoomController(HotelRoomsDAO hotelRoomsDAO, HotelDAO hotelDAO) {this.hotelRoomsDAO = hotelRoomsDAO;
        this.hotelDAO = hotelDAO;
    }


    @PostMapping(value = "/hotels/rooms")
    public HotelRooms saveGuest(@RequestBody HotelRooms guest) {
        return hotelRoomsDAO.save(guest);
    }

    @GetMapping(value = "/hotels/rooms")
    public Iterable<HotelRooms> getAllGuests() {
        System.out.println("xoxo");
        return hotelRoomsDAO.findAll();
    }

    @GetMapping(value = "/hotels/rooms/{id}")
    public Optional<HotelRooms> getGuest(@PathVariable Long id) {
        return hotelRoomsDAO.findById(id);
    }


    @GetMapping(value = "/hotels/rooms/delete/{id}")
    public void deleteGuest(@PathVariable Long id) {
        hotelRoomsDAO.deleteById(id);
    }

    @PostMapping(value = "/hotels/{id}/avg")
    public void getHotelRoomsAVG(@PathVariable Long id) {

        Optional<Hotel> hotel = hotelDAO.findById(id);
        HotelRooms hotelRooms;
        if (hotel.isPresent()) {
            hotelRooms = hotelRoomsDAO.findByHotel(hotel.get());
        }



    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(HttpServletRequest req, Exception ex) {
        System.err.println(ex);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
