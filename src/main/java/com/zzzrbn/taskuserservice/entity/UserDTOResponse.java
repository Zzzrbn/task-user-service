package com.zzzrbn.taskuserservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class UserDTOResponse {
	
    private Long id;
	private String firstname;
	private String lastname;
	private String phoneNumber;
    @JsonIgnoreProperties({
    	//"id", 
    	"usersIds", "users"})
	private Company company;

}