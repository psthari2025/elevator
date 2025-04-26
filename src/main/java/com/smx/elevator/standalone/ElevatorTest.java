/**
 * 
 */
package com.smx.elevator.standalone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;



/**
 * @author kolli
 * This class computes the travel time for the floors visited through the elevator for the below combinations
 * 1. UP - go up first	from the starting floor
 * 2. DOWN - go down first from the starting floor
 * 3. SAME - go through the floors as entered	
 */
public class ElevatorTest
{
	public static final int TRAVEL_TIME_PER_FLOOR = 10;

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{		
		Scanner sc = null;		
		try 
		{
			sc = new Scanner(System.in);
			 
			boolean validInput = false;
			List<Integer> floorsToVisit = new ArrayList<Integer>();
			int startingFloor = -1;
			while(!validInput)
			{
				try
				{
				     // Read a string
				     System.out.print("Enter the starting floor: ");
				     startingFloor =  Integer.parseInt(sc.nextLine());
				     System.out.print("Enter the list of floors to visit: ");
				     String userEnteredFloorsString = sc.nextLine();
					 userEnteredFloorsString = userEnteredFloorsString.trim();
					
					 floorsToVisit.add(startingFloor);
					 floorsToVisit.addAll(List.of(userEnteredFloorsString.split(",")).stream().map(Integer::parseInt).toList());

					validInput = true;
				} 
				catch (NumberFormatException e)
				{
					System.out.println("Invalid input. Please enter a comma-separated list of integers.");
				}
				
			}
			
			System.out.print("Type Same, if you want to go through the floors as entered or UP or DOWN to go up or down first: ");
            String direction = sc.nextLine();
            direction = direction.toUpperCase();
            
			while (!("UP".equalsIgnoreCase(direction) || "DOWN".equalsIgnoreCase(direction)
					|| "U".equalsIgnoreCase(direction) || "D".equalsIgnoreCase(direction)
					|| "SAME".equalsIgnoreCase(direction)))
			{
				System.out.print("Invalid direction, Enter the direction (up/down), you want to go first");
				direction = sc.nextLine();
			}

			FloorTravel floorTravel = new FloorTravel();
			
		     if ("SAME".equalsIgnoreCase(direction))
		     {
		    	 
		    	 System.out.println("Going Floors as entered");
		     	 floorTravel =computeTravelTimeAsEntered(floorsToVisit, floorTravel);
		     	 System.out.print("total travel time, floors visited in order: " +floorTravel.getTravelTime() + " " + floorsToVisit.stream().map(String :: valueOf).collect(Collectors.joining(",")));
		         return;
		     }				

			floorsToVisit.sort((a,b) -> a-b);
				
			// Print the sorted list
			System.out.println("Sorted list: " + floorsToVisit);
			
			int startingFloorIndex =floorsToVisit.indexOf(startingFloor);
			
			System.out.println("Starting floor index: " + startingFloorIndex);
			
			// if the starting floor is the first floor, then go up
			if(startingFloorIndex == 0)
			{
				System.out.println("Going up");
				travelUp(floorsToVisit, startingFloorIndex,floorTravel);				
				
			}
			// if the starting floor is the top floor, then go down
			else if (startingFloorIndex == floorsToVisit.size() - 1)
			{
				System.out.println("Going down");
				travelDown(floorsToVisit, startingFloorIndex,floorTravel);
			} else 
			{

			     
			     if ("UP".equalsIgnoreCase(direction) || "U".equalsIgnoreCase(direction))
			     {
			     		System.out.println("Going up first");
			     		List<Integer> floorsIntCopy = new ArrayList<>(floorsToVisit.subList(startingFloorIndex, floorsToVisit.size())); 
			     		floorTravel =travelUp(floorsIntCopy, 0, floorTravel);
			     		floorTravel =travelDown(floorsToVisit, floorsToVisit.size()-1,floorTravel);
			     		System.out.println("Total Travel time: " + floorTravel.getTravelTime() + " seconds");	
			     		System.out.print(floorTravel.getFloorNumberVisited().stream().map(String :: valueOf).collect(Collectors.joining(",")));
			     } 
			     else
			     {
			     		System.out.println("Going down first");	     		
			     		List<Integer> floorsToGoDown = new ArrayList<>(floorsToVisit.subList(0, startingFloorIndex+1));
			     		floorTravel= travelDown(floorsToGoDown, startingFloorIndex, floorTravel);
			     		floorTravel = travelUp(floorsToVisit, 0, floorTravel);
			     		System.out.println("Total Travel time: " + floorTravel.getTravelTime()+ " seconds");
			     		System.out.print(floorTravel.getFloorNumberVisited().stream().map(String :: valueOf).collect(Collectors.joining(",")));
			     }
			}
	    }
		catch(Exception e)
		{
			e.printStackTrace();		
		}
		finally
		{
			if (sc != null) {
				sc.close();
			}		
		}

	}
	
	public static class FloorTravel
	{
		List<Integer>floorNumberVisited = new ArrayList<Integer>();
		int travelTime;

		public FloorTravel() 
		{			
		
		}
		
		public int getTravelTime()
        {
            return travelTime;
        }
		
		public void setTravelTime(int travelTime) {
			this.travelTime = travelTime;
		}
		
		public List<Integer> getFloorNumberVisited() {
			return floorNumberVisited;
		}
		
		public void setFloorNumberVisited(List<Integer> floorNumber) {
			this.floorNumberVisited = floorNumber;
		}
		
		public void addFloorNumberVisited(int floorNumber) {
			this.floorNumberVisited.add(floorNumber);
		}


	}
	
	private static FloorTravel travelUp(List<Integer> floorsToVisit, int startingFloorIndex, FloorTravel floorTravel)
	{
		int travelTime = floorTravel.getTravelTime();
		
		for (int i = startingFloorIndex ; i < floorsToVisit.size()-1; i++)
		{
			if(!floorTravel.getFloorNumberVisited().contains(floorsToVisit.get(i)))
			{
				floorTravel.addFloorNumberVisited(floorsToVisit.get(i));				
			}
			
			System.out.println("Going up to floor: " + floorsToVisit.get(i+1));
			
			
			int floorsToTravel = floorsToVisit.get(i+1)-floorsToVisit.get(i);
			
			System.out.println("floorsToTravel: " + floorsToTravel);
			
			int j =floorsToVisit.get(i);
			
			while (floorsToTravel > 0)
			{				
				if(j+1 ==floorsToVisit.get(i+1))
				{
					if(!floorTravel.getFloorNumberVisited().contains(floorsToVisit.get(i+1)))
					{
						floorTravel.addFloorNumberVisited(floorsToVisit.get(i+1));
						System.out.println("Ding Dong, time to exit your floor " + (j+1));
						
					}
				
					
				}
				else
				{
					System.out.println("Travelling through floor: " + (j +1));
					
				}
				
				j++;
				travelTime += TRAVEL_TIME_PER_FLOOR;
				floorsToTravel--;
			}

		}
		System.out.println("Travel time: " + travelTime + " seconds");
		floorTravel.setTravelTime(travelTime);
		return floorTravel;
		
	}
	
	
	private static FloorTravel travelDown(List<Integer> floorsToVisit, int startingFloorIndex, FloorTravel floorTravel)
	{
		int travelTime = floorTravel.getTravelTime();
		
		for (int i = startingFloorIndex ; i > 0; i--)
		{
			if(!floorTravel.getFloorNumberVisited().contains(floorsToVisit.get(i)))
			{
				floorTravel.addFloorNumberVisited(floorsToVisit.get(i));				
			} 
			System.out.println("Going down to floor: " + floorsToVisit.get(i-1));
			int floorsToTravel = Math.abs(floorsToVisit.get(i)-floorsToVisit.get(i-1));
			
			System.out.println("floorsToTravel: " + floorsToTravel);
			
			int j =floorsToVisit.get(i);
			
			while (floorsToTravel > 0)
			{				
				if(j-1 ==floorsToVisit.get(i-1))
				{
					if(!floorTravel.getFloorNumberVisited().contains(floorsToVisit.get(i-1)))
					{
						floorTravel.addFloorNumberVisited(floorsToVisit.get(i-1));
						System.out.println("Ding Dong, time to exit your floor " + (j-1));					
						
					}
				}
				else
				{
					System.out.println("Travelling through floor: " + (j -1));
					
				}
				j--;
				travelTime += TRAVEL_TIME_PER_FLOOR;
				floorsToTravel--;
			}
		}	
		
		System.out.println("Travel time: " + travelTime + " seconds");	
		floorTravel.setTravelTime(travelTime);
		return floorTravel;
	}
	
	private static FloorTravel computeTravelTimeAsEntered(List<Integer> floorstVisitRequest, FloorTravel floorTravel) 
	{
		 int travelTime =floorTravel.getTravelTime();
		 for( int i= 0; i < floorstVisitRequest.size()-1 ; i++)
		 {
			 int numFloors= Math.abs(floorstVisitRequest.get(i)-floorstVisitRequest.get(i+1));
			 
			 while(numFloors >0)
			 {
				travelTime+=TRAVEL_TIME_PER_FLOOR;
				numFloors--;					
			 }				
		 }
			floorTravel.setTravelTime(travelTime);
			return floorTravel;
		
	}
	

}
