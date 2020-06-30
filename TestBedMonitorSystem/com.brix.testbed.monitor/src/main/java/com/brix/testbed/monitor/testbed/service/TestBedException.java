package com.brix.testbed.monitor.testbed.service;

public class TestBedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6826799416783947945L;
	/**
	 * Default Constructor.
	 * @return no return type
	 * @param Nothing
	 */
	public TestBedException(){
		super();
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String
	 */
	public TestBedException(String msg){
		super(msg);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String,Throwable
	 */
	public TestBedException(String msg, Throwable cause){
		super(msg, cause);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param Throwable
	 */
	public TestBedException(Throwable cause){
		super(cause);
	}
}
