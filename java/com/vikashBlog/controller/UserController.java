package com.vikashBlog.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikashBlog.payload.ApiResponse;
import com.vikashBlog.payload.UserDto;
import com.vikashBlog.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	//POST -create user
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto ){
		UserDto createdUser = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createdUser,HttpStatus.CREATED);
	}
	
	//PUT -Update user
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable int userId){
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}
	
	
	//GET -Get all user
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUserById( @PathVariable int userId) {
		System.out.println("in delete controller");
		this.userService.deleteUser(userId);
		return new  ResponseEntity(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<List<UserDto>> getAllUser(){
		
		return ResponseEntity.ok(this.userService.getAllUser());
	}
	
	@GetMapping("/getOneUser/{userId}")
	public ResponseEntity<UserDto> getOneUser(@PathVariable int userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
}
