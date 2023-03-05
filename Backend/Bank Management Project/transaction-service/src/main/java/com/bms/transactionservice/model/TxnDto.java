package com.bms.transactionservice.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TxnDto {
	
	
	private Long amount;
	private String reciever;
	public TxnDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TxnDto(Long amount, String reciever) {
		super();
		this.amount = amount;
		this.reciever = reciever;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
		
}
