package com.bms.fdservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.fdservice.model.DAOCustomer;

public interface CustomerRepo extends JpaRepository<DAOCustomer,Long> {
 
}
