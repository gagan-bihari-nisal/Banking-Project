package com.bms.historyservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bms.historyservice.model.HistoryModel;
import com.bms.historyservice.model.TxnDao;

@Service
public class HistoryService {

	@Autowired
	TxnClient client;

	public List<HistoryModel> getTxnHistory() {
		List<TxnDao> txns = client.getAllTransactions().getBody();
		List<HistoryModel> history = new ArrayList<>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		txns.forEach(t -> {
			String type = null;
			if (t.getReciever().equals(username)) {
				type = "DEPOSIT";
			} else {
				type = "WITHDRAW";
			}
			HistoryModel h = new HistoryModel(t.getTransaction_id(), username, t.getSender(), t.getReciever(), type,
					t.getAmount(), t.getStatus(),t.getTransaction_time());
			history.add(h);

		});

		return history;
	}

}
