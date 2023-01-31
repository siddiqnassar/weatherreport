package com.weather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.model.Response;
import com.weather.service.WeatherReportServiceImpl;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class WeatherReportController {

	Logger logger = LoggerFactory.getLogger(WeatherReportController.class);

	@Autowired
	private WeatherReportServiceImpl weatherReportServiceImpl;
   
   /*
    * @RequestParam String cityName
    * Will take cityName as input and returns city predictions along with future temperatures.
    */
   @GetMapping(value = "/get/city/forecast")
   @ApiOperation(value = "Gets City Forecast for the next three days and recommendations", notes = "City Name must exist in the query parameter")
   public ResponseEntity<Response> getWeatherForeCast(@RequestParam("cityname") String cityName) {
	   logger.info("The City Name in getWeatherForeCast is {}", cityName);
       return ResponseEntity.status(HttpStatus.OK).body(weatherReportServiceImpl.getWeatherForeCast(cityName));
   }
}
