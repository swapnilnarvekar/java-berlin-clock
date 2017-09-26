/**
 * 
 */
package com.ubs.opsit.interviews.parser.berlintimeparser;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ubs.opsit.interviews.parser.TimeParser;

/**
 * @author snarvek1
 *
 */
public class BerlinTimeParser implements TimeParser {

	private final static Logger LOG = Logger.getLogger(BerlinTimeParser.class);
	
	@Override
	public String parseTime(String aGivenTime) {
		String[] timeComponents = aGivenTime.split(":");
		
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
		
		StringBuilder stringBuilder = new StringBuilder("");
		for(int i=0;i<numberOfHoursInBottomRow; i++){
			stringBuilder.append("R");//switch on the bulbs with RED color
		}
		
		String result = paddingToRight(stringBuilder.toString(), 4, 'O');
    	
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
		
		StringBuilder stringBuilder = new StringBuilder("");
		for(int i=0;i<numberOfHoursInTopRow; i++){
			stringBuilder.append("R");//switch on the bulbs with RED color
		}
		
		String result = paddingToRight(stringBuilder.toString(), 4, 'O');
		
    	LOG.debug("Hours for top row are constructed as - " + result);
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
		
		StringBuilder stringBuilder = new StringBuilder("");
		for(int i=0;i<numberOfMinutesInTopRow; i++){
			if( (i != 0) && (i+1)%3 == 0){
				stringBuilder.append("R"); //switch on a bulb with RED color
    		}else{
    			stringBuilder.append("Y"); //switch on a bulb with YELLOW color
    		}
		}//for
		
		String result = paddingToRight(stringBuilder.toString(), 11, 'O');
		
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
		
		StringBuilder stringBuilder = new StringBuilder("");
		for(int i=0;i<numberOfMinutesInBottomRow; i++){
			stringBuilder.append("Y"); //switch on a bulb with YELLOW color
		}
		
		String result = paddingToRight(stringBuilder.toString(), 4, 'O');
		LOG.debug("result : " + result);
    	return result;
	}
	

	public String paddingToRight(String stringToBePadded , int limit , char characterToBePadded){
		LOG.debug("stringToBePadded : " + stringToBePadded +
					" | limit : "+ limit + 
					" | characterToBePadded : "+characterToBePadded);
		
		int lengthOfString = stringToBePadded.length();
		int numberOfOccurance = limit - lengthOfString;
		
		StringBuilder stringBuilder = new StringBuilder(stringToBePadded);
		for(int i=0 ;i<numberOfOccurance ;i++){
			stringBuilder.append("O");
		}
		
		LOG.debug("given string is padded as : " + stringBuilder.toString());
		return stringBuilder.toString();
		
	}
}
