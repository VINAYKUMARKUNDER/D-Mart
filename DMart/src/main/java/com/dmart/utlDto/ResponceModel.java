package com.dmart.utlDto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponceModel<T> {
	
	private T responce;
	private HttpStatus status;
	private Integer statusCode;
	private LocalDateTime DateAndTime;
	

}

