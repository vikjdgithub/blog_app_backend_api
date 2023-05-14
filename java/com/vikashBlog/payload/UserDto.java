package com.vikashBlog.payload;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	private int id;
	
	
	@NotBlank
	private String name;
	
	@NotBlank
	@NotEmpty
	@Email(message = "email address is not valid")
	private String email;
	
	
	@NotBlank
	@Size(min = 3,max = 8,message = "password must be min 3 character and maximum 8 character")
	private String password;
	
	
	@NotBlank
	private String about;
		
}
