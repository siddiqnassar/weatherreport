package com.weather.service;

import static org.junit.Assert.assertEquals;

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

import com.weather.configuration.ForeCastProperties;
import com.weather.model.Weather;
import com.weather.service.RecommendationService;
import com.weather.utils.DateTimeManagement;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendationServiceImplTest {

	@Mock
	DateTimeManagement dateTimeManagement;
	
	@Mock
	ForeCastProperties foreCastProperties;
	
	@Autowired
	RecommendationService recommedationService;
	
	@Mock
	Weather weather;
	
	ArrayList<Weather> weatherList = new ArrayList<Weather>();
	
	@Before
	public void setUp() {
		weather.setDt(12345);
	}
	
	/*
	 * TestCase will return size of 0 as weather List is empty and hence no daywise weather is created
	 */
	@Test
	public void dayWiseWeatherDataTest() {
		Mockito.when(dateTimeManagement.isTommorrow(123444)).thenReturn(true);
		Mockito.when(dateTimeManagement.convertTimeStampToTime(123456, false)).thenReturn("12345");
		assertEquals(0,recommedationService.dayWiseWeatherData(weatherList).size() );
	}
	
	/*
	 * TestCase will return size of 1 as weather List is empty and hence one empty string as key will be created.
	 */
	@Test
	public void dayWiseWeatherTemperaturesTest() {
	  assertEquals(1, recommedationService.dayWiseWeatherTemperatures(weatherList).size());
	}
}
