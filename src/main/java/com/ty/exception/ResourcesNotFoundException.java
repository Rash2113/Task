package com.ty.exception;

public class ResourcesNotFoundException extends RuntimeException{

	private String message;
	
	public ResourcesNotFoundException() {
		
	}
	
	public ResourcesNotFoundException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.getMessage();
	}
}
