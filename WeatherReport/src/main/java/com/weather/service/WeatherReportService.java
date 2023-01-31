package com.weather.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.weather.model.Response;

public interface WeatherReportService {

	public Response getWeatherForeCast(String cityName);
	Response createResponse(String message, LinkedHashMap<String, String> recommendationHash,
			LinkedHashMap<String, ArrayList<Double>> temperatureHash);
}
