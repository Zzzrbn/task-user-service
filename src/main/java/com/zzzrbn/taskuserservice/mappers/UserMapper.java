package com.zzzrbn.taskuserservice.mappers;

import org.springframework.stereotype.Component;

import com.zzzrbn.taskuserservice.entity.Company;
import com.zzzrbn.taskuserservice.entity.UserDTORequest;
import com.zzzrbn.taskuserservice.entity.UserDTOResponse;
import com.zzzrbn.taskuserservice.entity.Userrecord;

@Component
public class UserMapper {

	public Userrecord userrequestToUser(UserDTORequest userDTORequest) {
		Userrecord userrecord = new Userrecord();
		userrecord.setFirstname(userDTORequest.getFirstname());
		userrecord.setLastname(userDTORequest.getLastname());
		userrecord.setPhoneNumber(userDTORequest.getPhoneNumber());
		userrecord.setCompanyId(userDTORequest.getCompanyId());
		return userrecord;
	}

	public UserDTOResponse userToUserResponse(Userrecord userrecord, Company company) {
		UserDTOResponse response = new UserDTOResponse();
		response.setId(userrecord.getId());
		response.setFirstname(userrecord.getFirstname());
		response.setLastname(userrecord.getLastname());
		response.setPhoneNumber(userrecord.getPhoneNumber());
		response.setCompany(company);
		return response;
	}

	public void updateUserrecord(Userrecord userrecord, UserDTORequest userDTORequest) {
		userrecord.setFirstname(userDTORequest.getFirstname());
		userrecord.setLastname(userDTORequest.getLastname());
		userrecord.setPhoneNumber(userDTORequest.getPhoneNumber());
		if (userDTORequest.getCompanyId() != null) {
			userrecord.setCompanyId(userDTORequest.getCompanyId());
		} else {
			userrecord.setCompanyId(null);
		}
	}

}
