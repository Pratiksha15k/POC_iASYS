package com.brix.testbed.monitor.user.service;

public class UserException extends Exception{

	/**
	 * Default Constructor.
	 * @return no return type
	 * @param Nothing
	 */
	public UserException(){
		super();
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String
	 */
	public UserException(String msg){
		super(msg);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String,Throwable
	 */
	public UserException(String msg, Throwable cause){
		super(msg, cause);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param Throwable
	 */
	public UserException(Throwable cause){
		super(cause);
	}
}
