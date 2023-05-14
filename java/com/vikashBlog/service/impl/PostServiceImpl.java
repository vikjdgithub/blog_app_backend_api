package com.vikashBlog.service.impl;

import java.util.Date;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vikashBlog.entity.Category;
import com.vikashBlog.entity.Post;
import com.vikashBlog.entity.User;
import com.vikashBlog.exception.ResourceNotFoundException;
import com.vikashBlog.payload.CategoryDto;
import com.vikashBlog.payload.PostDto;
import com.vikashBlog.payload.PostResponse;
import com.vikashBlog.payload.UserDto;
import com.vikashBlog.repository.CategoryRepository;
import com.vikashBlog.repository.PostRepository;
import com.vikashBlog.repository.UserRepo;
import com.vikashBlog.service.PostService;import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ModelMapper modelMap;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "userId",userId));
		
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "categoryId",categoryId));
		
			Post post = this.modelMap.map(postDto, Post.class);
			

			post.setImageName("default.jpg");
			post.setAddedDate(new Date());
			post.setCategory(category);
			post.setUser(user);
			Post save = this.postRepo.save(post);

	    return this.modelMap.map(save, PostDto.class);
		
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		this.postRepo.save(post);
		return this.modelMap.map(post,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId",postId));

		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy,String sortDir) {
		
		Sort sort= (sortDir.equalsIgnoreCase("asc"))?sort=Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
//		if (sortDir.equalsIgnoreCase("asc")) {
//			sort=Sort.by(sortBy).ascending();
//		} else {
//            sort=Sort.by(sortBy).descending();
//		}
		
		PageRequest p = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> posts = pagePost.getContent();
		List<PostDto> listOfPostDto = posts.stream().map((post)->this.modelMap.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse= new PostResponse();
		postResponse.setContent(listOfPostDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","postId", postId));
		return this.modelMap.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("post", "categoryId", categoryId));
		
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto> postsDto = posts.stream().map((post)->this.modelMap.map(post, PostDto.class)).collect(Collectors.toList());
	return postsDto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "userId", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postsDto = posts.stream().map((post)->this.modelMap.map(post, PostDto.class)).collect(Collectors.toList());
	return postsDto;
		
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
	List<PostDto> postsDtos = posts.stream().map((post)->this.modelMap.map(post, PostDto.class)).collect(Collectors.toList());
	
	return postsDtos;
	}

	

}
