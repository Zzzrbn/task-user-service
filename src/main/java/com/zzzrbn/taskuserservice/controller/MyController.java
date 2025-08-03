package com.zzzrbn.taskuserservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzzrbn.taskuserservice.entity.CompanyDTO;
import com.zzzrbn.taskuserservice.entity.UserDTO;
import com.zzzrbn.taskuserservice.service.CompanyService;
import com.zzzrbn.taskuserservice.service.UserrecordService;



@RestController
@RequestMapping("/")
public class MyController {

	@Autowired
	private UserrecordService userrecordService;
	
//	@Autowired
//	private CompanyService companyService;
	
	@GetMapping("/user")
	public List<UserDTO> showAllUsersrecords() {
		List<UserDTO> allUsersrecords = userrecordService.getAllUsersrecords();
		return allUsersrecords;
	}
	
	@GetMapping("/user/{id}")
	public UserDTO getUserrecord(@PathVariable Long id) {
		UserDTO userDTO = userrecordService.getUserrecord(id);
		return userDTO;
	}
	
	@PostMapping("/user")
	public UserDTO createUserrecord(@RequestBody UserDTO userDTO)
	{
		userrecordService.createUserrecord(userDTO);
		return userDTO;
	}
	
	@PutMapping("/user/{id}")
	public UserDTO updateUserrecord(@PathVariable Long id, @RequestBody UserDTO userDTO)
	{
		userrecordService.updateUserrecord(id, userDTO);
		return userDTO;		
	}
		
	@DeleteMapping("/user/{id}")
	public String deleteUserrecord(@PathVariable Long id) throws Exception
	{
		UserDTO userDTO = userrecordService.getUserrecord(id);
		if (userDTO == null)
		{
			throw new Exception("There is no user with id = "+id+" in Database");
		}
		userrecordService.deleteUserrecord(id);
		return "User with id = " + id + " was deleted";
	}
//*****************************************************************************************************	
	
	
//	@GetMapping("/company")
//	public List<CompanyDTO> showAllCompanies() {
//		List<CompanyDTO> allCompanies = companyService.getAllCompanies();
//		return allCompanies;
//	}
//	
//	
//	
//	@GetMapping("/company/{id}")
//	public CompanyDTO getCompany(@PathVariable Long id) {
//		CompanyDTO companyDTO = companyService.getCompany(id);
//		return companyDTO;
//	}
//	
//	
//	
//	@PostMapping("/company")
//	public CompanyDTO addNewCompany(@RequestBody CompanyDTO companyDTO)
//	{
//		companyService.createCompany(companyDTO);
//		return companyDTO;
//		
//	}
//	
//	@PutMapping("/company/{id}")
//	public CompanyDTO updateCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO)
//	{
//		companyService.updateCompany(id, companyDTO);
//		return companyDTO;
//		
//	}
//	
//	@DeleteMapping("/company/{id}")
//	public String deleteCompany(@PathVariable Long id) throws Exception
//	{
//		CompanyDTO companyDTO = companyService.getCompany(id);
//		
//		if (companyDTO == null)
//		{
//			throw new Exception("There is no company with id = "+id+" in Database");
//		}
//		companyService.deleteCompany(id);
//		return "Company with id = " + id + " was deleted";
//	}
//	

	
}
