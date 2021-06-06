package com.craft.knowledge.controller;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;

import com.craft.knowledge.model.Admin;
import com.craft.knowledge.model.Role;
import com.craft.knowledge.repository.AdminRepository;



@RestController
public class AdminRegistrationController {
	

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private AdminRepository adminRepository;
	
	public AdminRegistrationController(AdminRepository adminRepository) {
	super();
	this.adminRepository = adminRepository;
}

	@RequestMapping("/registerAdmin/{firstname}/{lastname}/{email}/{password}")
	public String registerAdmin(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname, 
			@PathVariable("email") String email, @PathVariable("password") String password)
	{
		Admin admin = new Admin();
		admin.setFirstname(firstname);
		admin.setLastname(lastname);
		admin.setEmail(email);
		Admin adminEmail = adminRepository.findByEmail(email);
		admin.setPassword(passwordEncoder.encode(password));
		admin.setRoles(Arrays.asList(new Role("ROLE_USER")));
		if(adminEmail==null) {
			adminRepository.save(admin);
			return "successfully registered";
		}else {
			return "email found";
		}
		
	}

}
