package com.example.restservice;

import java.util.List;

public class WeatherEntry {
	private Integer id;
	private String date;
	private Location location;              
	private List<Float> temperature;            //  List
	

	public WeatherEntry() {}
	
	public WeatherEntry(WeatherEntry weatherEntry) {
		this.id = weatherEntry.getId();
		this.date = weatherEntry.getDate();
		this.location = weatherEntry.getLocation();
		this.temperature = weatherEntry.getTemperature();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public List<Float> getTemperature() {
		return temperature;
	}
	public void setTemperature(List<Float> temperature) {
		this.temperature = temperature;
	}

}

