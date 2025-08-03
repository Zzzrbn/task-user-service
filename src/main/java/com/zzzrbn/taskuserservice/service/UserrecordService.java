package com.zzzrbn.taskuserservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zzzrbn.taskuserservice.entity.UserDTO;
import com.zzzrbn.taskuserservice.entity.Userrecord;

@Service
public interface UserrecordService {
	
	public List<UserDTO> getAllUsersrecords();
	
	public void createUserrecord(UserDTO userDTO);
	
	public UserDTO getUserrecord (Long id);
	
	public List<Userrecord> getUserrecordsbyId (Long id);
	
	public void deleteUserrecord(Long id);
	
	public void updateUserrecord(Long id, UserDTO userDTO);

}
