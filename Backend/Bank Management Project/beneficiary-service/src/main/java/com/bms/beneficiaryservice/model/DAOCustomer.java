package com.bms.beneficiaryservice.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bf_id")
	private Set<BfDao> bfs;

	public DAOCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DAOCustomer(Long customerId, String username, Set<BfDao> bfs) {
		super();
		this.customerId = customerId;
		this.username = username;
		this.bfs = bfs;
	}


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public Set<BfDao> getBfs() {
		return bfs;
	}


	public void setBfs(Set<BfDao> bfs) {
		this.bfs = bfs;
	}

	
//	public Long getBalance() {
//		return balance;
//	}
//
//	public void setBalance(Long balance) {
//		this.balance = balance;
//	}


}