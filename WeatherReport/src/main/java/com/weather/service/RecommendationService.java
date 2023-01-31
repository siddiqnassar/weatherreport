package com.weather.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.weather.model.Weather;

public interface RecommendationService {

	public LinkedHashMap<String, String> dayWiseWeatherData(List<Weather> weatherList);
	public void createWindowWiseRecommendations(String window , Weather weather);
	public LinkedHashMap<String, ArrayList<Double>> dayWiseWeatherTemperatures(List<Weather> weatherList);
	void assignDateVariable(String day, Long dateTimeStamp);
}
