package com.technical.uni.controller;

import com.technical.uni.model.Staff;
import com.technical.uni.repository.StaffDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class StaffContoller {

    private final StaffDAO staffDAO;

    public StaffContoller(StaffDAO staffDAO) {this.staffDAO = staffDAO;}

    @PostMapping(value = "/staff")
    public Staff saveGuest(@RequestBody Staff guest) {
        return staffDAO.save(guest);
    }

    @GetMapping(value = "/staff")
    public Iterable<Staff> getAllGuests() {
        return staffDAO.findAll();
    }

    @GetMapping(value = "/staff/{id}")
    public Optional<Staff> getGuest(@PathVariable Long id) {
        return staffDAO.findById(id);
    }


    @GetMapping(value = "/staff/delete/{id}")
    public void deleteGuest(@PathVariable Long id) {
        staffDAO.deleteById(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
