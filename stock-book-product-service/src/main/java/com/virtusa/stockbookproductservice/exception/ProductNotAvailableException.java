package com.virtusa.stockbookproductservice.exception;

public class ProductNotAvailableException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ProductNotAvailableException(String message) {
		super(message);
	}

	
}
