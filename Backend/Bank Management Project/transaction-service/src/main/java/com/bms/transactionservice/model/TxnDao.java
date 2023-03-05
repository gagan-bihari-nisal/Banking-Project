package com.bms.transactionservice.model;

import java.time.LocalDateTime;

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
@Table(name="transactions")
public class TxnDao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long transaction_id;
	private Long amount;
	private String sender;
	//private String senderName;
	private String reciever;
//	private String recieverName;
	private LocalDateTime transaction_time;  
	private String status;
	@ManyToOne(targetEntity=DAOCustomer.class)
	@JoinColumn(name="customerId")
	@JsonProperty(access = Access.WRITE_ONLY)
	private DAOCustomer customer;

	
	public TxnDao() {
		super();
	}


	public TxnDao(Long transaction_id, Long amount, String sender, String reciever, LocalDateTime transaction_time,
			String status,DAOCustomer customer) {
		super();
		this.transaction_id = transaction_id;
		this.amount = amount;
		this.sender = sender;
		this.reciever = reciever;
		this.transaction_time = transaction_time;
		this.status = status;
		this.customer=customer;
	}


	public Long getTransaction_id() {
		return transaction_id;
	}


	public void setTransaction_id(Long transaction_id) {
		this.transaction_id = transaction_id;
	}


	public Long getAmount() {
		return amount;
	}


	public void setAmount(Long amount) {
		this.amount = amount;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getReciever() {
		return reciever;
	}


	public void setReciever(String reciever) {
		this.reciever = reciever;
	}


	public LocalDateTime getTransaction_time() {
		return transaction_time;
	}


	public void setTransaction_time(LocalDateTime transaction_time) {
		this.transaction_time = transaction_time;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public DAOCustomer getCustomer() {
		return customer;
	}


	public void setCustomer(DAOCustomer customer) {
		this.customer = customer;
	}



	
	
	
}
