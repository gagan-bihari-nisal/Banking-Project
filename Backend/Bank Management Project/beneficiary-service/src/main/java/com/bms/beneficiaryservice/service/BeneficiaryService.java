package com.bms.beneficiaryservice.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bms.beneficiaryservice.exceptions.BadBeneficiaryException;
import com.bms.beneficiaryservice.model.BfDao;
import com.bms.beneficiaryservice.model.BfDto;
import com.bms.beneficiaryservice.model.DAOCustomer;
import com.bms.beneficiaryservice.repository.BeneficiaryRepo;

@Service
public class BeneficiaryService {
	@Autowired
	CustomersClient customersClient;
	
	@Autowired
	CustomerService customerService;
	@Autowired
	BeneficiaryRepo bfRepo;
	
	public BfDao addBeneficiary(BfDto bf) throws BadBeneficiaryException {
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
	//	Double availPercent=100-bfRepo.availablePercent(username);
		Double availPercent =bfRepo.availablePercent(username)==null?100:100-bfRepo.availablePercent(username);
		Double percent=bf.getPercent();
		if(percent>availPercent)
			throw new BadBeneficiaryException("Percent can only be less than or equal to "+availPercent);
		String fname=bf.getFirstName();
		String lname=bf.getLastName();
		String relation=bf.getRelation();
		String phone=bf.getPhone();
		LocalDate addedOn = LocalDate.now();
		
		ResponseEntity<DAOCustomer> customer = customersClient.getCustomer();
		BfDao newBf=new BfDao(null,username,fname,lname,relation,phone,addedOn,percent,customer.getBody());
		customerService.saveCustomer(customer.getBody());
		return bfRepo.save(newBf);
		
		
	}
	
	public List<BfDao> getAll(){
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		return bfRepo.findAllByUsername(username);
		
	}
	
	public void deleteBfById(Long id) {
		 bfRepo.deleteById(id);
	}
}
