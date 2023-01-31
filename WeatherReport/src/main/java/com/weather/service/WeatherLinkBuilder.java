package com.weather.service;

public interface WeatherLinkBuilder {

	public String weatherLinkBuilder(String foreCastHost, String cityName, String appId, String forecastDayCount,String units);
}
