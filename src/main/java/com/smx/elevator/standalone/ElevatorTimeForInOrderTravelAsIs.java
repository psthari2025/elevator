
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
public class ElevatorTimeForInOrderTravelAsIs
{
	/**
	 * @param TRAVEL_TIME_PER_FLOOR = This is set as a constant time of 10 seconds between consecutive floors
	 */
	public static final int TRAVEL_TIME_PER_FLOOR = 10;
	
	
	// This the main method
	public static void main(String[] args)
	{	
		printDataEntryInstructions();
		Scanner sc = null;		
		try 
		{
			sc = new Scanner(System.in);
		 
		     // Read a string
			boolean validInput = false;
			List<Integer> floorsToVisit = new ArrayList<Integer>();
			while(!validInput)
			{
				try
				{
					System.out.print("Enter the list of floors to visit: ");
					String userEnteredFloorsString = sc.nextLine();
					userEnteredFloorsString =userEnteredFloorsString.trim();
					floorsToVisit.addAll(
				            Arrays.asList(userEnteredFloorsString.split(","))
				                  .stream()
				                  .map(Integer::parseInt) // Convert strings to integers
				                  .toList());
					
					validInput =true;					
				}
				catch (NumberFormatException e) {
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
	
	private static void printDataEntryInstructions()
	{
		System.out.println("*******Enter Data in the below Format***********");
		System.out.println("Enter floors you want to visit: 12,2,9,1,32");
		System.out.println("*******************************************");
		System.out.println("*******************************************");
		
	}
}
		
		
	


