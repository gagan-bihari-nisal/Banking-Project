package com.bms.beneficiaryservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.beneficiaryservice.exceptions.BadBeneficiaryException;
import com.bms.beneficiaryservice.model.BfDao;
import com.bms.beneficiaryservice.model.BfDto;
import com.bms.beneficiaryservice.service.BeneficiaryService;

@RestController
public class BeneficiaryController {
	
	@Autowired
	BeneficiaryService service;
	
	@PostMapping("/addbeneficiary")
	public ResponseEntity<BfDao> addBeneficiary(@RequestBody @Valid BfDto bf,BindingResult bindingResults) throws BadBeneficiaryException{
		if(bindingResults.hasErrors()) {
			throw new BadBeneficiaryException(bindingResults.getAllErrors().stream().map(e -> e.getDefaultMessage())
					.reduce((s1, s2) -> s1 + " " + s2).orElse(""));
		}
		return ResponseEntity.ok(service.addBeneficiary(bf));
	}
	
	@GetMapping("/showAll")
	public ResponseEntity<List<BfDao>> getAll(){
		return  ResponseEntity.ok(service.getAll());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id")Long id){
		service.deleteBfById(id);
		return  ResponseEntity.ok("Deleted");
	}

}
