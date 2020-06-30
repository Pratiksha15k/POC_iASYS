package com.brix.testbed.monitor.utility;

public class TestBedAlarmsUtilityException extends Exception{

	private static final long serialVersionUID = 1L;
	/**
	 * Default Constructor.
	 * @return no return type
	 * @param Nothing
	 */
	public TestBedAlarmsUtilityException() {
		super();
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String
	 */
	public TestBedAlarmsUtilityException(String message) {
		super(message);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param String,Throwable
	 */
	public TestBedAlarmsUtilityException(String message, Throwable throwable) {
		super(message, throwable);
	}
	/**
	 * Parameterized Constructor.
	 * @return no return type
	 * @param Throwable
	 */
	public TestBedAlarmsUtilityException(Throwable throwable) {
		super(throwable);
	}
}
