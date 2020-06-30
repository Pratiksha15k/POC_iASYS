package com.brix.testbed.monitor.user.dao;

public class UserDaoException extends Exception{
	
	/**
	 * Default Constructor.
	 * @return no return type
	 * @param Nothing
	 */
	public UserDaoException(){
		super();
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String
	 */
	public UserDaoException(String msg){
		super(msg);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String,Throwable
	 */
	public UserDaoException(String msg, Throwable cause){
		super(msg, cause);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param Throwable
	 */
	public UserDaoException(Throwable cause){
		super(cause);
	}

}
