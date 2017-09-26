/*
 * 
 */
package com.ubs.opsit.interviews.additionaltestcases;

import org.junit.Test;

import com.ubs.opsit.interviews.TimeConverter;
import com.ubs.opsit.interviews.berlintimeconverter.BerlinTimeConverter;
import com.ubs.opsit.interviews.exception.BerlinTimeConversionException;

/**
 * The Class BerlinClockFixtureWithAdditionalTestCases.
 * 
 * @author Swapnil A. Narvekar
 */
public class BerlinClockFixtureWithAdditionalTestCases {

	/** The berlin clock. */
	private TimeConverter berlinClock = new BerlinTimeConverter();
	
	/**
	 * Berlin clock acceptance tests time as null.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void berlinClockAcceptanceTests_TimeAsNull(){
    	berlinClock.convertTime(null);
	}
	
	/**
	 * Berlin clock acceptance tests time as empty.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void berlinClockAcceptanceTests_TimeAsEmpty(){
    	berlinClock.convertTime("");
	}
	
	/**
	 * Berlin clock acceptance tests time is not in proper format.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void berlinClockAcceptanceTests_TimeIsNotInProperFormat(){
    	berlinClock.convertTime("12:23");
	}
	
	/**
	 * Test B clock time with alpha numeric characters.
	 */
	@Test(expected=BerlinTimeConversionException.class)
	public void berlinClockAcceptanceTests_TimeWithAlphaNumericCharacters(){
		berlinClock.convertTime("AA:BB:CC");
	}
	
	@Test(expected=BerlinTimeConversionException.class)
	public void berlinClockAcceptanceTests_TimeWithHoursMoreThan24(){
		berlinClock.convertTime("26:00:00");
	}
	
	@Test(expected=BerlinTimeConversionException.class)
	public void berlinClockAcceptanceTests_TimeWithHoursLessThan0(){
		berlinClock.convertTime("-1:00:00");
	}
	
	@Test(expected=BerlinTimeConversionException.class)
	public void berlinClockAcceptanceTests_TimeWithMinutesMoreThan59(){
		berlinClock.convertTime("00:63:00");
	}
	
	@Test(expected=BerlinTimeConversionException.class)
	public void berlinClockAcceptanceTests_TimeWithMinutesLessThan0(){
		berlinClock.convertTime("00:-1:00");
	}
	
	@Test(expected=BerlinTimeConversionException.class)
	public void berlinClockAcceptanceTests_TimeWithSecondssMoreThan59(){
		berlinClock.convertTime("00:00:60");
	}
	
	@Test(expected=BerlinTimeConversionException.class)
	public void berlinClockAcceptanceTests_TimeWithSecondsLessThan0(){
		berlinClock.convertTime("00:00:-59");
	}
}
