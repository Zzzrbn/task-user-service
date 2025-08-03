package com.zzzrbn.taskuserservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzzrbn.taskuserservice.dao.UserrecordDAO;
import com.zzzrbn.taskuserservice.entity.UserDTO;
import com.zzzrbn.taskuserservice.entity.Userrecord;

import jakarta.transaction.Transactional;

@Service
public class UserrecordServiceImpl implements UserrecordService{
	
	@Autowired
	private UserrecordDAO userrecordDAO;

	@Override
	@Transactional
	public List<UserDTO> getAllUsersrecords() {
		return userrecordDAO.getAllUsersrecords();
	}

	@Override
	@Transactional
	public void createUserrecord(UserDTO userDTO) {
		userrecordDAO.createUserrecord(userDTO);
		
	}

	@Override
	@Transactional
	public UserDTO getUserrecord(Long id) {
		return userrecordDAO.getUserrecord(id);
	}

	@Override
	@Transactional
	public void deleteUserrecord(Long id) {
		userrecordDAO.deleteUserrecord(id);
		
	}

	@Override
	@Transactional
	public void updateUserrecord(Long id, UserDTO userDTO) {
		userrecordDAO.updateUserrecord(id, userDTO);
		
	}

	@Override
	public List<Userrecord> getUserrecordsbyId(Long id) {
		return userrecordDAO.getUserrecordsbyId(id);
	}
	
	

}
