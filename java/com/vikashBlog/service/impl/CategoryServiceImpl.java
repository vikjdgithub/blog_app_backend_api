package com.vikashBlog.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikashBlog.entity.Category;
import com.vikashBlog.exception.ResourceNotFoundException;
import com.vikashBlog.payload.CategoryDto;
import com.vikashBlog.repository.CategoryRepository;
import com.vikashBlog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.categoryDtoToCategory(categoryDto);
		Category savedCategory= categoryRepo.save(category);
		CategoryDto savedCategoryDto = this.categoryToCategoryDto(savedCategory);
		return savedCategoryDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		System.out.println(categoryId );
		Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "categoryId", categoryId));
		//category.setCategoryId(categoryDto.getCategoryId());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setDescription(categoryDto.getDescription());
		Category savedCategory = this.categoryRepo.save(category);
		
		return this.categoryToCategoryDto(savedCategory);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));

		this.categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));

		return this.categoryToCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> listOfCategoryDto = categories.stream().map(category->this.categoryToCategoryDto(category)).collect(Collectors.toList());
		return listOfCategoryDto;
	}
	
	public Category categoryDtoToCategory(CategoryDto categoryDto) {
          return this.modelMapper.map(categoryDto, Category.class);
	}
	
	public CategoryDto categoryToCategoryDto(Category category) {
        return this.modelMapper.map(category, CategoryDto.class);
	}

}
