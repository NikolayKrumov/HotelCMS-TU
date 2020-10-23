package com.technical.uni.repository;

import com.technical.uni.model.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelDAO extends CrudRepository<Hotel, Long> {}
