package com.javaguides.productservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;

@Data
@ResponseStatus
public class ProductCustomException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String errorCode;
	
	public ProductCustomException(String errorMessage,String errorCode) {
		super(errorMessage);
		this.errorCode=errorCode;
		
	}
	
}
