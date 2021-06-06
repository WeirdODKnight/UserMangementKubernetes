package com.craft.software.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.craft.software.model.User;




@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
