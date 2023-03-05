package com.bms.transactionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bms.transactionservice.model.TxnDao;

public interface TxnRepo extends JpaRepository<TxnDao,Long> {
	@Query("SELECT u FROM TxnDao u WHERE u.reciever = ?1 or u.sender=?1 order by transaction_time desc")
	public List<TxnDao> findAllTransactions(String username);
}
