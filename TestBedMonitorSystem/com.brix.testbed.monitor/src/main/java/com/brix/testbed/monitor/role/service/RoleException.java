package com.brix.testbed.monitor.role.service;

public class RoleException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/******
	 * Default Constructor
	 * @param no parameter
	 * @return no return type
	 */
	public RoleException() {
		super();
	}
	
	/****
	 * Parameterized Constructor
	 * @param message
	 * @return no return type
	 */
	public RoleException(String message) {
		super(message);
	}
	
	/****
	 * Parameterized Constructor
	 * @param throwable
	 * @return no return type
	 */
	public RoleException(Throwable throwable) {
		super(throwable);
	}
	
	/****
	 * Parameterized Constructor
	 * @param message, throwable
	 * @return no return type
	 */
	public RoleException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
