package com.craft.knowledges.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.craft.knowledges.dto.AdminRegistrationDto;
import com.craft.knowledges.service.AdminService;




@Controller
@RequestMapping("/registration")
public class AdminRegistrationController {
	
	
	
	RestTemplate restTemplate = new RestTemplate();

	private AdminService adminService;

	public AdminRegistrationController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}
	
	@ModelAttribute("admin")
    public AdminRegistrationDto adminRegistrationDto() {
        return new AdminRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerAdminAccount(@ModelAttribute("admin") AdminRegistrationDto registrationDto) {
		//adminService.save(registrationDto);
        String passcode = registrationDto.getPassword();
        String firstname = registrationDto.getFirstname();
        String lastname = registrationDto.getLastname();
        String email = registrationDto.getEmail();
		String a = restTemplate.getForObject("http://localhost:8085/registerAdmin/"+firstname+"/"+lastname+"/"+email+"/"+passcode, String.class);
		if(a.contains("successfully registered")) {
			return "redirect:/registration?success";
	} else {
		System.out.println(a);
	        return "redirect:/registration?error";	
	}
	}
}
