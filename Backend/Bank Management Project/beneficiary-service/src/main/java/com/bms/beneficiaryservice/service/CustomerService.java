package com.bms.beneficiaryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.beneficiaryservice.model.DAOCustomer;
import com.bms.beneficiaryservice.repository.CustomerRepo;

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