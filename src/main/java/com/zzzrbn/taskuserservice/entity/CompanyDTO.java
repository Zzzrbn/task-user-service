package com.zzzrbn.taskuserservice.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

//@Data
public class CompanyDTO {

//    private Long id;
//    private String name;
//    private BigDecimal budget;
//    @JsonIgnoreProperties({"id", "companyId", "company"})
//    private List<Userrecord> users;
	
    private Long id;
	private String name;
	private BigDecimal budget;
    @JsonIgnoreProperties({
    	//"id", 
    	"companyId", "company"})
    private List<Userrecord> users;
	
    @Override
	public String toString() {
		return "CompanyDTO [id=" + id + ", name=" + name + ", budget=" + budget + ", users=" + users + "]";
	}
    
    
	
}
