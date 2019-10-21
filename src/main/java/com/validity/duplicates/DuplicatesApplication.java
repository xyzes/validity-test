package com.validity.duplicates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The DuplicatesApplication class contains the main function and starts
 * the Spring Boot web application to display duplicates.
 */
@SpringBootApplication
public class DuplicatesApplication {

	/**
	 * MAIN function. This web application will display duplicates or near-duplicates
	 * from a contact list.
	 */
	public static void main(String[] args) {
		// Run Spring Boot application
		SpringApplication.run(DuplicatesApplication.class, args);
	}

}
