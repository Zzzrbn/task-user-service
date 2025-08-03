package com.zzzrbn.taskuserservice.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.zzzrbn.taskuserservice.entity.Company;
import com.zzzrbn.taskuserservice.entity.CompanyDTO;

@Service
public interface CompanyService {

	public List<CompanyDTO> getAllCompanies();
	
	public void createCompany(CompanyDTO companyDTO);
	
	public CompanyDTO getCompany (Long id);
	
	public Company getCompanybyId (Long id);
	
	public void deleteCompany(Long id);
	
	public void updateCompany(Long id, CompanyDTO companyDTO);
	
}
