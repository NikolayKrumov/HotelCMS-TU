package com.technical.uni.repository;

import com.technical.uni.model.Guest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Repository
public interface GuestDAO extends CrudRepository<Guest, Long> {



    @Query(value = "UPDATE guests SET :column = :value", nativeQuery = true)
    Guest updateCustomer(@Param("column") String column,
                            @Param("value") String value);

    @Query(value = "SELECT * FROM guests WHERE id = :id", nativeQuery = true)
    Guest getCustomerById(@Param("id") Long id);

    //Spravka1
    @Query(value = "SELECT * FROM guests WHERE first_name = :firstName", nativeQuery = true)
    Guest getCustomerByFirstName(@Param("firstName") String firstName);

    //Spravka2
    @Query(value = "SELECT * FROM guests WHERE last_name = :lastName", nativeQuery = true)
    Guest getCustomerByLastName(@Param("lastName") String lastName);

    //Spravka4
    @Query(value = "SELECT * FROM guests g "
        + "LEFT JOIN hotels h on h.id = g.hotel_id and h.name = :hotelName WHERE first_name = :firstName", nativeQuery = true)
    Guest getCustomerInfoByCustomerFirstNameAndHotelName(@Param ("firstName") String firstName,
                                                         @Param("hotelName") String hotelName);

    @Query(value = "SELECT * FROM guests g "
        + "LEFT JOIN hotels h on h.id = g.hotel_id and h.name = :hotelName WHERE g.registration_date between :startDate AND :endDate",
           nativeQuery = true)
    Iterable<Guest> getGuestByHotelAndRegistrationDateBetween(@Param("hotelName") String hotelName,
                                                    @Param("startDate") String startDate,
                                                    @Param("endDate") String endDate);

}
