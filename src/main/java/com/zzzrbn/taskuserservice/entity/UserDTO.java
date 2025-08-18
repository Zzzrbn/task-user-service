package com.zzzrbn.taskuserservice.entity;

import lombok.Data;

@Data
public class UserDTO {
	
    private Long id;
	private String firstname;
	private String lastname;
	private String phoneNumber;
    private Company company;

}
