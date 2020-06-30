package com.brix.testbed.monitor.utility;

public class TestBedLogUtilityException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default Constructor.
	 * @return no return type
	 * @param Nothing
	 */
	public TestBedLogUtilityException(){
		super();
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String
	 */
	public TestBedLogUtilityException(String msg){
		super(msg);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String,Throwable
	 */
	public TestBedLogUtilityException(String msg, Throwable cause){
		super(msg, cause);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param Throwable
	 */
	public TestBedLogUtilityException(Throwable cause){
		super(cause);
	}
}
