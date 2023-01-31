package com.weather.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.configuration.ForeCastProperties;
import com.weather.model.Weather;
import com.weather.utils.DateTimeManagement;

@Service
public class RecommendationServiceImpl implements RecommendationService {

	@Autowired
	DateTimeManagement dateTimeManagement;
	
	@Autowired
	ForeCastProperties foreCastProperties;
	
	private LinkedHashMap<String, String> recommedationHash = new LinkedHashMap<>();
	private LinkedHashMap<String, ArrayList<Double>> temperatureHash = new LinkedHashMap<>();
	
	private String date1,date2,date3;
	
	Logger logger = LoggerFactory.getLogger(RecommendationService.class);
	
	/*
	 * List<Weather> WeatherList
	 * Will take weather List as input and create Day wise recommendations by checking next 3 days date.
	 */
	@Override
	public LinkedHashMap<String, String> dayWiseWeatherData(List<Weather> weatherList) {
		recommedationHash.clear();
		logger.info("Entered dayWiseWeatherData Function");
		weatherList.forEach((weatherBlock) -> {
			String date = null;
			if(dateTimeManagement.isTommorrow(weatherBlock.getDt())) {
				date = dateTimeManagement.convertTimeStampToTime(weatherBlock.getDt(), false);
				createWindowWiseRecommendations(date , weatherBlock);
			} else if(dateTimeManagement.isDayAfterTommorrow(weatherBlock.getDt())) {
				date = dateTimeManagement.convertTimeStampToTime(weatherBlock.getDt(), false);
				createWindowWiseRecommendations(date , weatherBlock);
			}else if(dateTimeManagement.isNextDayToTomorrow(weatherBlock.getDt())) {
				date = dateTimeManagement.convertTimeStampToTime(weatherBlock.getDt(), false);
				createWindowWiseRecommendations(date , weatherBlock);
			}
			
	    });
		logger.info("Output of recommedationHash from  dayWiseWeatherData Function is : {} ", recommedationHash);
		return recommedationHash;
	}
	
	/*
	 * String window as date-time format, Weather weather
	 * Will create recommendations as per given time window and weather Json Date respectively
	 */
	@Override
	public void createWindowWiseRecommendations(String window , Weather weather) {
		
		//dateTimeManagement.convertTimeStampToTime(weather.getDt(), true);
		if(window == null) return ;
		if(weather.getWeather().get(0).getMain().contains(foreCastProperties.getRain())) {
			recommedationHash.put(window, "Carry Umbrella");
		} else if(weather.getMain().getTemp() >= foreCastProperties.getTemperature()) {
			recommedationHash.put(window, "Use Sunscreen lotion");
		} else if(weather.getWind().getSpeed() > foreCastProperties.getWind()) {
			recommedationHash.put(window, "ThunderStorm Dont Step Out");
		} else if(weather.getWeather().get(0).getMain().contains(foreCastProperties.getSnow())) {
			recommedationHash.put(window, "Snow is Falling");
		} else {
			recommedationHash.put(window, "You Can Roam freely without umbrella");
		}
	}
	
	/*
	 * List<Weather> weatherList
	 * Will create future temperatures in list form for the next 3 days.
	 * Assign those list to particular date as key in temperatureHash.
	 */
	@Override
	public LinkedHashMap<String, ArrayList<Double>> dayWiseWeatherTemperatures(List<Weather> weatherList) {
		
		temperatureHash.clear();
		logger.info("Entered dayWiseWeatherTemperatures Function");
		
		ArrayList<Double> tomorrowTemp = new ArrayList<Double>();
		ArrayList<Double> nextDayToTmrwTemp = new ArrayList<Double>();
		ArrayList<Double> nextToNextTmrwTemp = new ArrayList<Double>();
		
		date1=date2=date3="";
		
		weatherList.forEach((weatherBlock) -> {
			if(dateTimeManagement.isTommorrow(weatherBlock.getDt())) {
				tomorrowTemp.add(weatherBlock.getMain().getTemp());
				assignDateVariable("day1",weatherBlock.getDt());
			} else if(dateTimeManagement.isDayAfterTommorrow(weatherBlock.getDt())) {
				nextDayToTmrwTemp.add(weatherBlock.getMain().getTemp());
				assignDateVariable("day2",weatherBlock.getDt());
			}else if(dateTimeManagement.isNextDayToTomorrow(weatherBlock.getDt())) {
				nextToNextTmrwTemp.add(weatherBlock.getMain().getTemp());
				assignDateVariable("day3",weatherBlock.getDt());
			}
	    });
		
		temperatureHash.put(date1, tomorrowTemp);
		temperatureHash.put(date2, nextDayToTmrwTemp);
		temperatureHash.put(date3, nextToNextTmrwTemp);
		logger.info("Output of temperatureHash from  dayWiseWeatherTemperatures Function is : {} ", temperatureHash);
		return temperatureHash;
	}
	
	/*
	 * String day, Long dateTimeStamp
	 * will convert dateTimeStamp to only date and assigns to date1,date2,date3 variables respectively. 
	 */
	@Override
	public void assignDateVariable(String day , Long dateTimeStamp) {
		if(day.contains("day1")) {
			date1 = dateTimeManagement.convertTimeStampToTime(dateTimeStamp, true);
		} else if(day.contains("day2")) {
			date2 = dateTimeManagement.convertTimeStampToTime(dateTimeStamp, true);
		} else if (day.contains("day3")) {
			date3 = dateTimeManagement.convertTimeStampToTime(dateTimeStamp, true);
		}
	}
}
