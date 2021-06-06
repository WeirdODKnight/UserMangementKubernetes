package com.craft.knowledges.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.craft.knowledges.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
