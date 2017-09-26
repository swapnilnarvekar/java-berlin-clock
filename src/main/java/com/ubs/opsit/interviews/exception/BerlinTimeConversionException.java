/**
 * 
 */
package com.ubs.opsit.interviews.exception;

/**
 * The Class BerlinTimeConversionException.
 *
 * @author Swapnil A. Narvekar
 */
public class BerlinTimeConversionException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new berlin time conversion exception.
	 *
	 * @param message the message
	 */
	public BerlinTimeConversionException(String message){
		super(message);
	}
	
	/**
	 * Instantiates a new berlin time conversion exception.
	 *
	 * @param exception the exception
	 */
	public BerlinTimeConversionException(Exception exception){
		super(exception);
	}

}
