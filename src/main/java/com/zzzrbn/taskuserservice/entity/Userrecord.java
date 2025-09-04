package com.zzzrbn.taskuserservice.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name="userrecords")
public class Userrecord{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "First name is required")
    @Column(name = "firstname", nullable = false)
    private String firstname;
    
    @NotBlank(message = "Last name is required")
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid phone number format")
    @Column(name = "phoneNumber", nullable = false, unique = false)
    private String phoneNumber;
    
    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Transient
    private Company company;

	@Override
	public int hashCode() {
		return Objects.hash(company, companyId, firstname, lastname, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Userrecord other = (Userrecord) obj;
		return Objects.equals(company, other.company) && Objects.equals(companyId, other.companyId)
				&& Objects.equals(firstname, other.firstname) && Objects.equals(lastname, other.lastname)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}    
}
