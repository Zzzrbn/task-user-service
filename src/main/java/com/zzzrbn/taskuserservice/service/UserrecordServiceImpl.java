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

	public List<UserDTOResponse> getAllUsersrecords(int page, int size) {
		log.info("Returning all users. page: {}, size: {}", page, size);
		List<Userrecord> urList = paginatedUserrecords(page, size);
		return userMapper.userrecordsListToUserDTOResponses(urList, feigncompany);
	}

	@Transactional
	public UserDTOResponse createUserrecord(UserDTORequest userDTORequest) {
		Userrecord userrecord = userMapper.userrequestToUser(userDTORequest);
		Userrecord savedUserrecord = userrecordDAO.save(userrecord);
		Company company = getCompanyForUser(savedUserrecord.getCompanyId());
		log.info("Created new user: {}", savedUserrecord);
		return userMapper.userToUserResponse(savedUserrecord, company);
	}

	@Transactional
	public UserDTOResponse updateUserrecord(Long id, UserDTORequest userDTORequest) {
		Userrecord userrecord = userrecordDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("User", id));
		userMapper.updateUserrecord(userrecord, userDTORequest);
		Userrecord updatedUserrecord = userrecordDAO.save(userrecord);
		Company company = getCompanyForUser(updatedUserrecord.getCompanyId());
		log.info("Updated user with id {}", id);
		return userMapper.userToUserResponse(updatedUserrecord, company);
	}

	public UserDTOResponse getUserrecord(Long id) {
		Userrecord userrecord = userrecordDAO.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User", id));
		Company company = getCompanyForUser(userrecord.getCompanyId());
		log.info("Returned user by id = {} : {}", id, userrecord);
		return userMapper.userToUserResponse(userrecord, company);
	}

	@Transactional
	public void deleteUserrecord(Long id) {
        if (!userrecordDAO.existsById(id)) {
            throw new EntityNotFoundException("User", id);
        }
			userrecordDAO.deleteById(id);
			log.info("User with id: {} removed", id);		
	}

	@Transactional
	public List<UserDTOResponse> findByCompanyId(Long companyId) {
		List<Userrecord> userrecords = userrecordDAO.findByCompanyId(companyId);
		log.info("Returned {} user(s) by company id = {}", userrecords.size(), companyId);
		return userMapper.userrecordsListToUserDTOResponses(userrecords, new Company());
	}
	
	@Transactional
    public void removeCompanyIdFromUsers(Long companyId) {
        int updatedCount = userrecordDAO.removeCompanyIdFromUsers(companyId);
        log.info("Removed companyId {} from {} users", companyId, updatedCount);
    }

	private List<Userrecord> paginatedUserrecords(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Userrecord> urPage = userrecordDAO.findAll(pageable);
		List<Userrecord> urList = urPage.getContent();
		log.info("Returned paginated list of users : {}", urList.toString());
		return urList;
	}

	
	private Company getCompanyForUser(Long companyId) {
			Company company = feigncompany.getCompany(companyId);
			return company != null ? company : new Company();
	}
}
    
