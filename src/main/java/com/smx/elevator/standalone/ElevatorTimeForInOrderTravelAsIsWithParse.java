
package com.smx.elevator.standalone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author kolli
 *  This class computes the travel time for the floors visited through the elevator
 */
public class ElevatorTimeForInOrderTravelAsIsWithParse
{
	/**
	 * @param TRAVEL_TIME_PER_FLOOR = This is set as a constant time of 10 seconds between consecutive floors
	 */
	public static final int TRAVEL_TIME_PER_FLOOR = 10;
	
	
	// This the main method
	public static void main(String[] args)
	{		
		Scanner sc = null;
		
		try 
		{
			sc = new Scanner(System.in);
		 
		     // Read a string
			boolean validInput = false;
			List<Integer>floorsToVisit =new ArrayList<Integer>();
			while(!validInput)
			{
				try
				{
					System.out.print("Enter the list of floors to visit, e.g. elevator start=12 floor=2,9,1,32 ");
					String userSingleInput = sc.nextLine();
					
					userSingleInput = userSingleInput.trim();
					
					String startingFloor =userSingleInput.substring("elevator start=".length(),userSingleInput.indexOf("floor=") ).trim();
					String floorsString =userSingleInput.substring(userSingleInput.indexOf("floor=")+"floor=".length() ,userSingleInput.length() ).trim();

					floorsToVisit.add(Integer.parseInt(startingFloor));
					floorsToVisit.addAll(
				            Arrays.asList(floorsString.split(","))
				                  .stream()
				                  .map(Integer::parseInt) // Convert strings to integers
				                  .toList());
					

					validInput =true;					
				}
				catch (Exception e) {
					System.out.println("Invalid input. Please enter a comma-separated list of integers.");
				}
			}
			
			 int travelTime =0;
			 for( int i= 0; i < floorsToVisit.size()-1 ; i++)
			 {
				 int numFloors= Math.abs(floorsToVisit.get(i)-floorsToVisit.get(i+1));
				 
				 while(numFloors >0)
				 {
					travelTime+=TRAVEL_TIME_PER_FLOOR;
					numFloors--;					
				 }				
			 }
			
			 System.out.print("total travel time, floors visited in order: " +travelTime + " " + floorsToVisit.stream().map(String :: valueOf).collect(Collectors.joining(",")));

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
}
		
		
	


