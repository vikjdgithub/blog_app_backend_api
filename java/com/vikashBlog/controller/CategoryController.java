package com.vikashBlog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikashBlog.payload.ApiResponse;
import com.vikashBlog.payload.CategoryDto;
import com.vikashBlog.service.CategoryService;

@RestController
@RequestMapping("/get/apis")
public class CategoryController {

	@Autowired
	private CategoryService catServ;
	
	
	@PostMapping("/createCategory")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createdCategory = catServ.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateCategory/{categoryId}")
	public ResponseEntity<CategoryDto> upateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
		System.out.println(categoryId);
		CategoryDto updatedCategory = catServ.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		catServ.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/getCategory/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
		CategoryDto categoryDto = catServ.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> allCategory = catServ.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(allCategory,HttpStatus.ACCEPTED);
	}
	
	
	
	
	
}
