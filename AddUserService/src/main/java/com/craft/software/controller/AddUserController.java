package com.craft.software.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.craft.software.model.User;
import com.craft.software.repository.UserRepository;

import java.util.Arrays;




@RestController
public class AddUserController {
	
	

	private UserRepository userRepository;
	
	public AddUserController(UserRepository userRepository) {
	super();
	this.userRepository = userRepository;
}

	@RequestMapping("/addUser/{firstname}/{lastname}/{department}")
	public String addUser(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname, 
			@PathVariable("department") String department)
	{
		User user = new User();
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setDepartment(department);
		
		userRepository.save(user);
		return "successfully added";
	}

}