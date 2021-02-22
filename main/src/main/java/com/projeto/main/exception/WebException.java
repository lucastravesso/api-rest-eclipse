package com.projeto.main.exception;

import org.springframework.http.HttpStatus;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class WebException extends RuntimeException implements CodeException{


	private static final long serialVersionUID = 3406636182783807331L;

	  private final HttpStatus status;
	  private final String message;
	  
	  public WebException(HttpStatus status, String message, String code) {
	    super(status.value() + " : " + message);
	    this.status = status;
	    this.message = message;
	  }


	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}



}
