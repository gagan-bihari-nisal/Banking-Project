package com.bms.historyservice.model;

import java.time.LocalDateTime;

public class HistoryModel {
	private Long transaction_id;
	private String username;
	private String sender;
	private String reciever;
	private String type;
	private Long amount;
	private String status;
	private LocalDateTime transaction_time;

	public HistoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LocalDateTime getTransaction_time() {
		return transaction_time;
	}


	public void setTransaction_time(LocalDateTime transaction_time) {
		this.transaction_time = transaction_time;
	}


	public HistoryModel(Long transaction_id, String username, String sender, String reciever, String type, Long amount,
			String status, LocalDateTime transaction_time) {
		super();
		this.transaction_id = transaction_id;
		this.username = username;
		this.sender = sender;
		this.reciever = reciever;
		this.type = type;
		this.amount = amount;
		this.status = status;
		this.transaction_time = transaction_time;
	}


	public Long getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(Long transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
