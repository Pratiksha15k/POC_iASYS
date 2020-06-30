package com.brix.testbed.monitor.testbed.dao;

public class TestBedDaoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7824024745035871763L;
	/**
	 * Default Constructor.
	 * @return no return type
	 * @param Nothing
	 */
	public TestBedDaoException(){
		super();
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String
	 */
	public TestBedDaoException(String msg){
		super(msg);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String,Throwable
	 */
	public TestBedDaoException(String msg, Throwable cause){
		super(msg, cause);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param Throwable
	 */
	public TestBedDaoException(Throwable cause){
		super(cause);
	}

}
