package com.bms.fdservice.model;

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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fd_id")
	private Set<FdDao> fds;

	public DAOCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOCustomer(Long customerId, String username, Set<FdDao> fds) {
		super();
		this.customerId = customerId;
		this.username = username;
		//this.balance = balance;
		this.fds = fds;
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

//	public Long getBalance() {
//		return balance;
//	}
//
//	public void setBalance(Long balance) {
//		this.balance = balance;
//	}

	public Set<FdDao> getFds() {
		return fds;
	}

	public void setFds(Set<FdDao> fds) {
		this.fds = fds;
	}

}