package com.technical.uni.repository;

import com.technical.uni.model.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDAO extends CrudRepository<Staff, Long> {}
