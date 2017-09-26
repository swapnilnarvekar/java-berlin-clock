/**
 * 
 */
package com.ubs.opsit.interviews.validator.berlintimevalidator;

import java.text.NumberFormat;
import java.text.ParseException;

import org.apache.log4j.Logger;

import com.ubs.opsit.interviews.berlintimeconverter.BerlinTimeConverter;
import com.ubs.opsit.interviews.exception.BerlinTimeConversionException;
import com.ubs.opsit.interviews.validator.TimeValidator;

/**
 * The Class BerlinTimeValidator.
 *
 * @author Swapnil A. Narvekar
 */
public class BerlinTimeValidator implements TimeValidator {
	private final static Logger LOG = Logger.getLogger(BerlinTimeValidator.class);
	
	/**
	 * This method is responsible for validation of given time string with possible scenarios.<br>
	 * The possibles scenarios are mentioned as below : <br>
	 * 1. a given time should not be null <br>
	 * 2. a given time should not be empty <br>
	 * 3. a given time should not have alphaNumeric format for e.g. AB:23:00 / 12:AA:45 / 12:1:AA<br>
	 * 4. a given time should not be in incomplete format for e.g. 12:23 <br>
	 * 5. Each time component of a given time must be valid in it's range for e.e. 35:00:00 -> is incorrect format 
	 */
	@Override
	public void validateGivenTime(String aTime) {
		LOG.debug("given time : "+ aTime);
		validateForInvalidArgument(aTime);
				
		validateForTimeComponents(aTime);
		
		validateFomatOfGivenTime(aTime);
		
		validateForValidTimeRange(aTime);
	}
	

	/**
	 * Validate fomat of given time.
	 *
	 * @param aTime the a time
	 */
	private void validateFomatOfGivenTime(String aTime) {
		LOG.debug("aTime : "+ aTime);
		String[] timeComponents = aTime.split(":");
		//handle number format exception
		NumberFormat numberFormat = NumberFormat.getInstance();
		for(String timeComponent : timeComponents){
			try {
			      Number number = numberFormat.parse(timeComponent);
			      LOG.debug("given time component is formatted as : "  + number);
			} catch (ParseException parseException) {
			      throw new BerlinTimeConversionException(parseException);
			}
		}
		LOG.debug("a given time is validated successfully");
	}

	/**
	 * Validate for time components.
	 *
	 * @param aTime the a time
	 */
	private void validateForTimeComponents(String aTime) {
		//check for proper format
		String[] timeComponents = aTime.split(":");
		if(timeComponents.length<3){
			throw new IllegalArgumentException("Given time "+ aTime+" must be in correct format - hh:mm:ss");
		}
	}

	/**
	 * Validate for invalid argument.
	 *
	 * @param aTime the a time
	 */
	private void validateForInvalidArgument(String aTime) {
		//check for invalid argument
		if((aTime == null) || (aTime.trim().equals(""))){
			throw new IllegalArgumentException("Given time should not be null or empty");
		}
	}
	
	/**
	 * Validate for valid time range.
	 *
	 * @param aTime the a time
	 */
	private void validateForValidTimeRange(String aTime) {
		String[] timeComponents = aTime.split(":");
		NumberFormat numberFormat = NumberFormat.getInstance();
		int counter = 0;
		for(String timeComponent : timeComponents){
			try {
			      Number number = numberFormat.parse(timeComponent);
			      
			      switch (counter){
			      	case 0 :  
			    	  		if((number.intValue() < 0) || (number.intValue() > 24)){ 
		    		  			throw new BerlinTimeConversionException("Please enter correct values for hours");
			    	  		}	
			    	  		break;
			      	case 1 : 
			      			if((number.intValue() < 0) || (number.intValue() > 59)){ 
			      				throw new BerlinTimeConversionException("Please enter correct time ");
			      			}
			      			break;
			      	case 2 : 
			      			if((number.intValue() < 0) || (number.intValue() > 59)){ 
			      				throw new BerlinTimeConversionException("Please enter correct time ");
			      			}
			      			break;
			      }
			      
			      counter++;
			} catch (ParseException pe) {
			      throw new BerlinTimeConversionException(pe.getMessage());
			}
		}
		
	}

}
