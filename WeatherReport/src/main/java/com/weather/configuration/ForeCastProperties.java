package com.weather.configuration;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "forecast")
@Validated
public class ForeCastProperties {

	@NotNull
	private String nextDaysCount;
	
	@NotNull
	private String link;
	
	@NotNull
	private String appId;

	@NotNull
	private String units;
	
	@NotNull
	private int futureDays;
	
	@NotNull
	private double temperature;

	@NotNull
	private String rain;
	
	@NotNull
	private double wind;
	
	@NotNull
	private String snow;
	

	public String getSnow() {
		return snow;
	}

	public void setSnow(String snow) {
		this.snow = snow;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public String getRain() {
		return rain;
	}

	public void setRain(String rain) {
		this.rain = rain;
	}

	public double getWind() {
		return wind;
	}

	public void setWind(double wind) {
		this.wind = wind;
	}

	public int getFutureDays() {
		return futureDays;
	}

	public void setFutureDays(int futureDays) {
		this.futureDays = futureDays;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getNextDaysCount() {
		return nextDaysCount;
	}

	public void setNextDaysCount(String nextDaysCount) {
		this.nextDaysCount = nextDaysCount;
	}
	
}
