package com.bms.fdservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.fdservice.model.DAOCustomer;
import com.bms.fdservice.repository.CustomerRepo;

@Service
public class CustomerService {
	@Autowired
	CustomerRepo repo;
	
	@Autowired
	CustomersClient client;
	
	public DAOCustomer saveCustomer(DAOCustomer customer) {
		return repo.save(customer);
	}
}