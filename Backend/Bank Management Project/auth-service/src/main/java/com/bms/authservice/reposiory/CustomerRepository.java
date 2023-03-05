package com.bms.authservice.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.authservice.model.DAOCustomer;

public interface CustomerRepository extends JpaRepository<DAOCustomer,Long>{
	public DAOCustomer findByUsername(String username);
	public boolean existsByUsername(String username);
	//public Long getBalanceByUsername(String username);
	
}
