package com.dmart.service.exception;


public class ResponseNotFoundException extends RuntimeException{
	
	private String respnseName;
	private String responseMsg;
	private Long id;
	public ResponseNotFoundException(String respnseName, String responseMsg, Long id) {
		super(String.format("%s is not found with %s : %s", respnseName,responseMsg, id));
		this.respnseName = respnseName;
		this.responseMsg = responseMsg;
		this.id = id;
	}
	
	

}

