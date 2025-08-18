package com.zzzrbn.taskuserservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zzzrbn.taskuserservice.entity.Company;

@FeignClient(name = "task-company-service", contextId = "task-company-service1")
public interface Feigncompany {
	
	@GetMapping("/company/{id}")
	public Company getCompany(@PathVariable Long id) ;
}
