package com.technical.uni.repository;

import com.technical.uni.model.RoomStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomStatusDAO extends CrudRepository<RoomStatus, Long> {

    RoomStatus findByStatus(@Param("status") String status);
}
