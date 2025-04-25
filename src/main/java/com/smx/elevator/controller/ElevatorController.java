/**
 * 
 */
package com.smx.elevator.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smx.elevator.dto.ElevatorDTO;
import com.smx.elevator.service.ElevatorService;

/**
 * 
 */
@RestController
@RequestMapping("/elevator")
public class ElevatorController {
	
	 private static final Logger LOGGER = LogManager.getLogger(ElevatorController.class);
	
	@Autowired
	private ElevatorService elevatorService;
	
	@GetMapping("/greeting")
	public String greeting() {
		return "Hello World";
	}
	
	@PostMapping("/compute")
	public ElevatorDTO computeElevatorTravelTime(@RequestBody ElevatorDTO elevatorDTO)
	{	
		return elevatorService.computeElevatorTravel(elevatorDTO);
	}

	
	
}
