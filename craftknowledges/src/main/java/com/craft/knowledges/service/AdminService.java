package com.craft.knowledges.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.craft.knowledges.dto.AdminRegistrationDto;
import com.craft.knowledges.model.Admin;


public interface AdminService extends UserDetailsService{
	Admin save(AdminRegistrationDto registrationDto);
}
