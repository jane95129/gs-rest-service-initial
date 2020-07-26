package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class GreetingController {

	private static ObjectMapper mapper = new ObjectMapper();
	
	@GetMapping("/weatherEntry")
	public WeatherEntry weatherEntry(@RequestParam(value = "id", defaultValue = "1") Integer id) {
		return new WeatherEntry();
	}
	
	@PostMapping("/weatherEntry")
	public WeatherEntry weatherEntry1(@RequestParam(value = "weatherEntry") String weatherEntryJSONString) 
			throws JsonMappingException, JsonProcessingException {		
		//JSON string to Java Object
		weatherEntryJSONString = weatherEntryJSONString.replaceAll("\\n", "\\\\n");
		WeatherEntry obj = mapper.readValue(weatherEntryJSONString, WeatherEntry.class);
		return obj;
	}
	
	@PostMapping("/greeting")
	public Greeting weatherEntry2(@RequestParam(value = "greeting") String greetingJSONString) 
			throws JsonMappingException, JsonProcessingException {		
		Greeting obj = mapper.readValue(greetingJSONString, Greeting.class);
		return obj;
	}	
	
/*
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	@PostMapping("/greeting")
	public Greeting greeting2(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
*/
	
}
