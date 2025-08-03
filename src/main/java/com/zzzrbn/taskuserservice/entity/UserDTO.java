package com.zzzrbn.taskuserservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class UserDTO {
	
	@JsonIgnore
    private Long id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    @JsonIgnoreProperties({"id", "users", "usersIds"})
    private Company company;
    //@JsonIgnore
    private Long companyId;
}
