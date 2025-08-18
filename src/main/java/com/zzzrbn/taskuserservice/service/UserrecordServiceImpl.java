package com.zzzrbn.taskuserservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.zzzrbn.taskuserservice.client.Feigncompany;
import com.zzzrbn.taskuserservice.dao.UserrecordDAO;
import com.zzzrbn.taskuserservice.entity.Company;
import com.zzzrbn.taskuserservice.entity.UserDTORequest;
import com.zzzrbn.taskuserservice.entity.UserDTOResponse;
import com.zzzrbn.taskuserservice.entity.Userrecord;
import com.zzzrbn.taskuserservice.mappers.UserMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserrecordServiceImpl implements UserrecordService {

	private final UserrecordDAO userrecordDAO;

	private final Feigncompany feigncompany;

	private final UserMapper userMapper;

	@Override
	@Transactional
	public List<UserDTOResponse> getAllUsersrecords(int page, int size) {
		log.info("Get all users. Page: {}, size: {}", page, size);
		Pageable pageable = PageRequest.of(page, size);
		Page<Userrecord> urPage = userrecordDAO.findAll(pageable);
		List<Userrecord> urList = urPage.getContent();
		List<UserDTOResponse> userDTOResponses = new ArrayList<>();
		for (Userrecord ur : urList) {
			Company company = new Company();
			try {
				company = feigncompany.getCompany(ur.getCompanyId());
			} catch (Exception e) {
				log.info("Exception while get all users: {}.", e);
			}
			userDTOResponses.add(userMapper.userToUserResponse(ur, company));
		}
		return userDTOResponses;
	}

	@Override
	@Transactional
	public UserDTOResponse createUserrecord(UserDTORequest userDTORequest) {
		log.info("Creating new user: {}", userDTORequest);
		Userrecord userrecord = userMapper.userrequestToUser(userDTORequest);
		userrecordDAO.save(userrecord);
		return userMapper.userToUserResponse(userrecord,
				// company
				new Company());
	}

	@Override
	@Transactional
	public UserDTOResponse updateUserrecord(Long id, UserDTORequest userDTORequest) throws Exception {
		log.info("Updating user with id {}: {}", id, userDTORequest);
		if (!userrecordDAO.existsById(id)) {
			log.info("User with id {} is not exist ", id);
			throw new Exception("User with id " + id + " is not exist");
		}
		Optional<Userrecord> userrecord = userrecordDAO.findById(id);
		userMapper.updateUserrecord(userrecord.get(), userDTORequest);
		userrecordDAO.save(userrecord.get());
		return userMapper.userToUserResponse(userrecord.get(), new Company());
	}

	@Override
	public UserDTOResponse getUserrecord(Long id) {
		log.info("Get  user by id: {}", id);
		Optional<Userrecord> ur = userrecordDAO.findById(id);
		Company company = feigncompany.getCompany(ur.get().getCompanyId());
		if (company != null) {
			return userMapper.userToUserResponse(ur.get(), company);
		} else {
			return userMapper.userToUserResponse(ur.get(), new Company());
		}
	}

	@Override
	public void deleteUserrecord(Long id) throws Exception {
		log.info("Deleting user with id: {}", id);
		if (!userrecordDAO.existsById(id)) {
			log.info("User with id {} is not exist", id);
			throw new Exception("User with id " + id + " is not exist");
		}
		userrecordDAO.deleteById(id);
	}

	@Override
	public List<UserDTOResponse> findByCompanyId(Long companyId) {
		log.info("Get users by company id: {}", companyId);
		List<Userrecord> userrecords = userrecordDAO.findByCompanyId(companyId);
		List<UserDTOResponse> userDTOResponses = new ArrayList<UserDTOResponse>();
		for (Userrecord ur : userrecords) {
			userDTOResponses.add(userMapper.userToUserResponse(ur, new Company()));
		}
		return userDTOResponses;
	}
}
