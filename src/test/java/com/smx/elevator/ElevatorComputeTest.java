/**
 * 
 */
package com.smx.elevator;

import java.util.List;

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

}
