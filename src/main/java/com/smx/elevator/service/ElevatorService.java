/**
 * 
 */
package com.smx.elevator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smx.elevator.ElevatorComputeUtitliy;
import com.smx.elevator.dto.ElevatorDTO;

/**
 * 
 */
@Service
public class ElevatorService
{
	@Autowired
	private ElevatorComputeUtitliy elevatorComputeUtitliy;
	
	public ElevatorDTO computeElevatorTravel(ElevatorDTO elevatorDto)
	{
		return elevatorComputeUtitliy.compute(elevatorDto);
		
	}

}
