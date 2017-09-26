/**
 * 
 */
package com.ubs.opsit.interviews.berlintimeconverter;

import org.apache.log4j.Logger;

import com.ubs.opsit.interviews.TimeConverter;
import com.ubs.opsit.interviews.parser.TimeParser;
import com.ubs.opsit.interviews.parser.berlintimeparser.BerlinTimeParser;
import com.ubs.opsit.interviews.validator.TimeValidator;
import com.ubs.opsit.interviews.validator.berlintimevalidator.BerlinTimeValidator;

/**
 * The Class : <b>BerlinTimeConverter</b><br>
 * Description :This class is responsible for converting a given time(hh:mm:ss) to 'Berlin' time
 * It implements the interface - TimeConverter which is provided by UBS 
 *
 * @author Swapnil A. Narvekar
 */
public class BerlinTimeConverter implements TimeConverter{

	private final static Logger LOG = Logger.getLogger(BerlinTimeConverter.class);
	private TimeValidator timeValidator;
	private TimeParser timeParser;
	
	
	public BerlinTimeConverter() {
		timeValidator = new BerlinTimeValidator();
		timeParser = new BerlinTimeParser();
	}
	
	/**
	 * This method converts a given time (hh:mm:ss) into 'Berlin' time in the form of String -
	 *  <br> for e.g : if time is 13:17:01 <br>
	 *	Then the clock should look like<br>
	 *  O<br>
	 *  RROO<br>
	 *  RRRO<br>
	 *  YYROOOOOOOO<br>
	 *  YYOO<br>
	 * 
	 */
	@Override
	public String convertTime(String aTime) {
		LOG.info("a given time is input as - " + aTime);
		
		timeValidator.validateGivenTime(aTime);
		LOG.info("a given time is validated successfully! ");
		
		
		String resultString = timeParser.parseTime(aTime);
		LOG.info("a given time is converted in 'Berlin Time'successfully ");
		
		
		return resultString;
	}

	
}
