package com.zzzrbn.taskuserservice.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTORequest {
	
	@NotBlank(message = "First name is required")
    private String firstname;
	@NotBlank(message = "Last name is required")
    private String lastname;
	@Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid phone number format")
    private String phoneNumber;

	private Long companyId;
}
