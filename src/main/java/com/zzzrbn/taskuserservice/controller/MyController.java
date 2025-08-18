package com.zzzrbn.taskuserservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MyController {

	@Autowired
	private UserrecordService userrecordService;

	@GetMapping("/user")
	public ResponseEntity<List<UserDTOResponse>> getAllUsersrecords(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		List<UserDTOResponse> allUsersrecords = userrecordService.getAllUsersrecords(page, size);
		return ResponseEntity.status(HttpStatus.CREATED).body(allUsersrecords);
	}

	@GetMapping("/user/bycompany/{companyId}")
	public ResponseEntity<List<UserDTOResponse>> getUsersByCompanyId(@PathVariable Long companyId) {
		List<UserDTOResponse> users = userrecordService.findByCompanyId(companyId);
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserDTOResponse> getUserrecord(@PathVariable Long id) {
		UserDTOResponse userDTOResponse = userrecordService.getUserrecord(id);
		return ResponseEntity.status(HttpStatus.OK).body(userDTOResponse);
	}

	@PostMapping("/user")
	public ResponseEntity<UserDTORequest> createUserrecord(@RequestBody UserDTORequest userDTORequest) {
		userrecordService.createUserrecord(userDTORequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(userDTORequest);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<UserDTOResponse> updateUserrecord(@PathVariable Long id,
			@RequestBody UserDTORequest userDTORequest) throws Exception {
		try {
			UserDTOResponse userDTOResponse = userrecordService.updateUserrecord(id, userDTORequest);
			return ResponseEntity.status(HttpStatus.OK).body(userDTOResponse);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUserrecord(@PathVariable Long id) throws Exception {
		try {
			userrecordService.deleteUserrecord(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User with id = " + id + " was deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id = " + id + "does not exist");
		}
	}

}
