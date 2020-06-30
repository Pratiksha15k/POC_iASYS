package com.brix.testbed.monitor.exception;

public class TestBedMonitorException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5177670071920627559L;
	/**
	 * Default Constructor.
	 * @return no return type
	 * @param Nothing
	 */
	public TestBedMonitorException(){
		super();
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String
	 */
	public TestBedMonitorException(String msg){
		super(msg);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String,Throwable
	 */
	public TestBedMonitorException(String msg, Throwable cause){
		super(msg, cause);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param Throwable
	 */
	public TestBedMonitorException(Throwable cause){
		super(cause);
	}
}
