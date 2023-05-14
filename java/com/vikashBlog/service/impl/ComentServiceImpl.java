package com.vikashBlog.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikashBlog.entity.Comment;
import com.vikashBlog.entity.Post;
import com.vikashBlog.exception.ResourceNotFoundException;
import com.vikashBlog.payload.CommentDto;
import com.vikashBlog.repository.CommentRepository;
import com.vikashBlog.repository.PostRepository;
import com.vikashBlog.service.CommentService;

@Service
public class ComentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired	
	private PostRepository postRepo;
	
	@Autowired
	private ModelMapper modelMap;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
	Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId", postId));
	 	
	Comment comment = this.modelMap.map(commentDto, Comment.class);
	
	comment.setPost(post);
	
	Comment savedComment = this.commentRepo.save(comment);
	
	CommentDto savedCommentDto = this.modelMap.map(savedComment, CommentDto.class);
	return savedCommentDto;
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment com=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "commentId", commentId));
		
		this.commentRepo.delete(com);

	}

}
