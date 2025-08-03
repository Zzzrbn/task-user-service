package com.zzzrbn.taskuserservice.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zzzrbn.taskuserservice.controller.Feigncompany;
import com.zzzrbn.taskuserservice.dao.UserrecordDAO;
import com.zzzrbn.taskuserservice.entity.Company;
import com.zzzrbn.taskuserservice.entity.UserDTO;
import com.zzzrbn.taskuserservice.entity.Userrecord;
import com.zzzrbn.taskuserservice.mappers.CompanyMapper;
import com.zzzrbn.taskuserservice.mappers.UserMapper;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserrecordDAOImpl implements UserrecordDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private Feigncompany feigncompany;
	
//    private final Feigncompany companyServiceClient;
//
//    @Autowired
//    public public UserrecordDAOImpl(Feigncompany companyServiceClient) {
//        this.companyServiceClient = companyServiceClient;
//    }
	private final CompanyMapper companyMapper;
	
	private final UserMapper userMapper;

	@Override
	public List<UserDTO> getAllUsersrecords() {
		Session session = entityManager.unwrap(Session.class);
		Query<Userrecord> query = session.createQuery("from Userrecord ", Userrecord.class);
		List<Userrecord> allUserrecords = query.getResultList();
		List<UserDTO> allUserDTOs = new ArrayList<>();
		for (Userrecord userrecord : allUserrecords)
		{
			allUserDTOs.add(userMapper.toDTO(userrecord, 
					//session.get(Company.class, userrecord.getCompanyId())
				 companyMapper.toEntity(feigncompany.getCompany(userrecord.getCompanyId()))					
					));
		}
		
		return allUserDTOs;
	}

	@Override
	public void createUserrecord(UserDTO userDTO) {
		Session session = entityManager.unwrap(Session.class);
		Userrecord userrecord = userMapper.toEntity(userDTO);
		//userrecord.setCompany(userDTO.getCompany());
//		userrecord.setCompany(
//				session.get(Company.class, userrecord.getCompanyId())
//				//companyService.getCompanybyId(userrecord.getCompanyId())
//				);
				
		session.persist(userrecord);		
	}

	@Override
	public UserDTO getUserrecord(Long id) {
		Session session = entityManager.unwrap(Session.class);
		Userrecord userrecord = session.get(Userrecord.class, id);
		Company company = 
				//companyService.getCompanybyId(userrecord.getCompanyId());
				session.get(Company.class, userrecord.getCompanyId());
		return userMapper.toDTO(userrecord, company);
	}

	@Override
	public void deleteUserrecord(Long id) {
		Session session = entityManager.unwrap(Session.class);
		session.remove(session.get(Userrecord.class, id));			
	}

	@Override
	public void updateUserrecord(Long id, UserDTO userDTO) {
		Session session = entityManager.unwrap(Session.class);
		Userrecord userrecord = session.get(Userrecord.class, id);
			
			userrecord.setFirstname(userDTO.getFirstname());
			userrecord.setLastname(userDTO.getLastname());
			userrecord.setPhoneNumber(userDTO.getPhoneNumber());
			userrecord.setCompanyId(userDTO.getCompanyId());
			System.out.println(userrecord);
			//session.merge(userDTO);
					
	}

	@Override
	public List<Userrecord> getUserrecordsbyId(Long id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Userrecord> query = session.createQuery("select * from Userrecord where company_id := companyid", Userrecord.class);
		query.setParameter("companyid", id);
		List<Userrecord> UserrecordsbyId = query.getResultList();
		return UserrecordsbyId;
	}
	
}
