package com.example.restservice;

import java.util.List;

public class Location {
	private float lat;
	private float lon;
	private String city;              
	private String state;            //  enum
	
	public Location () {}
	
	public Location (Location location) {
		this.lat = location.getLat();
		this.lon = location.getLon();
		this.city = location.getCity();
		this.state = location.getState();
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
