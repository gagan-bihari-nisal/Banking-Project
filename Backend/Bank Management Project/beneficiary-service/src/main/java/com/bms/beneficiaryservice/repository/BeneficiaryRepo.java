package com.bms.beneficiaryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bms.beneficiaryservice.model.BfDao;

public interface BeneficiaryRepo extends JpaRepository<BfDao,Long>{
	@Query("SELECT SUM(u.percent) FROM BfDao u WHERE u.username=?1")
	Double availablePercent(String username);
	@Query("SELECT u FROM BfDao u WHERE u.username=?1")
	List<BfDao> findAllByUsername(String username);
}
