package com.zzzrbn.taskuserservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzzrbn.taskuserservice.dao.CompanyDAO;
import com.zzzrbn.taskuserservice.entity.Company;
import com.zzzrbn.taskuserservice.entity.CompanyDTO;

import jakarta.transaction.Transactional;

//@Service
public class CompanyServiceImpl implements CompanyService {

//	@Autowired
//	private CompanyDAO companyDAO;
//
//	@Override
//	@Transactional
//	public List<CompanyDTO> getAllCompanies() {
//		return companyDAO.getAllCompanies();
//	}
//
//	@Override
//	@Transactional
//	public void createCompany(CompanyDTO companyDTO) {
//		companyDAO.createCompany(companyDTO);
//		
//	}
//
//	@Override
//	@Transactional
//	public CompanyDTO getCompany(Long id) {
//		return companyDAO.getCompany(id);
//	}
//
//	@Override
//	@Transactional
//	public void deleteCompany(Long id) throws Exception {
//		companyDAO.deleteCompany(id);		
//	}
//
//	@Override
//	@Transactional
//	public void updateCompany(Long id, CompanyDTO companyDTO) {
//		companyDAO.updateCompany(id, companyDTO);
//		
//	}
//
//	@Override
//	public Company getCompanybyId(Long id) {
//		return companyDAO.getCompanybyID(id);
//	}
//	
	
}
