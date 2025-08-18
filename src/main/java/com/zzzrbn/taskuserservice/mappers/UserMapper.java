package com.zzzrbn.taskuserservice.mappers;

import org.springframework.stereotype.Component;

import com.zzzrbn.taskuserservice.entity.Company;
import com.zzzrbn.taskuserservice.entity.UserDTO;
import com.zzzrbn.taskuserservice.entity.UserDTORequest;
import com.zzzrbn.taskuserservice.entity.UserDTOResponse;
import com.zzzrbn.taskuserservice.entity.Userrecord;

@Component
public class UserMapper {

	public UserDTO toDTO(Userrecord userrecord, Company company) {
        if (userrecord == null) {
            return null;
        }
        
        UserDTO dto = new UserDTO();
        dto.setId(userrecord.getId());
        dto.setFirstname(userrecord.getFirstname());
        dto.setLastname(userrecord.getFirstname());
        dto.setPhoneNumber(userrecord.getPhoneNumber());
        dto.setCompany(company);
//        dto.setCompanyId(userrecord.getCompanyId());
        return dto;
    }

    public Userrecord toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        
        Userrecord userrecord = new Userrecord();
        userrecord.setId(userDTO.getId());
        userrecord.setFirstname(userDTO.getFirstname());
        userrecord.setLastname(userDTO.getFirstname());
        userrecord.setPhoneNumber(userDTO.getPhoneNumber());
        
        if (userDTO.getCompany() != null) {
            userrecord.setCompanyId(userDTO.getCompany().getId()
            		);
        }
//        if (userDTO.getCompanyId()!=null)
//        {
//        	userrecord.setCompanyId(userDTO.getCompanyId());
//        }
        
        return userrecord;
    }
    
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
        
           
//        try {
//            Company company = companyClient.getCompanyById(user.getCompanyId());
            
//        } catch (Exception e) {
//            log.error("Error fetching company details for companyId: {}", user.getCompanyId(), e);
//        }
        
        return response;
        }
    
    public void updateUserrecord (Userrecord userrecord, UserDTORequest userDTORequest)
    {
    	userrecord.setFirstname(userDTORequest.getFirstname());
		userrecord.setLastname(userDTORequest.getLastname());
		userrecord.setPhoneNumber(userDTORequest.getPhoneNumber());
		if (userDTORequest.getCompanyId() != null)
		{
		userrecord.setCompanyId(userDTORequest.getCompanyId());
		}
		else 
		{
		userrecord.setCompanyId(null);
		}
    }
      
}
