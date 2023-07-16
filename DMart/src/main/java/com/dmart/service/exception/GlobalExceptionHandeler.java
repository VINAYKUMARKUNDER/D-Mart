package com.dmart.service.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dmart.utlDto.ResponceModel;




@ControllerAdvice
public class GlobalExceptionHandeler {

	@ExceptionHandler(ResponseNotFoundException.class)
	public ResponseEntity<ResponceModel> ResponseNotFoundExceptionHandeler(ResponseNotFoundException rs) {

		ResponceModel msg = new ResponceModel(rs.getMessage(), HttpStatus.NOT_FOUND,404, LocalDateTime.now());
		return new ResponseEntity<ResponceModel>(msg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponceModel> methodArgumentNotValidException(MethodArgumentNotValidException rs){
		
		Map<String, String>  error = new HashMap<>();
		 rs.getBindingResult().getAllErrors().forEach((err)->{
			 String fieldName = ((FieldError) err).getField();
			 String massage = err.getDefaultMessage();
			 error.put(fieldName, massage);
		 });
		
		 ResponceModel msg = new ResponceModel(error, HttpStatus.BAD_REQUEST,400, LocalDateTime.now());
		return new ResponseEntity<ResponceModel>(msg, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ResponceModel> dataIntegrityViolationException(DataIntegrityViolationException rs){
		String internalMassage="Duplicate entry!!!   data is already store in database!!                                ."
				+rs.getMostSpecificCause().toString();
		ResponceModel msg = new ResponceModel(internalMassage, HttpStatus.BAD_REQUEST,400, LocalDateTime.now());
		return new ResponseEntity<ResponceModel>(msg, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidDataAccessResourceUsageException.class)
	public ResponseEntity<ResponceModel> invalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException rs){
		ResponceModel msg = new ResponceModel(rs.getMessage(), HttpStatus.BAD_REQUEST,400, LocalDateTime.now());
		return new ResponseEntity<ResponceModel>(msg, HttpStatus.BAD_REQUEST);
	}

}

