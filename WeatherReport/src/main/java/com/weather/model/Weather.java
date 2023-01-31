package com.weather.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Weather {
	private long dt;
	private Main main;
	private List<WeatherStatus> weatherStatus;
	private Clouds clouds;
	private Wind wind;
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	public long getDt() {
		return dt;
	}
	public void setDt(long dt) {
		this.dt = dt;
	}
	public List<WeatherStatus> getWeatherStatus() {
		return weatherStatus;
	}
	public void setWeatherStatus(List<WeatherStatus> weatherStatus) {
		this.weatherStatus = weatherStatus;
	}
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public List<WeatherStatus> getWeather() {
		return weatherStatus;
	}
	public void setWeather(List<WeatherStatus> weatherStatus) {
		this.weatherStatus = weatherStatus;
	}
	public Clouds getClouds() {
		return clouds;
	}
	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}
}
