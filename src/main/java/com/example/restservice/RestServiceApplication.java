package com.example.restservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class RestServiceApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}
        
	@Autowired
    JdbcTemplate jdbcTemplate;

	 @Override
	public void run(String... strings) throws Exception {

		 log.info("Creating table weather_entries");
		 
		 jdbcTemplate.execute("DROP TABLE weather_entries IF EXISTS");
		 jdbcTemplate.execute("CREATE TABLE weather_entries(" +
                 "id SERIAL, date VARCHAR(255), "
				 + "location_lat FLOAT, location_lon FLOAT, location_city VARCHAR(255), location_state VARCHAR(255), "
				 + "temp1 FLOAT, temp2 FLOAT, temp3 FLOAT, temp4 FLOAT, temp5 FLOAT, temp6 FLOAT, temp7 FLOAT, temp8 FLOAT, "
				 + "temp9 FLOAT, temp10 FLOAT, temp11 FLOAT, temp12 FLOAT, temp13 FLOAT, temp14 FLOAT, temp15 FLOAT, temp16 FLOAT, "
				 + "temp17 FLOAT, temp18 FLOAT, temp19 FLOAT, temp20 FLOAT, temp21 FLOAT, temp22 FLOAT, temp23 FLOAT, temp24 FLOAT"
				 + ")");

		 log.info("weather_entries created");
		 // String weatherEntry = {"id":1,"date":"1985-01-01","location":{"lat":36.1189,"lon":-86.6892,"city":"Palo Alto","state":"California"},"temperature":[37.3,36.8,36.4,36.0,35.6,35.3,35.0,34.9,35.8,38.0,40.2,42.3,43.8,44.9,45.5,45.7,44.9,43.0,41.7,40.8,39.9,39.2,38.6,38.1]}

		 jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
		 jdbcTemplate.execute("CREATE TABLE customers(" +
                  "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

		 // Split up the array of whole names into an array of first/last names
		 List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                  .map(name -> name.split(" "))
                  .collect(Collectors.toList());

		 // Use a Java 8 stream to print out each tuple of the list
		 splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

		 // Uses JdbcTemplate's batchUpdate operation to bulk load data
		 jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

		 log.info("Querying for customer records where first_name = 'Josh':");
		 jdbcTemplate.query(
				 "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
				 (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))	 
			 );
	 }
}
