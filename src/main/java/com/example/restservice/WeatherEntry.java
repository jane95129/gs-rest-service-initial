package com.example.restservice;

import java.util.Arrays;
import java.util.List;

public class WeatherEntry {
	private Integer id;
	private String date;
	private Location location;              
	private List<Float> temperature;            //  List
	

	public WeatherEntry() {}
	
	public WeatherEntry(int id, String date) {
		this.id = id;
		this.date = date;		
	}
	
	public WeatherEntry(WeatherEntry weatherEntry) {
		this.id = weatherEntry.getId();
		this.date = weatherEntry.getDate();
		this.location = weatherEntry.getLocation();
		this.temperature = weatherEntry.getTemperature();
	}
	
	public WeatherEntry(int id, String date, 
				 float location_lat, float location_lon, String location_city, String location_state,
				 float temp1, float temp2, float temp3, float temp4, float temp5, float temp6, float temp7, float temp8,
				 float temp9, float temp10, float temp11, float temp12, float temp13, float temp14, float temp15, float temp16,
				 float temp17, float temp18, float temp19, float temp20, float temp21, float temp22, float temp23, float temp24)
	{
		this.id = id;
		this.date = date;
		this.location = new Location(location_lat, location_lon, location_city, location_state);
		this.temperature = Arrays.asList(new Float[] {
				temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8,
				temp9, temp10, temp11, temp12, temp13, temp14, temp15, temp16,
				temp17, temp18, temp19, temp20, temp21, temp22, temp23, temp24	
		});
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

