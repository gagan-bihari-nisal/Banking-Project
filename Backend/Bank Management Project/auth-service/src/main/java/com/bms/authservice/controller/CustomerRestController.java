package com.bms.authservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.authservice.exception.BadCustomerException;
import com.bms.authservice.filter.TokenGeneratorFilter;
import com.bms.authservice.model.CustomerDTO;
import com.bms.authservice.model.DAOCustomer;
import com.bms.authservice.model.JWTRequest;
import com.bms.authservice.model.JWTResponse;
import com.bms.authservice.service.CustomerService;

@RestController
public class CustomerRestController {

	@Autowired
	CustomerService customerService;

	@Autowired
	AuthenticationManager authentication;

	@Autowired
	TokenGeneratorFilter jwtUtil;

	@GetMapping("/hello")
	public String hello() {
		System.out.println("\n\n\n\n\n\n============IN HELLO==========\n\n\n\n\n\n");
		return "hello auth service";
	}

	@GetMapping("/customer")
	public ResponseEntity<DAOCustomer> getCustomer() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return ResponseEntity.ok(customerService.getCustomer(username));
	}

	@GetMapping("/balance")
	public Long balance() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return customerService.getBalance(username);
	}
 
	@PostMapping("/register") 
	public ResponseEntity<DAOCustomer> registerCustomer(@RequestBody @Valid CustomerDTO customer,
			BindingResult bindingResult) throws BadCustomerException {
		if (bindingResult.hasErrors()) {
			throw new BadCustomerException(bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage())
					.reduce((s1, s2) -> s1 + " " + s2).orElse(""));
		}
		System.out.println("============IN REGISTER==========");
		return ResponseEntity.ok(customerService.saveCustomer(customer));
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginCustomer(@RequestBody JWTRequest request) throws BadCustomerException {
		System.out.println("\n\n\n\n\n\n============IN LOGIN==========\n\n\n\n\n\n");
		
		UserDetails userDetails = customerService.loadUserByUsername(request.getUsername());
		try {
			authentication.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
					request.getPassword(), userDetails.getAuthorities()));
		} catch (Exception e) {
			throw new BadCustomerException("Authentication Failed. Try Again.");
		}
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JWTResponse(jwt));
	}

	@GetMapping("/{username}/exists")
	public boolean customerExists(@PathVariable("username") String username) {
		if (customerService.checkUserExists(username)) {
			return true;
		}
		return false;
	}

	@PutMapping("deposit/{username}/{amount}")
	public void deposit(@PathVariable("username") String username, @PathVariable("amount") Long amount) {
		System.out.println("The user is " + username);
		DAOCustomer customer = customerService.getCustomer(username);
		customer.setBalance(customer.getBalance() + amount);
		customerService.updateUser(customer);
	}
	
	

	@PutMapping("withdraw/{amount}")
	public void withdraw(@PathVariable("amount") Long amount) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		DAOCustomer customer = customerService.getCustomer(username);
		System.out.println(customer.getBalance() + "===" + amount + " ====" + username);
		customer.setBalance(customer.getBalance() - amount);
		customerService.updateUser(customer);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<DAOCustomer> updateProfile(@RequestBody @Valid DAOCustomer customer,
			BindingResult bindingResult) throws BadCustomerException{
		if (bindingResult.hasErrors()) {
			throw new BadCustomerException(bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage())
					.reduce((s1, s2) -> s1 + " " + s2).orElse(""));
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		DAOCustomer newCustomer = customerService.getCustomer(username);
		newCustomer.setFirstName(customer.getFirstName());
		newCustomer.setLastName(customer.getLastName());
		newCustomer.setGender(customer.getGender());
		newCustomer.setDob(customer.getDob());
		newCustomer.setFatherName(customer.getFatherName());
		newCustomer.setContact(customer.getContact());
		return ResponseEntity.ok(customerService.updateUser(newCustomer));
	}

}
