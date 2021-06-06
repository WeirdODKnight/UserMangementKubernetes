package com.craft.knowledges.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.craft.knowledges.dto.AdminRegistrationDto;
import com.craft.knowledges.model.User;
import com.craft.knowledges.service.UserService;


@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	// display list of users
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstname", "asc", model);		
	}
	
	@GetMapping("/showNewUserForm")
	public String showNewUserForm(Model model) {
		// create model attribute to bind form data
		User user = new User();
		model.addAttribute("user", user);
		return "new_user";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		// save user to database
		RestTemplate restTemplate = new RestTemplate();
		//userService.saveUser(user);
		String firstname = user.getFirstname();
		String lastname = user.getLastname();
		String department = user.getDepartment();
		restTemplate.getForObject("http://localhost:8084/addUser/"+firstname+"/"+lastname+"/"+department, String.class);
		return "redirect:/";
	}
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") User user) {
		// save user to database
		//RestTemplate restTemplate = new RestTemplate();
		userService.saveUser(user);
			return "redirect:/";
	}
	
	
	

	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get user from the service
		User user = userService.getUserById(id);
		
		// set user as a model attribute to pre-populate the form
		model.addAttribute("user", user);
		return "update_user";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.userService.deleteUserById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<User> listUsers = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listUsers", listUsers);
		return "index";
	}
}
