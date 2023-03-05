package com.bms.transactionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.transactionservice.model.TxnDao;
import com.bms.transactionservice.model.TxnDto;
import com.bms.transactionservice.service.CustomersClient;
import com.bms.transactionservice.service.TxnService;

@RestController
public class TxnController {

	@Autowired
	TxnService txnService;

	@Autowired
	CustomersClient client;

	@GetMapping("/transaction/hello")
	public String hello() {
		return "hello" ;
	}

	@PostMapping("/transaction")
	public ResponseEntity<TxnDao> transaction(@RequestBody TxnDto txn) throws Exception {
		return ResponseEntity.ok(txnService.saveTxn(txn));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<TxnDao>> getAllTransactions(){
		System.out.println("\n\n\n----------HISTORY-----------\n\n\n");
		return ResponseEntity.ok(txnService.getAllTxns());
	}
}
