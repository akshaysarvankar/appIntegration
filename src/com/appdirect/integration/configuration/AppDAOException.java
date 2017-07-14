package com.appdirect.integration.configuration;

public class AppDAOException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AppDAOException() {
		super();
	}
	
	public AppDAOException(String message) {
		super(message);
	}
	
	public AppDAOException(Throwable message) {
		super(message);
	}
	
	public AppDAOException(Throwable cause,String message) {
		super(message, cause);
	}

}
