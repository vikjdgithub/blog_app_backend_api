package com.vikashBlog.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	//private Integer categoryId;
	
	@NotBlank
	@Size(min=4,max=100)
	private String categoryTitle;
	@NotBlank
	@Size(min=10)
	private String description;
}
