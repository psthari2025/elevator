/**
 * 
 */
package com.smx.elevator;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smx.elevator.dto.ElevatorDTO;
import com.smx.elevator.service.ElevatorService;

/**
 * 
 */
@SpringBootTest
public class ElevatorComputeTest
{
	private static final Logger LOGGER = LogManager.getLogger(ElevatorComputeTest.class);

	@Autowired
	private ElevatorService elevatorService;
	
	@Test
	public void computeElevatorFloorTravelTime()
	{
		ElevatorDTO elevatorDTO = new ElevatorDTO();
		elevatorDTO.setTravelDirection("SAME");
		elevatorDTO.setStartingFloor(12);
		elevatorDTO.setFloorstVisitRequest(List.of(2, 9, 1,32));
		
		ElevatorDTO computedElevatorDTO =elevatorService.computeElevatorTravel(elevatorDTO);
		
		LOGGER.info("Elevator Travel Time: {}", computedElevatorDTO.getTotalTravelTime());
		
	}
	
	
	@Test
	public void computeElevatorFloorTravelTimeUP()
	{
		ElevatorDTO elevatorDTO = new ElevatorDTO();
		elevatorDTO.setTravelDirection("UP");
		elevatorDTO.setStartingFloor(10);
		elevatorDTO.setFloorstVisitRequest(List.of(19, 5, 12,3));
		
		ElevatorDTO computedElevatorDTO =elevatorService.computeElevatorTravel(elevatorDTO);
		
		LOGGER.info("Elevator Travel Time: {}", computedElevatorDTO.getTotalTravelTime());
		LOGGER.info("ElevatorDTO: {} ", computedElevatorDTO.getFloorstTravelled());
		for(String message :computedElevatorDTO.getMessages().stream().collect(Collectors.toList()))
		{
			LOGGER.info("Log Data: {}", message);
			
		}
		
	}
	
	@Test
	public void computeElevatorFloorTravelTimeDown()
	{
		ElevatorDTO elevatorDTO = new ElevatorDTO();
		elevatorDTO.setTravelDirection("DOWN");
		elevatorDTO.setStartingFloor(10);
		elevatorDTO.setFloorstVisitRequest(List.of(19, 5, 12,3));
		
		ElevatorDTO computedElevatorDTO =elevatorService.computeElevatorTravel(elevatorDTO);
		
		LOGGER.info("Elevator Travel Time: {}", computedElevatorDTO.getTotalTravelTime());
		LOGGER.info("ElevatorDTO: {} ", computedElevatorDTO.getFloorstTravelled());
		for(String message :computedElevatorDTO.getMessages().stream().collect(Collectors.toList()))
		{
			LOGGER.info("Log Data: {}", message);
			
		}
		
		
	}


}
