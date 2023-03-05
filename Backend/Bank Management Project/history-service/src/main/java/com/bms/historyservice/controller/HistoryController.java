package com.bms.historyservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bms.historyservice.model.HistoryModel;
import com.bms.historyservice.service.HistoryService;
import com.bms.historyservice.service.TxnClient;

@RestController
public class HistoryController {
	
	@Autowired
	HistoryService service;
	
	@Autowired
	TxnClient client;
	
	@GetMapping
	public String hello() {
		return client.hello();
	}
	
	@GetMapping("/history")
	public ResponseEntity<List<HistoryModel>> getAllTransactions(){
		return ResponseEntity.ok(service.getTxnHistory());
	}

}
