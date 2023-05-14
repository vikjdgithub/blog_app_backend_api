package com.vikashBlog.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.websocket.server.ServerEndpoint;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vikashBlog.entity.User;
import com.vikashBlog.exception.ResourceNotFoundException;
import com.vikashBlog.payload.UserDto;
import com.vikashBlog.repository.UserRepo;
import com.vikashBlog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dToToUser(userDto);
		
		User saveduser= userRepo.save(user);
		return this.UserToUserDto(saveduser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int  userId)  {
		
		User user = userRepo.findById(userId).orElseThrow(() ->new ResourceNotFoundException("user","Id",userId));
		
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		
		return this.UserToUserDto(updatedUser);
	}

	@Override
	public UserDto getUserById(int userId) {
		User user = userRepo.findById(userId).orElseThrow(() ->new ResourceNotFoundException("user","Id",userId));
		
		UserDto userDto = this.UserToUserDto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepo.findAll();
		System.out.println("in get all users");
		List<UserDto> listofAllUserDto = users.stream().map(user->this.UserToUserDto(user)).collect(Collectors.toList());
		
		return listofAllUserDto;
	}

	@Override
	public void deleteUser(int userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
	
		this.userRepo.delete(user);
	
	}
	
	public User dToToUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
//	   user.setId(userDto.getId());
//	   user.setName(userDto.getName());
//	   user.setEmail(userDto.getEmail());
//	   user.setPassword(userDto.getPassword());
//	   user.setAbout(userDto.getAbout());
	   return user;
	}
	
	public UserDto UserToUserDto(User user) {
		return this.modelMapper.map(user, UserDto.class);
	}

}
