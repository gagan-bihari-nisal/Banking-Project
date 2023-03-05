package com.bms.transactionservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.transactionservice.model.DAOCustomer;
import com.bms.transactionservice.repository.CustomerRepo;

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
