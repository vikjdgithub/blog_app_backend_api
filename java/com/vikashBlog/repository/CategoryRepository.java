package com.vikashBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikashBlog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
