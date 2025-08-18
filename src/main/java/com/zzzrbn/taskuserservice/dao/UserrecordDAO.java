package com.zzzrbn.taskuserservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zzzrbn.taskuserservice.entity.Userrecord;

public interface UserrecordDAO extends JpaRepository<Userrecord, Long>{
	List<Userrecord> findByCompanyId(Long companyId);
}
