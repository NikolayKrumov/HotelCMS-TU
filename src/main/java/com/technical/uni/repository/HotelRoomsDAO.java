package com.technical.uni.repository;

import com.technical.uni.model.Hotel;
import com.technical.uni.model.HotelRooms;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRoomsDAO  extends CrudRepository<HotelRooms, Long> {

    HotelRooms findByHotel(@Param("hotel") Hotel hotel);
}
