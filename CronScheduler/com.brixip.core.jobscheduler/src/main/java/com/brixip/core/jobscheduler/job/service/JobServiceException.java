package com.brixip.core.jobscheduler.job.service;

public class JobServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6126103704028735760L;

	public JobServiceException(){}

	/**
	 * 
	 * @param msg
	 */
	public JobServiceException(String msg){
		super(msg);
	}

	/**
	 * 
	 * @param msg
	 * @param cause
	 */
	public JobServiceException(String msg, Throwable cause){
		super(msg, cause);
	}

	/**
	 * 
	 * @param cause
	 */
	public JobServiceException(Throwable cause){
		super(cause);
	}

}

