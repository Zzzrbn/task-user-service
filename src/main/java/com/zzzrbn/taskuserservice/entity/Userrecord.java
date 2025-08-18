package com.zzzrbn.taskuserservice.entity;

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
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
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
    @Column(name = "phoneNumber", nullable = false, unique = true)
    private String phoneNumber;
    
    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Transient
    private Company company;                     
    
}
