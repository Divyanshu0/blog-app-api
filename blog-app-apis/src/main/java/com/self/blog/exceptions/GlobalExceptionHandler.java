package com.self.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleException(ResourceNotFoundException ex){
		Map<String , Object> body = new HashMap<>();
		body.put("Id", ex.fieldValue);
		body.put("message"," Not found!");
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> body = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error->{
		   String field = ((FieldError)error).getField();
			body.put(field, error.getDefaultMessage());
		});
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

}
