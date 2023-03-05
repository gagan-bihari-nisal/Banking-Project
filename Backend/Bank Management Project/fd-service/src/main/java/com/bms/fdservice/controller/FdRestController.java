package com.bms.fdservice.controller;

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

import com.bms.fdservice.exceptions.BadFdException;
import com.bms.fdservice.model.FdDao;
import com.bms.fdservice.model.FdDto;
import com.bms.fdservice.service.FdService;

@RestController
public class FdRestController {
	@Autowired
	FdService service;
	
	@PostMapping("/create")
	ResponseEntity<?> createFd(@RequestBody @Valid FdDto fd,BindingResult bindingResult) throws BadFdException{
		int tenure=fd.getTenure();
		if(tenure!=1 && tenure!=3 && tenure!=5) {
			throw new BadFdException("Tenure can only be 1,3 or 5 years.");
		}
		if(bindingResult.hasErrors()) {
			throw new BadFdException(bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage())
					.reduce((s1, s2) -> s1 + " " + s2).orElse(""));
		}
		return ResponseEntity.ok(service.createFd(fd));
	}
	
	@DeleteMapping("/breakfd/{id}")
	public ResponseEntity<String> breakFd(@PathVariable("id") Long id){
		service.breakFd(id);
		return  ResponseEntity.ok("Fixed Deposit is withdrawn");
	}
	
	@GetMapping("/showAll")
	public ResponseEntity<List<FdDao>> getAll(){
		return  ResponseEntity.ok(service.getAll());
	}

}
