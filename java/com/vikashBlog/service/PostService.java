package com.vikashBlog.service;

import java.util.List;


import com.vikashBlog.entity.Post;
import com.vikashBlog.payload.PostDto;
import com.vikashBlog.payload.PostResponse;

public interface PostService {

	//create post
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//Update post
	PostDto  updatePost(PostDto postDto,Integer postId);

	//Delete post by Id
	void deletePost(Integer postId);
	
	//get All post
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//get one post by id
	PostDto  getPostById(Integer postId);
	
	
	//get post by category
	List<PostDto > getPostByCategory(Integer categoryId);

    //get post by user
	List<PostDto > getPostByUser(Integer postId);


	//search post by keyword
	List<PostDto > searchPosts(String keyword);

}
