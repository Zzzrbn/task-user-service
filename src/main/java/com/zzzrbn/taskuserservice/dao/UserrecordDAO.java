package com.zzzrbn.taskuserservice.dao;

import java.util.List;

import com.zzzrbn.taskuserservice.entity.UserDTO;
import com.zzzrbn.taskuserservice.entity.Userrecord;

public interface UserrecordDAO {

	public List<UserDTO> getAllUsersrecords();
	
	public void createUserrecord(UserDTO userDTO);
	
	public UserDTO getUserrecord (Long id);
	
	public List<Userrecord> getUserrecordsbyId (Long id);
	
	public void deleteUserrecord(Long id);
	
	public void updateUserrecord(Long id, UserDTO userDTO);
	
	
}
