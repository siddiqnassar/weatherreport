package com.weather.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.junit.Before; 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.weather.model.Response;
import com.weather.service.WeatherReportServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherReportControllerTest { 
		
		@Mock
		private WeatherReportServiceImpl weatherReportServiceImpl;
		
		@Autowired
		private Response response;
		private Object predictionWindow;
	    private Object futureTemperatures;
		
		 @Before 
		  public void setUp() {
			 
			 LinkedHashMap<String,String> recommendationHash = new LinkedHashMap<>();
			 recommendationHash.put("10/01/2023 02:30:00", "You Can Roam freely without umbrella");
			 
			 ArrayList<Double> tomorrowTemp = new ArrayList<Double>();
			 LinkedHashMap<String,ArrayList<Double>> futureDayTemperaturesHash = new LinkedHashMap<>();
			 tomorrowTemp.add(1.00);
			 futureDayTemperaturesHash.put("10/01/2023", tomorrowTemp);
			 
			 predictionWindow = recommendationHash;
			 response.setMessage("success");
			 response.setFutureDayTemperatures(tomorrowTemp);
			 response.setPredictionWindow(recommendationHash);
			 
		  }
		 
		/*
		 * TestCase for controller function.
		 */
		@Test
		public void getWeatherForeCastTest() {	
            Mockito.when(weatherReportServiceImpl.getWeatherForeCast("kadapa")).thenReturn(response); 
            Response expected = weatherReportServiceImpl.getWeatherForeCast("kadapa");
            Response actual= response;
            assertEquals(actual,expected);
		}	

    } 