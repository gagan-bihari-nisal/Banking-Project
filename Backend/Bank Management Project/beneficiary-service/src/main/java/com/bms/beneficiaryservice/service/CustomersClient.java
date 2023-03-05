package com.bms.beneficiaryservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.bms.beneficiaryservice.model.DAOCustomer;

@FeignClient("auth")
public interface CustomersClient {

	@GetMapping("/hello")
	public String hello();

	@GetMapping("/customer")
	public ResponseEntity<DAOCustomer> getCustomer();

	@GetMapping("/{username}/exists")
	public boolean customerExists(@PathVariable("username") String username);

	@PutMapping("deposit/{username}/{amount}")
	public void deposit(@PathVariable("username") String username, @PathVariable("amount") Long amount);

	@PutMapping("withdraw/{amount}")
	public void withdraw(@PathVariable("amount") Long amount);

}