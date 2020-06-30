package com.brix.testbed.monitor.role.dao;

public class RoleDaoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/****
	 * Default Constructor
	 * @return no return type
	 * @param no parameters
	 */
	public RoleDaoException() {
		super();
	}

	/*******
	 * Parameterized Constructor
	 * @param message
	 * @return no return type
	 */
	public RoleDaoException(String message) {
		super(message);
	}
	
	/*******
	 * Parameterized Constructor
	 * @param throwable
	 * @return no return type
	 */
	public RoleDaoException(Throwable throwable) {
		super(throwable);
	}
	
	/**********
	 * Parameterized Constructor
	 * @param message
	 * @param throwable
	 * @return no return type
	 */
	public RoleDaoException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
