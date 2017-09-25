/**
 * 
 */
package com.ubs.opsit.interviews.berlintimeconverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ubs.opsit.interviews.TimeConverter;
import com.ubs.opsit.interviews.validator.TimeValidator;
import com.ubs.opsit.interviews.validator.berlintimevalidator.BerlinTimeValidator;

/**
 * The Class BerlinTimeConverter.
 *
 * @author Narya
 */
public class BerlinTimeConverter implements TimeConverter{

	private TimeValidator timeValidator;
	
	
	public BerlinTimeConverter() {
		timeValidator = new BerlinTimeValidator();
	}
	
	/* (non-Javadoc)
	 * @see com.ubs.opsit.interviews.TimeConverter#convertTime(java.lang.String)
	 */
	@Override
	public String convertTime(String aTime) {
		
		timeValidator.validateGivenTime(aTime);
		
		String[] timeComponents = aTime.split(":");
		List<String> resultComponents = new ArrayList<>();
		resultComponents.add(computeSeconds(Integer.parseInt(timeComponents[2])));
		resultComponents.add(computeHours(Integer.parseInt(timeComponents[0])));
		resultComponents.add(computeMinutes(Integer.parseInt(timeComponents[1])));
		
		StringBuilder formattedString = new StringBuilder();
		for(String resulComponent : resultComponents){
			formattedString.append(resulComponent).append("\r\n");
		}
		String resultString  = formattedString.substring(0, formattedString.lastIndexOf("\r"));//( , formattedString.length()-1,"");
		
		return resultString;
	}

	
	/**
	 * Compute minutes.
	 *
	 * @param numberOfMinutes the number of minutes
	 * @return the string
	 */
	private String computeMinutes(int numberOfMinutes) {
		int numberOfMinutesInTopRow = numberOfMinutes / 5;
		int numberOfMinutesInBottomRow = numberOfMinutes % 5;
		
		String topRow = getTopRowForMinutes(numberOfMinutesInTopRow);
		String bottomRow = getBottomRowForMinutes(numberOfMinutesInBottomRow);
		
		
		return topRow+"\r\n"+bottomRow;
	}

	/**
	 * Gets the top row for minutes.
	 *
	 * @param numberOfMinutesInTopRow the number of minutes in top row
	 * @return the top row for minutes
	 */
	private String getTopRowForMinutes(int numberOfMinutesInTopRow) {
		String[] rows = new String[]{"O","O","O","O","O","O","O","O","O","O","O"};
    	for(int i=0;i<numberOfMinutesInTopRow; i++){
    		if( (i != 0) && (i+1)%3 == 0){
    			rows[i] = "R";
    		}else{
    			rows[i] = "Y";
    		}
    	}
    	
		return Arrays.toString(rows).replace(",", "").replace(" ", "").replace("[","").replace("]", "");
	}

	/**
	 * Gets the bottom row for minutes.
	 *
	 * @param numberOfMinutesInBottomRow the number of minutes in bottom row
	 * @return the bottom row for minutes
	 */
	private String getBottomRowForMinutes(int numberOfMinutesInBottomRow) {
		String[] rows = new String[]{"O","O","O","O"};
    	for(int i=0;i<numberOfMinutesInBottomRow; i++){
    		rows[i] = "Y";
    	}
    	return Arrays.toString(rows).replace(",", "").replace(" ", "").replace("[","").replace("]", "");
	}

	/**
	 * Compute hours.
	 *
	 * @param numberOfHours the number of hours
	 * @return the string
	 */
	private String computeHours(int numberOfHours) {
		int numberOfHoursInTopRow = numberOfHours / 5;
		int numberOfHoursInBottomRow = numberOfHours % 5;
		
		String topRow = getTopRowForHours(numberOfHoursInTopRow);
		String bottomRow = getBottomRowForHours(numberOfHoursInBottomRow);
		
		return topRow +"\r\n"+bottomRow;
	}

	/**
	 * Gets the bottom row for hours.
	 *
	 * @param numberOfHoursInBottomRow the number of hours in bottom row
	 * @return the bottom row for hours
	 */
	private String getBottomRowForHours(int numberOfHoursInBottomRow) {
		String[] rows = new String[]{"O","O","O","O"};
    	for(int i=0;i < numberOfHoursInBottomRow ;i++){
    		rows[i] = "R";
    	}
    	return Arrays.toString(rows).replace(",", "").replace(" ", "").replace("[","").replace("]", "");
	}

	/**
	 * Gets the top row for hours.
	 *
	 * @param numberOfHoursInTopRow the number of hours in top row
	 * @return the top row for hours
	 */
	private String getTopRowForHours(int numberOfHoursInTopRow) {
		String[] rows = new String[]{"O","O","O","O"};
    	for(int i=0;i<numberOfHoursInTopRow;i++){
    		rows[i] = "R";
    	}
    	
		return Arrays.toString(rows).replace(",", "").replace(" ", "").replace("[","").replace("]", "");
	}

	/**
	 * Compute seconds.
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
		if(numberOfSeconds %2 == 0){
			return "Y";
		}
		
		return "O";
	}
}
