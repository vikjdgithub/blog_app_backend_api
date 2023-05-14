package com.vikashBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikashBlog.payload.ApiResponse;
import com.vikashBlog.payload.CommentDto;
import com.vikashBlog.service.CommentService;

@RestController
@RequestMapping("/get/apis")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/createComment/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
		CommentDto createdComment = this.commentService.createComment(commentDto, postId);
	
          return new ResponseEntity<CommentDto>(createdComment,HttpStatus.CREATED);	
	}
	
	@DeleteMapping("/deleteComment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully",true),HttpStatus.OK);
	}
}
