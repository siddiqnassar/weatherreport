package com.weather.model;

import org.springframework.stereotype.Component;

@Component
public class Response {
	private String message;
	private Object predictionWindow;
	private Object futureDayTemperatures;
	public Object getFutureDayTemperatures() {
		return futureDayTemperatures;
	}
	public void setFutureDayTemperatures(Object futureDayTemperatures) {
		this.futureDayTemperatures = futureDayTemperatures;
	}
	public Object getPredictionWindow() {
		return predictionWindow;
	}
	public void setPredictionWindow(Object predictionWindow) {
		this.predictionWindow = predictionWindow;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
