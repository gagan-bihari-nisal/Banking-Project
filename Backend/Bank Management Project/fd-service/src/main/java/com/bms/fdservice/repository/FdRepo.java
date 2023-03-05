package com.bms.fdservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bms.fdservice.model.FdDao;

public interface FdRepo extends JpaRepository<FdDao,Long> {
	@Query("SELECT u FROM FdDao u WHERE u.username=?1")
	List<FdDao> findAllByUsername(String username);
}
