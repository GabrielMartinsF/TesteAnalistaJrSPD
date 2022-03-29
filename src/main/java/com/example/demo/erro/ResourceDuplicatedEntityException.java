package com.example.demo.erro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceDuplicatedEntityException  extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5182805816937299590L;

	public ResourceDuplicatedEntityException(String message) {
		super(message);
	}
}
