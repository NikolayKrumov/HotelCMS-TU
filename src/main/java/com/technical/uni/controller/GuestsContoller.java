package com.technical.uni.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.uni.model.Guest;
import com.technical.uni.repository.GuestDAO;
import lombok.SneakyThrows;
import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

@RestController
public class GuestsContoller {

    private final GuestDAO guestDAO;

    public GuestsContoller(GuestDAO guestDAO) {this.guestDAO = guestDAO;}

    @PostMapping(value = "/guests")
    public Guest saveGuest(@RequestBody Guest guest) {
        if (null == guest.getId()) {
            guest.setRegistrationDate(convertToLocalDateTimeViaSqlTimestamp(new Date()).toLocalDate());
        } else {
            Optional<Guest> oldGuest = guestDAO.findById(guest.getId());
            if (oldGuest.isPresent() && oldGuest.get().getRegistrationDate() != null) {
                guest.setRegistrationDate(oldGuest.get().getRegistrationDate());
            } else {
                guest.setRegistrationDate(convertToLocalDateTimeViaSqlTimestamp(new Date()).toLocalDate());
            }
        }
        return guestDAO.save(guest);
    }

    @GetMapping(value = "/guests")
    public Iterable<Guest> getAllGuests() {
        return guestDAO.findAll();
    }

    @GetMapping(value = "/guests/{id}")
    public Optional<Guest> getGuest(@PathVariable Long id) {
        return guestDAO.findById(id);
    }


    @GetMapping(value = "/guests/delete/{id}")
    public void deleteGuest(@PathVariable Long id) {
        guestDAO.deleteById(id);
    }

    @SneakyThrows
    @PostMapping(value = "/guests/getby/firstNameAndHotelName")
    public ResponseEntity<Guest> getGuestByFnAndHn(@RequestParam String firstName, @RequestParam String hotelName) {
        Guest guest = guestDAO.getCustomerInfoByCustomerFirstNameAndHotelName(firstName, hotelName);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(guest));

        if (null != guest)  {
            return new ResponseEntity<> (guest, HttpStatus.OK);
        } else {
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }
    }

    @SneakyThrows
    @PostMapping(value = "/guests/getby/dateBetween")
    public ResponseEntity<Iterable<Guest>> getGuestByHnAndDateBtw(@RequestParam String startDate,
                                                   @RequestParam String endDate,
                                                   @RequestParam String hotelName) {
        System.out.println("were here");
        Iterable<Guest> guests = guestDAO.getGuestByHotelAndRegistrationDateBetween(hotelName, startDate, endDate);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(guests));

        if (null != guests)  {
            return new ResponseEntity<> (guests, HttpStatus.OK);
        } else {
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping(value = "/guests/old")
//    @Transactional
//    public void deleteOldGuests() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MILLISECOND, -1209600000);
//        LocalDate localDate = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
//        System.out.println(localDate);
//        System.out.println(calendar.getTime());
////        calendar.getTime();
//        Date thisDate = calendar.getTime();
//        System.out.println(thisDate);
//        guestDAO.deleteOldGuests(localDate);
//    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        System.err.println(e);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private LocalDateTime convertToLocalDateTimeViaSqlTimestamp(Date dateToConvert) {
        return new java.sql.Timestamp(
            dateToConvert.getTime()).toLocalDateTime();
    }
}
