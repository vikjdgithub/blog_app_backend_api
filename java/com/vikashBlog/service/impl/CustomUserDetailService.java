package com.vikashBlog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vikashBlog.entity.User;
import com.vikashBlog.exception.ResourceNotFoundException;
import com.vikashBlog.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByName(username).orElseThrow(()->new ResourceNotFoundException("user", "username", username));
		
		
		
		
		return user;
	}

}
