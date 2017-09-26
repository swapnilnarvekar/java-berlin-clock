/**
 * 
 */
package com.ubs.opsit.interviews.berlintimeconverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.ubs.opsit.interviews.TimeConverter;
import com.ubs.opsit.interviews.validator.TimeValidator;
import com.ubs.opsit.interviews.validator.berlintimevalidator.BerlinTimeValidator;

/**
 * The Class : <b>BerlinTimeConverter</b><br>
 * Description :This class is responsible for converting a given time(hh:mm:ss) to 'Berlin' time
 * It implements the interface - TimeConverter 
 *
 * @author Swapnil A. Narvekar
 */
public class BerlinTimeConverter implements TimeConverter{

	private final static Logger LOG = Logger.getLogger(BerlinTimeConverter.class);
	private TimeValidator timeValidator;
	
	
	public BerlinTimeConverter() {
		timeValidator = new BerlinTimeValidator();
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
		
		String[] timeComponents = aTime.split(":");
		String resultString = berlinTimeParser(timeComponents);
		LOG.info("a given time is converted in 'Berlin Time'successfully ");
		
		
		return resultString;
	}

	/**
	 * @param timeComponents
	 * @return
	 */
	private String berlinTimeParser(String[] timeComponents) {
		LOG.debug("timeComponents : " + Arrays.toString(timeComponents));
		List<String> resultComponents = new ArrayList<>();
		resultComponents.add(computeSeconds(Integer.parseInt(timeComponents[2])));
		resultComponents.add(computeHours(Integer.parseInt(timeComponents[0])));
		resultComponents.add(computeMinutes(Integer.parseInt(timeComponents[1])));
		
		StringBuilder formattedString = new StringBuilder();
		for(String resulComponent : resultComponents){
			formattedString.append(resulComponent).append("\r\n");
		}
		String result = formattedString.substring(0, formattedString.lastIndexOf("\r"));
		LOG.debug("berlin time parsed as : " + result);
		return result;
	}

	
	/**
	 * Compute minutes by taking a minute component (mm) as an argument and returns a string  
	 *
	 * @param numberOfMinutes the number of minutes
	 * @return the string
	 */
	private String computeMinutes(int numberOfMinutes) {
		LOG.debug("numberOfMinutes : "+ numberOfMinutes);
		int numberOfMinutesInTopRow = numberOfMinutes / 5;
		int numberOfMinutesInBottomRow = numberOfMinutes % 5;
		
		String topRow = getTopRowForMinutes(numberOfMinutesInTopRow);
		String bottomRow = getBottomRowForMinutes(numberOfMinutesInBottomRow);
		
		String minutes = topRow+"\r\n"+bottomRow; 
		LOG.debug("minutes are constructed as : "+minutes);
		return minutes;
	}

	/**
	 * Gets the top row for minutes.
	 *
	 * @param numberOfMinutesInTopRow the number of minutes in top row
	 * @return the top row for minutes
	 */
	private String getTopRowForMinutes(int numberOfMinutesInTopRow) {
		LOG.debug("numberOfMinutesInTopRow : "+ numberOfMinutesInTopRow);
		
		//it represents a series of bulbs for minutes and all are in OFF state
		String[] rows = new String[]{"O","O","O","O","O","O","O","O","O","O","O"};
		
    	for(int i=0;i<numberOfMinutesInTopRow; i++){
    		if( (i != 0) && (i+1)%3 == 0){
    			rows[i] = "R"; //switch on a bulb with RED color
    		}else{
    			rows[i] = "Y"; //switch on a bulb with YELLOW color
    		}
    	}
    	
    	String result = Arrays.toString(rows).replace(",", "").replace(" ", "").replace("[","").replace("]", "");
    	LOG.debug("Minutes for top row are constructed as - " + result);
		return result; 
	}

	/**
	 * Gets the bottom row for minutes.
	 *
	 * @param numberOfMinutesInBottomRow the number of minutes in bottom row
	 * @return the bottom row for minutes
	 */
	private String getBottomRowForMinutes(int numberOfMinutesInBottomRow) {
		LOG.debug("numberOfMinutesInBottomRow : " + numberOfMinutesInBottomRow);
		
		//it represents a series of bulbs for minutes and all are in OFF state
		String[] rows = new String[]{"O","O","O","O"};
    	for(int i=0;i<numberOfMinutesInBottomRow; i++){
    		rows[i] = "Y"; //switch on a bulb with YELLOW color
    	}
    	
    	String result = Arrays.toString(rows).replace(",", "").replace(" ", "").replace("[","").replace("]", "");
    	LOG.debug("Minutes for bottom row are constructed as - " + result);
    	return result;
	}

	/**
	 * Compute minutes by taking a hour component (hh) as an argument and returns a string.
	 *
	 * @param numberOfHours the number of hours
	 * @return the string
	 */
	private String computeHours(int numberOfHours) {
		LOG.debug("numberOfHours : " + numberOfHours);
		int numberOfHoursInTopRow = numberOfHours / 5;
		int numberOfHoursInBottomRow = numberOfHours % 5;
		
		String topRow = getTopRowForHours(numberOfHoursInTopRow);
		String bottomRow = getBottomRowForHours(numberOfHoursInBottomRow);
		
		String result = topRow +"\r\n"+bottomRow;
		LOG.debug("Hour component is constructed as : " + result);
		return result;
	}

	/**
	 * Gets the bottom row for hours.
	 *
	 * @param numberOfHoursInBottomRow the number of hours in bottom row
	 * @return the bottom row for hours
	 */
	private String getBottomRowForHours(int numberOfHoursInBottomRow) {
		LOG.debug("numberOfHoursInBottomRow : " + numberOfHoursInBottomRow);
		
		//it represents a series of bulbs for hours and all are in OFF state
		String[] rows = new String[]{"O","O","O","O"};
    	for(int i=0;i < numberOfHoursInBottomRow ;i++){
    		rows[i] = "R";
    	}
    	
    	String result = Arrays.toString(rows).replace(",", "").replace(" ", "").replace("[","").replace("]", "");
    	LOG.debug("Hours for bottom row are constructed as - " + result);
    	return result;
	}

	/**
	 * Gets the top row for hours.
	 *
	 * @param numberOfHoursInTopRow the number of hours in top row
	 * @return the top row for hours
	 */
	private String getTopRowForHours(int numberOfHoursInTopRow) {
		LOG.debug("numberOfHoursInTopRow : " + numberOfHoursInTopRow);
		
		//it represents a series of bulbs for hours and all are in OFF state
		String[] rows = new String[]{"O","O","O","O"};
    	for(int i=0;i<numberOfHoursInTopRow;i++){
    		rows[i] = "R";
    	}
    	
    	String result = Arrays.toString(rows).replace(",", "").replace(" ", "").replace("[","").replace("]", "");
    	LOG.debug("Hours for top row are constructed as - " + result);
		return result;
	}

	/**
	 * Compute minutes by taking a seconds component (ss) as an argument and returns a string.
	 *
	 * @param numberOfSeconds the number of seconds
	 * @return the string
	 */
	private String computeSeconds(int numberOfSeconds) {
		return getRowForSeconds(numberOfSeconds);
	}

	/**
	 * Gets the row for seconds.
	 *
	 * @param numberOfSeconds the number of seconds
	 * @return the row for seconds
	 */
	private String getRowForSeconds(int numberOfSeconds){
		LOG.debug("numberOfSeconds : " + numberOfSeconds);
		String result = null;
		if(numberOfSeconds %2 == 0){
			result = "Y";
		}else{
			result = "O";
		}
		LOG.debug("numberOfSeconds represented as : " + result);
		return result;
		
	}
}
