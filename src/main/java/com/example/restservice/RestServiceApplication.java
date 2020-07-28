package com.example.restservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

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
	 }
}
