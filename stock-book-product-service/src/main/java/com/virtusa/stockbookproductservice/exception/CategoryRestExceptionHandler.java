package com.virtusa.stockbookproductservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CategoryRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<CategoryErrorResponse> handleException(Exception e)
	{
		CategoryErrorResponse error = new CategoryErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<CategoryErrorResponse>(error,HttpStatus.BAD_REQUEST);
		
	}
}
