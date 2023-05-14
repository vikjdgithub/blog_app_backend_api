package com.vikashBlog.payload;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.vikashBlog.entity.Category;
import com.vikashBlog.entity.Comment;
import com.vikashBlog.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {


	private Integer postId;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private UserDto user;
	
	private CategoryDto category;
	
	private Set<CommentDto> comments=new HashSet<>();
	
	
	
}
