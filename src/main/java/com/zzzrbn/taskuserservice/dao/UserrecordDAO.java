package com.zzzrbn.taskuserservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zzzrbn.taskuserservice.entity.Userrecord;

public interface UserrecordDAO extends JpaRepository<Userrecord, Long>{
	List<Userrecord> findByCompanyId(Long companyId);
	
    @Modifying
    @Query("UPDATE Userrecord u SET u.companyId = NULL WHERE u.companyId = :companyId")
    int removeCompanyIdFromUsers(@Param("companyId") Long companyId);
}
