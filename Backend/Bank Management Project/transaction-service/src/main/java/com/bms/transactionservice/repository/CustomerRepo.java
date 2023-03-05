package com.bms.transactionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.transactionservice.model.DAOCustomer;

public interface CustomerRepo extends JpaRepository<DAOCustomer,Long> {
 
}
