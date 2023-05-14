package com.vikashBlog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vikashBlog.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String emailId);
	
	Optional<User> findByName(String name);
}
