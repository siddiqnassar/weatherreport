package com.weather.model;

import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class WeatherForecastModel {

	private String cod;
	private String message;
	private int cnt;
	private List<Weather> list;
	private Object city;
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public List<Weather> getList() {
		return list;
	}
	public void setList(List<Weather> list) {
		this.list = list;
	}
	public Object getCity() {
		return city;
	}
	public void setCity(Object city) {
		this.city = city;
	}
}
