package com.smx.elevator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.smx.elevator.controller.ElevatorController;

@SpringBootApplication
public class ElevatorApplication {

	private static final Logger LOGGER = LogManager.getLogger(ElevatorApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Starting Elevator Application");
		SpringApplication.run(ElevatorApplication.class, args);
	}

}
