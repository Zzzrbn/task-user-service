package com.zzzrbn.taskuserservice.service;

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
import com.zzzrbn.taskuserservice.exception.EntityNotFoundException;
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

	@Transactional
	public List<UserDTOResponse> getAllUsersrecords(int page, int size) {
		log.info("Returning all users. page: {}, size: {}", page, size);
		List<Userrecord> urList = paginatedUserrecords(page, size);
		return userMapper.userrecordsListToUserDTOResponses(urList, feigncompany);
	}

	@Transactional
	public UserDTOResponse createUserrecord(UserDTORequest userDTORequest) {
		Userrecord userrecord = userMapper.userrequestToUser(userDTORequest);
		userrecordDAO.save(userrecord);
		log.info("Created new user: {}", userrecord);
		return userMapper.userToUserResponse(userrecord, new Company());
	}

	@Transactional
	public UserDTOResponse updateUserrecord(Long id, UserDTORequest userDTORequest) {
		Optional<Userrecord> userrecord = java.util.Optional.empty();
		if (existUserrecordWithId(id)) {
			userrecord = userrecordDAO.findById(id);
			userMapper.updateUserrecord(userrecord.get(), userDTORequest);
			userrecordDAO.save(userrecord.get());
			log.info("Updated user with id {}: {}", id, userDTORequest);
		}
		return userMapper.userToUserResponse(userrecord.get(), new Company());
	}

	@Transactional
	public UserDTOResponse getUserrecord(Long id) {
		Optional<Userrecord> ur = java.util.Optional.empty();
		if (existUserrecordWithId(id)) {
			userrecordDAO.findById(id);
		}
		return getCompanyForUserDTOResponse(ur.get(), id);
	}

	@Transactional
	public void deleteUserrecord(Long id) {
		if (existUserrecordWithId(id)) {
			userrecordDAO.deleteById(id);
			log.info("User with id: {} removed", id);
		}
	}

	@Transactional
	public List<UserDTOResponse> findByCompanyId(Long companyId) {
		List<Userrecord> userrecords = userrecordDAO.findByCompanyId(companyId);
		log.info("Returned users by company id = {} : {}", companyId, userrecords.toString());
		return userMapper.userrecordsListToUserDTOResponses(userrecords, new Company());
	}

	private boolean existUserrecordWithId(Long id) {
		if (!userrecordDAO.existsById(id)) {
			log.info("User with id {} is not exist", id);
			throw new EntityNotFoundException("User", id);
		}
		return true;
	}

	private List<Userrecord> paginatedUserrecords(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Userrecord> urPage = userrecordDAO.findAll(pageable);
		List<Userrecord> urList = urPage.getContent();
		log.info("Returned paginated list of users : {}", urList.toString());
		return urList;
	}

	private UserDTOResponse getCompanyForUserDTOResponse(Userrecord ur, Long companyId) {
		Company company = feigncompany.getCompany(companyId);
		if (company != null) {
			log.info("Returned user by id = {} : {}", ur.getId(), ur.toString());
			return userMapper.userToUserResponse(ur, company);
		} else {
			log.info("Returned user by id = {} : with no company", ur.getId());
			return userMapper.userToUserResponse(ur, new Company());
		}
	}

}
