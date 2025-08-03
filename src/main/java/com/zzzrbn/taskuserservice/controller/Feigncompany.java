package com.zzzrbn.taskuserservice.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zzzrbn.taskuserservice.entity.CompanyDTO;

@FeignClient(name = "task-company-service")
public interface Feigncompany {
	
	@GetMapping("/company")
	public List<CompanyDTO> showAllCompanies();
			
	@GetMapping("/company/{id}")
	public CompanyDTO getCompany(@PathVariable Long id) ;
	
	@PostMapping("/company")
	public CompanyDTO addNewCompany(@RequestBody CompanyDTO companyDTO);
	
	@PutMapping("/company/{id}")
	public CompanyDTO updateCompany(@PathVariable Long id
			, @RequestBody CompanyDTO companyDTO);
	
	@DeleteMapping("/company/{id}")
	public String deleteCompany(@PathVariable Long id);
	
}
