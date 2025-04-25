/**
 * 
 */
package com.smx.elevator;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.smx.elevator.dto.ElevatorDTO;

/**
 * @author kolli
 */
@Component
public class ElevatorComputeUtitliy 
{
	
	private static final Logger LOGGER = LogManager.getLogger(ElevatorComputeUtitliy.class);
	
	public static final int TRAVEL_TIME_PER_FLOOR = 10;
	
	public ElevatorDTO compute(ElevatorDTO elevatorDto)
	{

		try 
		{
	
		     int startingFloor =  elevatorDto.getStartingFloor();
		     List<Integer> floorsToVisit =elevatorDto.getFloorstVisitRequest();
		     floorsToVisit.add(0, startingFloor);
		     String direction = elevatorDto.getTravelDirection();	     
		     direction = direction.toUpperCase();
		     
		     if ("SAME".equalsIgnoreCase(direction))
		     {
		    	 
		     	 elevatorDto.addMessage("Going Floors as entered");
		     	 computeTravelTimeAsEntered(elevatorDto.getFloorstVisitRequest(), elevatorDto);
		         return elevatorDto;
		     }
				
		     floorsToVisit.sort((a,b) -> a-b);
				
			// Print the sorted list
			elevatorDto.addMessage("Sorted list: " + floorsToVisit);
			
			int startingFloorIndex =floorsToVisit.indexOf(startingFloor);
			
			elevatorDto.addMessage("Starting floor index: " + startingFloorIndex);
			
			// if the starting floor is the first floor, then go up
			if(startingFloorIndex == 0)
			{
				elevatorDto.addMessage("Going up");
				travelUp(floorsToVisit, startingFloorIndex, elevatorDto);				
				
			}
			// if the starting floor is the top floor, then go down
			else if (startingFloorIndex == floorsToVisit.size() - 1)
			{
				elevatorDto.addMessage("Going down");
				travelDown(floorsToVisit, startingFloorIndex,elevatorDto);
			} else 
			{
				// if the starting floor is in the middle, then check the direction
				 elevatorDto.addMessage("Going up/down (U/D)");

			     
			     if ("UP".equalsIgnoreCase(direction)) {
			     		elevatorDto.addMessage("Going up first");
			     		List<Integer> floorsIntCopy = new ArrayList<>(floorsToVisit.subList(startingFloorIndex, floorsToVisit.size()));
			     		travelUp(floorsIntCopy, 0,elevatorDto);
			     		travelDown(floorsToVisit, floorsToVisit.size()-1, elevatorDto);
			     	}

			     
			     else 
			     {
		     		elevatorDto.addMessage("Going down first");
		     		
		     		List<Integer> floorsToGoDown = new ArrayList<>(floorsToVisit.subList(0, startingFloorIndex+1));
		     		travelDown(floorsToGoDown, startingFloorIndex, elevatorDto);
		     		travelUp(floorsToVisit, 0,elevatorDto);
			     }
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();		
		}
		
		return elevatorDto;

		
	}

	private void computeTravelTimeAsEntered(List<Integer> floorstVisitRequest, ElevatorDTO elevatorDto) 
	{
		 int travelTime =0;
		 for( int i= 0; i < floorstVisitRequest.size()-1 ; i++)
		 {
			 int numFloors= Math.abs(floorstVisitRequest.get(i)-floorstVisitRequest.get(i+1));
			 
			 while(numFloors >0)
			 {
				travelTime+=TRAVEL_TIME_PER_FLOOR;
				numFloors--;					
			 }				
		 }
		elevatorDto.setFloorstTravelled(floorstVisitRequest);
		elevatorDto.setTotalTravelTime(travelTime);
		elevatorDto.addMessage("Travel time: " + travelTime + " seconds");
		
	}

	private static void travelUp(List<Integer> floorsToVisit, int startingFloorIndex, ElevatorDTO elevatorDto) {
		int travelTime = 0;
		
		for (int i = startingFloorIndex ; i < floorsToVisit.size()-1; i++)
		{
			
			if (i == startingFloorIndex)
			{
				elevatorDto.addMessage("Starting floor: " + floorsToVisit.get(i));
				elevatorDto.addFloorTravelled(floorsToVisit.get(i));
			}
			elevatorDto.addMessage("Going up to floor: " + floorsToVisit.get(i+1));
			
			
			int floorsToTravel = floorsToVisit.get(i+1)-floorsToVisit.get(i);
			
			elevatorDto.addMessage("floorsToTravel: " + floorsToTravel);
			
			int j =floorsToVisit.get(i);
			
			while (floorsToTravel > 0) {
				
				if(j+1 ==floorsToVisit.get(i+1))
				{
					if (!(elevatorDto.getFloorstTravelled() != null && elevatorDto.getFloorstTravelled().contains(floorsToVisit.get(i+1)))) 
	                {
						
						elevatorDto.addFloorTravelled(floorsToVisit.get(i+1));
						elevatorDto.addMessage("Ding Dong, time to exit your floor " + (j+1));
					}
					else
					{
						elevatorDto.addMessage("Travelling through floor: " + (j +1));					
					}
	              
					
				}
				else
				{
					elevatorDto.addMessage("Travelling through floor: " + (j +1));
					
				}
				
				j++;
				travelTime += TRAVEL_TIME_PER_FLOOR;
				floorsToTravel--;
			}

		}
		
		elevatorDto.setTotalTravelTime(travelTime);
		elevatorDto.addMessage("Travel time: " + travelTime + " seconds");
		
	}
	
	
	private static void travelDown(List<Integer> floorsToVisit, int startingFloorIndex, ElevatorDTO elevatorDto) {
		int travelTime = 0;
		
		for (int i = startingFloorIndex ; i > 0; i--) 
		{
			
			if (i == startingFloorIndex)
			{
				elevatorDto.addMessage("Starting floor: " + floorsToVisit.get(i));
				elevatorDto.addFloorTravelled(floorsToVisit.get(i));
			}
			
			elevatorDto.addMessage("Going down to floor: " + floorsToVisit.get(i-1));

			
			int floorsToTravel = Math.abs(floorsToVisit.get(i)-floorsToVisit.get(i-1));
			
			elevatorDto.addMessage("floorsToTravel: " + floorsToTravel);
			
			int j =floorsToVisit.get(i);
			
			while (floorsToTravel > 0) {
				
				if(j-1 ==floorsToVisit.get(i-1))
				{
					if (!(elevatorDto.getFloorstTravelled() != null && elevatorDto.getFloorstTravelled().contains(floorsToVisit.get(i-1))))
                	{
					
                		elevatorDto.addFloorTravelled(floorsToVisit.get(i-1));
    					elevatorDto.addMessage("Ding Dong, time to exit your floor " + (j-1));
                	}
					else
					{
						elevatorDto.addMessage("Travelling through floor: " + (j -1));						
					}
				}
				else
				{
					elevatorDto.addMessage("Travelling through floor: " + (j -1));
					
				}
				j--;
				travelTime += TRAVEL_TIME_PER_FLOOR;
				floorsToTravel--;
			}

		}	
		
		elevatorDto.setTotalTravelTime(travelTime);
		elevatorDto.addMessage("Travel time: " + travelTime + " seconds");
		
	}

}
