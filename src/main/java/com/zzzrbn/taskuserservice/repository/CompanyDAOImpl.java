package com.zzzrbn.taskuserservice.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zzzrbn.taskuserservice.dao.CompanyDAO;
import com.zzzrbn.taskuserservice.entity.Company;
import com.zzzrbn.taskuserservice.entity.CompanyDTO;
import com.zzzrbn.taskuserservice.entity.Userrecord;
import com.zzzrbn.taskuserservice.mappers.CompanyMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CompanyDAOImpl implements CompanyDAO {

	@Autowired
	private EntityManager entityManager;
	
	private final CompanyMapper companyMapper;

	
	@Override
	public List<CompanyDTO> getAllCompanies() {
		Session session = entityManager.unwrap(Session.class);
		Query<Company> query = session.createQuery("from Company", Company.class);
		List<Company> allCompanies = query.getResultList();
		List<CompanyDTO> allCompanyDTOs = new ArrayList<>();
		for (Company company : allCompanies)
		{
			Query<Userrecord> query1 = session.createQuery("from Userrecord where companyId =: company_id", Userrecord.class);
			query1.setParameter("company_id", company.getId());
			List<Userrecord> userrecords = query1.getResultList();
			company.setUsers(userrecords);
			allCompanyDTOs.add(companyMapper.toDTO(company, userrecords));
		}
		
		return allCompanyDTOs;
	}

	@Override
	public void createCompany(CompanyDTO companyDTO) {
		Session session = entityManager.unwrap(Session.class);
		
		Company company = companyMapper.toEntity(companyDTO);
//		List<Userrecord> userrecords = 
//				//userrecordService.getUserrecordsbyId(companyDTO.getId());
		
		Query<Userrecord> query = session.createQuery("from Userrecord where companyId =: company_id", Userrecord.class);
		query.setParameter("company_id", company.getId());
		List<Userrecord> userrecords = query.getResultList();
		company.setUsers(userrecords);
		session.persist(company);
	}

	@Override
	public CompanyDTO getCompany(Long id) {
		Session session = entityManager.unwrap(Session.class);
		Company company = session.get(Company.class, id);
		Query<Userrecord> query = session.createQuery("from Userrecord where companyId =: company_id", Userrecord.class);
		query.setParameter("company_id", company.getId());
		List<Userrecord> userrecords = query.getResultList();
		CompanyDTO companyDTO = companyMapper.toDTO(company,
				userrecords
				//userrecordService.getUserrecordsbyId(company.getId())
				
				);
		return companyDTO;
	}
	
	@Override
	public Company getCompanybyID(Long id) {
		Session session = entityManager.unwrap(Session.class);
		Company company = session.get(Company.class, id);
		return company;
	}

	@Override
	public void deleteCompany(Long id) {
		Session session = entityManager.unwrap(Session.class);
		session.remove(session.get(Company.class, id));
		
	}

	@Override
	public void updateCompany(Long id, CompanyDTO companyDTO) {
		Session session = entityManager.unwrap(Session.class);
		Company company = session.get(Company.class, id);
		company.setBudget(companyDTO.getBudget());
		company.setName(companyDTO.getName());
		company.setUsers(companyDTO.getUsers());
		//session.merge(companyDTO);
		
	}
	

}
