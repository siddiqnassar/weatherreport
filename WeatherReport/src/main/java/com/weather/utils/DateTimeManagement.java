package com.weather.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class DateTimeManagement {
	
	public Boolean isTommorrow(long dateEpoch) {
		long epochInMillis = dateEpoch * 1000;
		Calendar now = Calendar.getInstance();
		Calendar timeToCheck = Calendar.getInstance();
		timeToCheck.setTimeInMillis(epochInMillis);

		if(now.get(Calendar.YEAR) == timeToCheck.get(Calendar.YEAR)) {
		    if(now.get(Calendar.DAY_OF_YEAR) +1 == timeToCheck.get(Calendar.DAY_OF_YEAR) ) {
		    	return true;
		    }
		}
		return false;
	}
	
	public Boolean isDayAfterTommorrow(long dateEpoch) {
		long epochInMillis = dateEpoch * 1000;
		Calendar now = Calendar.getInstance();
		Calendar timeToCheck = Calendar.getInstance();
		timeToCheck.setTimeInMillis(epochInMillis);

		if(now.get(Calendar.YEAR) == timeToCheck.get(Calendar.YEAR)) {
		    if(now.get(Calendar.DAY_OF_YEAR) + 2 == timeToCheck.get(Calendar.DAY_OF_YEAR) ) {
		    	return true;
		    }
		}
		return false;
	}
	
	public Boolean isNextDayToTomorrow(long dateEpoch) {
		long epochInMillis = dateEpoch * 1000;
		Calendar now = Calendar.getInstance();
		Calendar timeToCheck = Calendar.getInstance();
		timeToCheck.setTimeInMillis(epochInMillis);

		if(now.get(Calendar.YEAR) == timeToCheck.get(Calendar.YEAR)) {
		    if(now.get(Calendar.DAY_OF_YEAR) + 3 == timeToCheck.get(Calendar.DAY_OF_YEAR)) {
		    	return true;
		    }
		}
		return false;
	}
	
	public String convertTimeStampToTime (long dateEpoch ,Boolean isOnlyDate) {
        Date date = new Date(dateEpoch * 1000);
        DateFormat format;
        if(isOnlyDate) {
        	format = new SimpleDateFormat("dd/MM/yyyy");
        } else {
        	format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        }
        format.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String formatted = format.format(date);
		return formatted;
	}
	
}
