package com.craft.knowledges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.craft.knowledges.model.Admin;



@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	Admin findByEmail(String email);
}
