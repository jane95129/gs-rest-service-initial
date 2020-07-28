package com.example.restservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class WeatherDataController {
	
	private static final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@ResponseStatus(HttpStatus.CREATED) //on normal completion
	@PostMapping("/weather/insert")
	public void weatherInsert(@RequestParam(value = "weather") String weatherJSONString) 
			throws JsonMappingException, JsonProcessingException {	
		
		weatherJSONString = weatherJSONString.replaceAll("\\n", "\\\\n");
		
		//Sample entry: {"id":1,"date":"1985-01-01","location":{"lat":36.1189,"lon":-86.6892,"city":"Palo Alto","state":"California"},"temperature":[37.3,36.8,36.4,36.0,35.6,35.3,35.0,34.9,35.8,38.0,40.2,42.3,43.8,44.9,45.5,45.7,44.9,43.0,41.7,40.8,39.9,39.2,38.6,38.1]}
		
		//JSON string to Java Object
		WeatherEntry obj = mapper.readValue(weatherJSONString, WeatherEntry.class);
		log.info(String.format("Inserting weather entry data for %d %s", obj.getId(), obj.getDate()));

		// Uses JdbcTemplate's Update operation to load data
		try {
			jdbcTemplate.update(
			      "INSERT INTO WEATHER_ENTRIES VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
			      obj.getId(), obj.getDate(), obj.getLocation().getLat(), obj.getLocation().getLon(), obj.getLocation().getCity(), obj.getLocation().getState(),
			      obj.getTemperature().get(0), obj.getTemperature().get(1), obj.getTemperature().get(2), obj.getTemperature().get(3),
			      obj.getTemperature().get(4), obj.getTemperature().get(5), obj.getTemperature().get(6), obj.getTemperature().get(7),
			      obj.getTemperature().get(8), obj.getTemperature().get(9), obj.getTemperature().get(10), obj.getTemperature().get(11),
			      obj.getTemperature().get(12), obj.getTemperature().get(13), obj.getTemperature().get(14), obj.getTemperature().get(15),
			      obj.getTemperature().get(16), obj.getTemperature().get(17), obj.getTemperature().get(18), obj.getTemperature().get(19),
			      obj.getTemperature().get(20), obj.getTemperature().get(21), obj.getTemperature().get(22), obj.getTemperature().get(23));

			log.info(String.format("weather entry inserted successfully : id: %d  date: %s", obj.getId(), obj.getDate()));
		} catch (DuplicateKeyException dke) {
			String msg = "Duplicate Key Exception caught, weather entry with id: " + obj.getId() + " already exists in table weather_entry";
			log.error(msg);
			throw new DupKeyException(msg);
		}
	}
	

	@ResponseStatus(HttpStatus.OK) //on normal completion
	@GetMapping("/weather/queryall")
	public List<WeatherEntry> weatherQueryall() 
			throws JsonMappingException, JsonProcessingException {		
		
		 log.info("Querying for all records from table weather_entries");
		 List<WeatherEntry> weatherEntries = jdbcTemplate.query(
				 "SELECT * FROM weather_entries order by id",
				 (rs, rowNum) -> new WeatherEntry(rs.getInt("id"), rs.getString("date"),
						 rs.getFloat("location_lat"), rs.getFloat("location_lon"), rs.getString("location_city"), rs.getString("location_state"),
				         rs.getFloat("temp1"), rs.getFloat("temp2"), rs.getFloat("temp3"), rs.getFloat("temp4"), rs.getFloat("temp5"), rs.getFloat("temp6"), 
				         rs.getFloat("temp7"), rs.getFloat("temp8"), rs.getFloat("temp9"), rs.getFloat("temp10"), rs.getFloat("temp11"), rs.getFloat("temp12"),
				         rs.getFloat("temp13"), rs.getFloat("temp14"), rs.getFloat("temp15"), rs.getFloat("temp16"), rs.getFloat("temp17"), rs.getFloat("temp18"),
				         rs.getFloat("temp19"), rs.getFloat("temp20"), rs.getFloat("temp21"), rs.getFloat("temp22"), rs.getFloat("temp23"), rs.getFloat("temp24")));
		 return weatherEntries;  
	}
	
	@ResponseStatus(HttpStatus.OK) //on normal completion
	@GetMapping("/weather/querybydate")
	public List<WeatherEntry> weatherQuerybydate(@RequestParam(value = "date") String dateString) 
			throws JsonMappingException, JsonProcessingException {		
		
		 dateString = dateString.replaceAll("\"", "");
		 log.info("Querying for weather_entries records where date = " + dateString);
		 List<WeatherEntry> weatherEntries = jdbcTemplate.query(
			 "SELECT * FROM weather_entries WHERE date = ?", new Object[] { dateString },
			 (rs, rowNum) -> new WeatherEntry(rs.getInt("id"), rs.getString("date"),
					 rs.getFloat("location_lat"), rs.getFloat("location_lon"), rs.getString("location_city"), rs.getString("location_state"),
			         rs.getFloat("temp1"), rs.getFloat("temp2"), rs.getFloat("temp3"), rs.getFloat("temp4"), rs.getFloat("temp5"), rs.getFloat("temp6"), 
			         rs.getFloat("temp7"), rs.getFloat("temp8"), rs.getFloat("temp9"), rs.getFloat("temp10"), rs.getFloat("temp11"), rs.getFloat("temp12"),
				     rs.getFloat("temp13"), rs.getFloat("temp14"), rs.getFloat("temp15"), rs.getFloat("temp16"), rs.getFloat("temp17"), rs.getFloat("temp18"),
				     rs.getFloat("temp19"), rs.getFloat("temp20"), rs.getFloat("temp21"), rs.getFloat("temp22"), rs.getFloat("temp23"), rs.getFloat("temp24"))
			 );

			 return weatherEntries;  
	}

	@ResponseStatus(HttpStatus.OK) //on normal completion
	@GetMapping("/weather/deleteall")
	public void weatherDeleteall() 
			throws JsonMappingException, JsonProcessingException {		
		
		log.info("Delete all records from table weather_entries");
		jdbcTemplate.update("DELETE FROM weather_entries");
	}	
	
}
