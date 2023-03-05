package com.bms.beneficiaryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.beneficiaryservice.model.DAOCustomer;

public interface CustomerRepo extends JpaRepository<DAOCustomer,Long> {
 
}
