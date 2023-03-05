package com.bms.historyservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.bms.historyservice.model.TxnDao;


@FeignClient(name="transactions")
public interface TxnClient {
	@GetMapping("/all")
	public ResponseEntity<List<TxnDao>> getAllTransactions();
	@GetMapping("/transaction/hello")
	public String hello();
}
