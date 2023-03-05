package com.bms.transactionservice.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class DAOCustomer { 

	@Id
	private Long customerId;
	private String username;

	//private Long balance;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "transaction_id")
	private Set<TxnDao> txns;

	public DAOCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

//	public Long getBalance() {
//		return balance;
//	}
//
//	public void setBalance(Long balance) {
//		this.balance = balance;
//	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<TxnDao> getTxns() {
		return txns;
	}

	public void setTxns(Set<TxnDao> txns) {
		this.txns = txns;
	}

	public DAOCustomer(Long customerId, String username,Set<TxnDao> txns) {
		super();
		this.customerId = customerId;
		this.username = username;
	//	this.balance = balance;
		this.txns = txns;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
