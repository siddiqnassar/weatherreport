package com.weather.service;


import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.weather.configuration.ForeCastProperties;
import com.weather.model.Response;
import com.weather.model.WeatherForecastModel;
import com.weather.utils.DateTimeManagement;

@Service("WeatherReportService")
public class WeatherReportServiceImpl implements WeatherReportService,WeatherLinkBuilder{
	@Autowired
	private ForeCastProperties forecast;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Response response;
	
	@Autowired
	DateTimeManagement dateTimeManagement;
	
	@Autowired
	RecommendationServiceImpl recommendationServiceImpl;
	
	Logger logger = LoggerFactory.getLogger(WeatherReportServiceImpl.class);
	
	/*
	 * String cityName
	 * Returns complete object of weatherForecast with Response as output Object
	 */
	@Override
	public Response getWeatherForeCast(String cityName) {
	    try {
	    	ResponseEntity<WeatherForecastModel> obj = restTemplate.getForEntity(weatherLinkBuilder(forecast.getLink(), cityName,forecast.getAppId(), forecast.getNextDaysCount(), forecast.getUnits()), WeatherForecastModel.class);
	    	
	    	LinkedHashMap<String, String> recommendationHash = recommendationServiceImpl.dayWiseWeatherData(obj.getBody().getList());
	    	LinkedHashMap<String, ArrayList<Double>> temperatureHash = recommendationServiceImpl.dayWiseWeatherTemperatures(obj.getBody().getList());
		    return createResponse("success", recommendationHash,temperatureHash);
	    } catch(HttpClientErrorException ex) {
	    	 logger.error("The HttpClientErrorException is : {}", ex.getMessage());
		    return createResponse(ex.getMessage(), null, null);
	    } catch (Exception ex) {
	    	 logger.error("The Exception is : {}", ex.getMessage());
		    return createResponse(ex.getMessage(), null, null);
	    }
	}
	
	/*
	 * String message, LinkedHashMap<String, String> recommendationHash ,LinkedHashMap<String, ArrayList<Double>> temperatureHash
	 * Returns Response Modal after setting the fields.
	 */
	@Override
	public Response createResponse(String message, LinkedHashMap<String, String> recommendationHash ,LinkedHashMap<String, ArrayList<Double>> temperatureHash ) {
		response.setMessage(message);
		response.setPredictionWindow(recommendationHash);
		response.setFutureDayTemperatures(temperatureHash);
		return response;
	}

	/*
	 * String foreCastHost, String cityName, String appId, String forecastDayCount, String units
	 * Returns link in String Format, which will be used to call 3rd Party API to get results.
	 */
	@Override
	public String weatherLinkBuilder(String foreCastHost, String cityName, String appId, String forecastDayCount, String units) {
		
		return foreCastHost + "?q=" + cityName + "&appid=" + appId + "&cnt=" + forecastDayCount + "&units=" + units;
	}
	
}
