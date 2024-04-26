package com.bms.transactionservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bms.transactionservice.exceptions.BadTxnException;
import com.bms.transactionservice.model.DAOCustomer;
import com.bms.transactionservice.model.TxnDao;
import com.bms.transactionservice.model.TxnDto;
import com.bms.transactionservice.repository.TxnRepo;

@Service
public class TxnService {
	@Autowired
	CustomersClient customersClient;
	@Autowired
	TxnRepo txnRepo;
	
	@Autowired
	CustomerService service;

	public TxnDao saveTxn(TxnDto txn) throws Exception {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		
		ResponseEntity<DAOCustomer> customer = customersClient.getCustomer();
		Long currentBalance =customersClient.balance();

		System.out.println("----------"+customer.getBody().getCustomerId()+"---------------"); 
		//check for transaction
		if (txn.getAmount() > currentBalance) {
			throw new BadTxnException("Account balance is not sufficient.");
		}
		
		//check if reciever exists or not
		if (!customersClient.customerExists(txn.getReciever())) {
			throw new BadTxnException(txn.getReciever() + " is not found in our records.");
		}
		TxnDao newTxn = new TxnDao();

		newTxn.setAmount(txn.getAmount());
		newTxn.setSender(username);
		newTxn.setReciever(txn.getReciever());
		newTxn.setTransaction_time(LocalDateTime.now());
		newTxn.setStatus("SUCCESS");
		newTxn.setCustomer(customer.getBody());
		if(username.equals(newTxn.getReciever())) {
			throw new BadTxnException("Sender and Reciever are the same. Hence, transaction is not possible. ");
		}

		customersClient.deposit(txn.getReciever(), txn.getAmount());

		customersClient.withdraw(txn.getAmount());
	//	customer.getBody().setBalance(customer.getBody().getBalance()-txn.getAmount());
		service.saveCustomer(customer.getBody());

		return txnRepo.save(newTxn);

	}
	
	public List<TxnDao> getAllTxns(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return txnRepo.findAllTransactions(username);
	}
}
