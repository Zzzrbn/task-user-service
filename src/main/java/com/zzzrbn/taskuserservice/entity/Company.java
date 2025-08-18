package com.zzzrbn.taskuserservice.entity;

import java.util.List;

import java.math.BigDecimal;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
//@NoArgsConstructor
@Data
//@Entity
//@Table(name="companies")
public class Company {
	
    private Long id;
    private String name;
    private BigDecimal budget;
    private List<Long> usersIds;
    private List<Userrecord> users;
	
//  @Id
//  //@ToString.Exclude
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  //@JsonIgnore
//  @Column(name = "id")
//  private Long id;
//  
//  @Column(name = "name")
//  private String name;
//  
//  @Column(name = "budget")
//  private BigDecimal budget;
//  
//  @ElementCollection
//  @CollectionTable(name = "company_users", joinColumns = @JoinColumn(name = "company_id"))
//  @Column(name = "userrecord_id")
//  private List<Long> usersIds;
//  
//  @Transient
//  private List<Userrecord> users;
    
}
