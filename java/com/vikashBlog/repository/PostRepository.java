package com.vikashBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikashBlog.entity.Category;
import com.vikashBlog.entity.Post;
import com.vikashBlog.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category user);
	
	List<Post> findByTitleContaining(String title);
}
