package com.bms.fdservice.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="fd")
public class FdDao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long fd_id;
	private Double principal;
	private Double rate;
	private int tenure;
	private Double maturity_amount;
	private LocalDate created_on;
	private LocalDate maturity_date;
	@ManyToOne(targetEntity=DAOCustomer.class)
	@JoinColumn(name="customerId")
	@JsonProperty(access = Access.WRITE_ONLY)
	private DAOCustomer customer;
	private String username;
	public FdDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LocalDate getMaturity_date() {
		return maturity_date;
	}

	public void setMaturity_date(LocalDate maturity_date) {
		this.maturity_date = maturity_date;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public FdDao(Long fd_id, Double principal, Double rate, int tenure, Double maturity_amount, LocalDate created_on,
			LocalDate maturity_date, DAOCustomer customer, String username) {
		super();
		this.fd_id = fd_id;
		this.principal = principal;
		this.rate = rate;
		this.tenure = tenure;
		this.maturity_amount = maturity_amount;
		this.created_on = created_on;
		this.maturity_date = maturity_date;
		this.customer = customer;
		this.username = username;
	}

	public Long getFd_id() {
		return fd_id;
	}
	public void setFd_id(Long fd_id) {
		this.fd_id = fd_id;
	}
	public Double getPrincipal() {
		return principal;
	}
	public void setPrincipal(Double principal) {
		this.principal = principal;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public Double getMaturity_amount() {
		return maturity_amount;
	}
	public void setMaturity_amount(Double maturity_amount) {
		this.maturity_amount = maturity_amount;
	}
	public LocalDate getCreated_on() {
		return created_on;
	}
	public void setCreated_on(LocalDate created_on) {
		this.created_on = created_on;
	}
	public DAOCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(DAOCustomer customer) {
		this.customer = customer;
	}

	
}
