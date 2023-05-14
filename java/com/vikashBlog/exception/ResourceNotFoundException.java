package com.vikashBlog.exception;

public class ResourceNotFoundException  extends  RuntimeException{

	String resourceName;
	String fieldName;
	long fieldValue;
	String keyword;
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s:%s", resourceName,fieldName,fieldValue ));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public ResourceNotFoundException(String resourceName, String fieldName, String keyword) {
		super(String.format("%s not found with %s:%s", resourceName,fieldName,keyword ));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.keyword = keyword;
	}
	
	
	
}
