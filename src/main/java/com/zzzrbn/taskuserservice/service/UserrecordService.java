package com.zzzrbn.taskuserservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zzzrbn.taskuserservice.entity.UserDTORequest;
import com.zzzrbn.taskuserservice.entity.UserDTOResponse;

@Service
public interface UserrecordService {
	
	public List<UserDTOResponse> getAllUsersrecords(int page, int size);
	
	public UserDTOResponse createUserrecord(UserDTORequest userDTORequest);
	
	public UserDTOResponse getUserrecord (Long id);
	
	public void deleteUserrecord(Long id) throws Exception;
	
	public UserDTOResponse updateUserrecord(Long id
			, UserDTORequest userDTORequest) throws Exception;
	
	public List<UserDTOResponse> findByCompanyId(Long companyId);
}
