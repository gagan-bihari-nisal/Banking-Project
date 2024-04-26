package com.bms.authservice.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bms.authservice.exception.BadCustomerException;
import com.bms.authservice.model.CustomerDTO;
import com.bms.authservice.model.DAOCustomer;
import com.bms.authservice.reposiory.CustomerRepository;
@Service
public class CustomerService implements UserDetailsService {

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DAOCustomer customer = customerRepo.findByUsername(username);
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if (null != customer) {
			authorities.add(new SimpleGrantedAuthority(customer.getRole()));
			return new User(customer.getUsername(), customer.getPassword(), authorities);
		} else {
			throw new UsernameNotFoundException(username + " not found in our records.");
		}
	}
	
	public DAOCustomer getCustomer(String username) {
		return customerRepo.findByUsername(username);
	}
	
	public Long getBalance(String username) {
		DAOCustomer customer = customerRepo.findByUsername(username);
		return customer.getBalance();
	}
	
	public boolean checkUserExists(String username) {
		DAOCustomer customer = customerRepo.findByUsername(username);
		if(customer==null) {
			return false;
		}else {
			return true;
		}
	}

	public DAOCustomer saveCustomer(CustomerDTO customer) throws BadCustomerException {
		if(checkUserExists(customer.getUsername())==true) {
			throw new BadCustomerException(customer.getUsername()+" already exists in our records. Try with a different username");
		}
		DAOCustomer newCustomer = new DAOCustomer();
		newCustomer.setRole(customer.getRole());
		newCustomer.setFirstName(customer.getFirstName());
		newCustomer.setLastName(customer.getLastName());
		newCustomer.setGender(customer.getGender());
		newCustomer.setDob(customer.getDob());
		newCustomer.setFatherName(customer.getFatherName());
		newCustomer.setContact(customer.getContact());
		newCustomer.setUsername(customer.getUsername());
		newCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
		newCustomer.setBalance(1000L);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-mm-yyyy");
		LocalDate localDate = LocalDate.now();
		// newCustomer.setCreatedOn(localDate.parse("13-12-1999", dtf));
		newCustomer.setCreatedOn(localDate);
	//	newCustomer.setTransactions(new ArrayList<>());
		
		return customerRepo.save(newCustomer);
	}
	
	public DAOCustomer updateUser(DAOCustomer customer) {
		return customerRepo.save(customer);
	}

}
