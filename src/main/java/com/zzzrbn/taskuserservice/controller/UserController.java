package com.zzzrbn.taskuserservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzzrbn.taskuserservice.entity.UserDTORequest;
import com.zzzrbn.taskuserservice.entity.UserDTOResponse;
import com.zzzrbn.taskuserservice.service.UserrecordService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	@Autowired
	private UserrecordService userrecordService;

	@GetMapping("/user")
	public List<UserDTOResponse> getAllUsersrecords(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		log.info("Getting all users (page= {}, size={})", page, size);
		List<UserDTOResponse> allUsersrecords = userrecordService.getAllUsersrecords(page, size);
		return allUsersrecords;
	}

	@GetMapping("/user/bycompany/{companyId}")
	public List<UserDTOResponse> getUsersByCompanyId(@PathVariable Long companyId) {
		log.info("Getting users by company id = {}", companyId);
		List<UserDTOResponse> users = userrecordService.findByCompanyId(companyId);
		return users;
	}

	@GetMapping("/user/{id}")
	public UserDTOResponse getUserrecord(@PathVariable Long id) {
		log.info("Getting user with id = {}", id);
		UserDTOResponse userDTOResponse = userrecordService.getUserrecord(id);
		return userDTOResponse;
	}

	@PostMapping("/user")
	public UserDTORequest createUserrecord(@RequestBody @Valid UserDTORequest userDTORequest){
		log.info("Adding new user: {}", userDTORequest.toString());
		userrecordService.createUserrecord(userDTORequest);
		return userDTORequest;
	}

	@PutMapping("/user/{id}")
	public UserDTOResponse updateUserrecord(@PathVariable Long id,
			@RequestBody @Valid UserDTORequest userDTORequest) {
		log.info("Updating user with id = {} : {}", id, userDTORequest.toString());
		UserDTOResponse userDTOResponse = userrecordService.updateUserrecord(id, userDTORequest);
		return userDTOResponse;
	}

	@DeleteMapping("/user/{id}")
	public String deleteUserrecord(@PathVariable Long id) {
		log.info("Deleting user with id = {}", id);
		userrecordService.deleteUserrecord(id);
		return "User with id = " + id + " was deleted";		
	}

}
