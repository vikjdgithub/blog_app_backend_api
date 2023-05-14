package com.vikashBlog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vikashBlog.config.AppConstant;
import com.vikashBlog.payload.ApiResponse;
import com.vikashBlog.payload.ImageResponse;
import com.vikashBlog.payload.PostDto;
import com.vikashBlog.payload.PostResponse;

import com.vikashBlog.service.PostService;

@RestController
@RequestMapping("/get/apis")
public class PostController {

	@Autowired
	private PostService postService;
	

	@PostMapping("/createPost/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
	}
	
	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		PostDto updatedPost = postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	@DeleteMapping("/deletePost/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfully!!",true),HttpStatus.OK);
	}
	
	@GetMapping("/getPost/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	@GetMapping("/getAllPost")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue = AppConstant.PAGE_NUMBER ,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false) String sortDir
			)
	{
	       PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	@GetMapping("/getPostByCategory/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> listOfPostByCategory = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(listOfPostByCategory,HttpStatus.OK);
	}
	
	@GetMapping("/getPostByuser/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> listOfPostByUser = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(listOfPostByUser,HttpStatus.OK);
	}
	
	@GetMapping("/searchPost/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByKeyword(@PathVariable String keyword){
    	List<PostDto> searchPosts = this.postService.searchPosts(keyword);
    	return new ResponseEntity<List<PostDto>>(searchPosts,HttpStatus.OK);
    }
	
	
	
}
