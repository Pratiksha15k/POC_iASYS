package com.brix.testbed.monitor.utility;

public class TestBedMonitorUtilityException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1450201938230703205L;
	/**
	 * Default Constructor.
	 * @return no return type
	 * @param Nothing
	 */
	public TestBedMonitorUtilityException(){
		super();
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String
	 */
	public TestBedMonitorUtilityException(String msg){
		super(msg);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String,Throwable
	 */
	public TestBedMonitorUtilityException(String msg, Throwable cause){
		super(msg, cause);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param Throwable
	 */
	public TestBedMonitorUtilityException(Throwable cause){
		super(cause);
	}

}
