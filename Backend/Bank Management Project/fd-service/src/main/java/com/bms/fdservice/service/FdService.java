package com.bms.fdservice.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bms.fdservice.model.DAOCustomer;
import com.bms.fdservice.model.FdDao;
import com.bms.fdservice.model.FdDto;
import com.bms.fdservice.repository.FdRepo;

@Service
public class FdService {
	@Autowired
	CustomersClient customersClient;
	@Autowired
	FdRepo fdRepo;
	@Autowired
	CustomerService customerService;
	
	public void breakFd(Long id) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<FdDao> fd=fdRepo.findById(id);
		Double breakFd=fd.get().getPrincipal();
		if(LocalDate.now().isAfter(fd.get().getMaturity_date())) {
			breakFd=fd.get().getMaturity_amount();
		}
		customersClient.deposit(username, Math.round(breakFd));
		ResponseEntity<DAOCustomer> customer = customersClient.getCustomer();
		customerService.saveCustomer(customer.getBody());
		fdRepo.deleteById(id);
	}

	public FdDao createFd(FdDto fd) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		ResponseEntity<DAOCustomer> customer = customersClient.getCustomer();
		int tenure=fd.getTenure();
		Double rate=getRate(tenure);
		Double principal=fd.getPrincipal();
		Double maturity=calc(principal,rate,tenure);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-mm-yyyy");
		LocalDate created_on = LocalDate.now();
		
		FdDao newFd=new FdDao(null, principal,rate,tenure,maturity,created_on,created_on.plusYears(tenure),customer.getBody(),username);
		customerService.saveCustomer(customer.getBody());
	//	newFd.setMaturity_amount(calc(fd.getPrincipal(),));
		return fdRepo.save(newFd);
		
	}

	private Double calc(Double principal, Double rate, int tenure) { 
		// TODO Auto-generated method stub
		Double maturity=principal+(principal*rate*tenure/100);
		return maturity;
	}

	private Double getRate(int tenure) {
		// TODO Auto-generated method stub
		Double rate = 0.0;
		if (tenure >= 1 && tenure < 3)
			rate = 4.0;
		else if (tenure >= 3 && tenure < 5)
			rate = 4.5;
		else if (tenure == 5)
			rate = 5.0;
		return rate;
	}
	
	
	public List<FdDao> getAll(){
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		return fdRepo.findAllByUsername(username);
		
	}

}
