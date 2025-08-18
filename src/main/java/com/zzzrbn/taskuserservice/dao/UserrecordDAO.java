package com.zzzrbn.taskuserservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zzzrbn.taskuserservice.entity.Userrecord;

public interface UserrecordDAO extends JpaRepository<Userrecord, Long>{
	
	List<Userrecord> findByCompanyId(Long companyId);

//	public List<UserDTO> getAllUsersrecords();
//	
//	public void createUserrecord(UserDTO userDTO);
//	
//	public UserDTO getUserrecord (Long id);
//	
//	public List<Userrecord> getUserrecordsbyId (Long id);
//	
//	public void deleteUserrecord(Long id) throws Exception;
//	
//	public void updateUserrecord(Long id, UserDTO userDTO);
	
	
}
