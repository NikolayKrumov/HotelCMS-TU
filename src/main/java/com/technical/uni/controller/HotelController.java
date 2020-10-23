package com.technical.uni.controller;

import com.technical.uni.model.Hotel;
import com.technical.uni.repository.HotelDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HotelController {

    private final HotelDAO hotelDAO;

    public HotelController(HotelDAO hotelDAO) {this.hotelDAO = hotelDAO;}

    @PostMapping(value = "/hotels")
    public Hotel saveGuest(@RequestBody Hotel guest) {
        return hotelDAO.save(guest);
    }

    @GetMapping(value = "/hotels")
    public Iterable<Hotel> getAllGuests() {
        return hotelDAO.findAll();
    }

    @GetMapping(value = "/hotels/{id}")
    public Optional<Hotel> getGuest(@PathVariable Long id) {
        return hotelDAO.findById(id);
    }


    @GetMapping(value = "/hotels/delete/{id}")
    public void deleteGuest(@PathVariable Long id) {
        hotelDAO.deleteById(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
