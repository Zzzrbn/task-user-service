package com.zzzrbn.taskuserservice.entity;

import java.util.List;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Company {
	
    private Long id;
    private String name;
    private BigDecimal budget;
    private List<Long> usersIds;
    private List<Userrecord> users;	
}
