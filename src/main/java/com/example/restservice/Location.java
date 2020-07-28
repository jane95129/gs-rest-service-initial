package com.example.restservice;

public class Location {
	private float lat;
	private float lon;
	private String city;              
	private String state;            //  enum
	
	public Location () {}
	
	public Location (float location_lat, float location_lon, String location_city, String location_state) {
		this.lat = location_lat;
		this.lon = location_lon;
		this.city = location_city;
		this.state = location_state;
	}
	
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	
}
