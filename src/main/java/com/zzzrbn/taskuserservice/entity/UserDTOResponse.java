package com.zzzrbn.taskuserservice.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class UserDTOResponse {
	
    private Long id;
	private String firstname;
	private String lastname;
	private String phoneNumber;
    @JsonIgnoreProperties({"usersIds", "users"})
	private Company company;
	
    @Override
	public int hashCode() {
		return Objects.hash(company, firstname, lastname, phoneNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTOResponse other = (UserDTOResponse) obj;
		return Objects.equals(company, other.company) && Objects.equals(firstname, other.firstname)
				&& Objects.equals(lastname, other.lastname) && Objects.equals(phoneNumber, other.phoneNumber);
	}
    
    

}