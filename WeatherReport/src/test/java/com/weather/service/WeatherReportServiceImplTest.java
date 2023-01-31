package com.weather.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.weather.configuration.ForeCastProperties;
import com.weather.model.Response;
import com.weather.model.Weather;
import com.weather.model.WeatherForecastModel;
import com.weather.service.RecommendationServiceImpl;
import com.weather.service.WeatherReportServiceImpl;
import com.weather.utils.DateTimeManagement;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherReportServiceImplTest {
	
	@Mock
	RestTemplate restTemplate;
	
	@Mock
	Response response;
	
	@Mock
	DateTimeManagement dateTimeManagement;
	
	@Autowired
	WeatherReportServiceImpl weatherServiceImpl;
	
	@Mock
	RecommendationServiceImpl recommendationServiceImpl;
	
	@Mock
	WeatherForecastModel weatherForeCastModel;
	
	@Autowired
	private ForeCastProperties forecast;
	
	LinkedHashMap<String,String> recommendationHash = new LinkedHashMap<>();
	 
	LinkedHashMap<String,ArrayList<Double>> futureDayTemperaturesHash = new LinkedHashMap<>();
	
 	ArrayList<Weather> weather = new ArrayList<Weather>();
 	
 	
    @Before 
    public void setUp() {
    	weatherForeCastModel.setCity("london");
    	weatherForeCastModel.setMessage("success");
    	weatherForeCastModel.setList(weather);
    	
	   	recommendationHash.put("10/01/2023 02:30:00", "You Can Roam freely without umbrella");
		ArrayList<Double> tomorrowTemp = new ArrayList<Double>();
	    tomorrowTemp.add(1.00);
	    futureDayTemperaturesHash.put("10/01/2023", tomorrowTemp);
	   	 
		response.setFutureDayTemperatures(futureDayTemperaturesHash);
		response.setMessage("success");
		response.setPredictionWindow(recommendationHash);
    }
    
	/*
	 * TestCase for getWeatherForeCast function.
	 * should return success as message success.
	 */
    @Test
    public void getWeatherForeCast() {
    	
    	Mockito.when(restTemplate.getForEntity(("https://api.openweathermap.org/data/2.5/forecast?q=london"), WeatherForecastModel.class)).thenReturn(new ResponseEntity <WeatherForecastModel>(weatherForeCastModel, HttpStatus.OK));
    	Mockito.when(recommendationServiceImpl.dayWiseWeatherData(weather)).thenReturn(recommendationHash);
    	Mockito.when(recommendationServiceImpl.dayWiseWeatherTemperatures(weather)).thenReturn(futureDayTemperaturesHash);
    	
    	assertEquals( "success" , weatherServiceImpl.getWeatherForeCast("london").getMessage());
	
    }
    
	/*
	 * TestCase for create function.
	 * Creates Response object as per the Input Given.
	 */
    @Test
    public void createResponse() {
    	assertEquals("success", weatherServiceImpl.createResponse("success", recommendationHash, futureDayTemperaturesHash).getMessage());
    }

}
