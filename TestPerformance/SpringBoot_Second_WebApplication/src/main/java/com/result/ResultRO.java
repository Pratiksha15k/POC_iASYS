package com.result;


import java.io.Serializable;


public class ResultRO<T> implements Serializable {

	private static final long serialVersionUID = 2841770107177469775L;

	private int returnCode;
	
	private String message;
	private String parent;
	
	private T result;
	
	private Integer startAt;
	
	private Integer maxResults;
	
	private Integer total;
	
	
	/**
	 * @return the returnCode
	 */
	public int getReturnCode() {
		return returnCode;
	}

	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	/**
	 * @return the startAt
	 */
	public int getStartAt() {
		return startAt;
	}

	/**
	 * @param startAt the startAt to set
	 */
	public void setStartAt(int startAt) {
		this.startAt = startAt;
	}

	/**
	 * @return the maxResults
	 */
	public int getMaxResults() {
		return maxResults;
	}

	/**
	 * @param maxResults the maxResults to set
	 */
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	public String getMessage() {
	    return message;
	}

	public void setMessage(String message) {
	    this.message = message;
	}

	public T getResult() {
	    return result;
	}

	public void setResult(T result) {
    this.result = result;
	}

	private static <E> ResultRO<E> createOutput(int returnCode, String message, E result) {
		ResultRO<E> out = new ResultRO<E>();
		out.setReturnCode(returnCode);
		out.setMessage(message);
		out.setResult(result);
		return out;
	}
	
	public static <E> ResultRO<E> createSuccess() {
	return createOutput(1, null, null);
	}
	public static <E> ResultRO<E> createSuccess(E result) {
		return createOutput(1, null, result);
	}
	public static <E> ResultRO<E> createSuccess(String message) {
		return createOutput(1, message, null);
	}
	public static <E> ResultRO<E> createSuccess(String message, E result) {
		return createOutput(1, message, result);
	}
	
	public static <E> ResultRO<E> createError() {
		return createOutput(3, null, null);
	}

		public static <E> ResultRO<E> createError(E result) {
		return createOutput(3, null, result);
	}
	
	public static <E> ResultRO<E> createError(String message) {
		return createOutput(3, message, null);
	}

    public static <E> ResultRO<E> createError(String message, E result) {
		return createOutput(3, message, result);
	}


	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
}
