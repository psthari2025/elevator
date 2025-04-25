/**
 * 
 */
package com.smx.elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */
public class ElevatorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{		
		Scanner sc = null;
		
		try 
		{
		 sc = new Scanner(System.in);
		 
	     // Read a string
	     System.out.print("Enter the starting floor ");
	     int startingFloor =  Integer.parseInt(sc.nextLine());
	     
	
	     // Read an integer
	     System.out.print("Enter your  floors: ");
	     String floors = sc.nextLine();
	     String [] floorsArray=   floors.split(",");
	     List<Integer>floorsInt = new ArrayList<Integer>();
	     
	     floorsInt.add(startingFloor);
	     
			for (int i = 0; i < floorsArray.length; i++) {
				floorsInt.add(Integer.parseInt(floorsArray[i]));
			}
			
			floorsInt.sort((a,b) -> a-b);
			
			// Print the sorted list
			System.out.println("Sorted list: " + floorsInt);
			
			int startingFloorIndex =floorsInt.indexOf(startingFloor);
			
			System.out.println("Starting floor index: " + startingFloorIndex);
			
			// if the starting floor is the first floor, then go up
			if(startingFloorIndex == 0)
			{
				System.out.println("Going up");
				travelUp(floorsInt, startingFloorIndex);				
				
			}
			// if the starting floor is the top floor, then go down
			else if (startingFloorIndex == floorsInt.size() - 1)
			{
				System.out.println("Going down");
				travelDown(floorsInt, startingFloorIndex);
			} else 
			{
				// if the starting floor is in the middle, then check the direction
				System.out.println("Going up/down (U/D)");
			}			
	     
	     System.out.print("Enter the direction (up/down), you want to go first");
	     String direction = sc.nextLine();
	     
	     while(!"UP".equalsIgnoreCase(direction) && !"DOWN".equalsIgnoreCase(direction) && !"U".equalsIgnoreCase(direction) && !"D".equalsIgnoreCase(direction))
         {
        	 System.out.print("Invalid direction, Enter the direction (up/down), you want to go first");
             direction = sc.nextLine();
         }
	     
	     direction = direction.toUpperCase();
	     
	     if ("U".contains(direction)) {
	     		System.out.println("Going up first");
	     		List<Integer> floorsIntCopy = new ArrayList<>(floorsInt.subList(startingFloorIndex, floorsInt.size()));
	     		travelUp(floorsIntCopy, 0);
	     		travelDown(floorsInt, floorsInt.size()-1);
	     	} else {
	     		System.out.println("Going down first");
	     		
	     		List<Integer> floorsToGoDown = new ArrayList<>(floorsInt.subList(0, startingFloorIndex+1));
	     		travelDown(floorsToGoDown, startingFloorIndex);
	     		travelUp(floorsInt, 0);
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




	private static void travelUp(List<Integer> floorsInt, int startingFloorIndex) {
		int travelTime = 0;
		
		for (int i = startingFloorIndex ; i < floorsInt.size()-1; i++) {
			System.out.println("Going up to floor: " + floorsInt.get(i+1));
			
			
			int floorsToTravel = floorsInt.get(i+1)-floorsInt.get(i);
			
			System.out.println("floorsToTravel: " + floorsToTravel);
			
			int j =floorsInt.get(i);
			
			while (floorsToTravel > 0) {
				
				if(j+1 ==floorsInt.get(i+1))
				{
					System.out.println("Ding Dong, time to exit your floor " + (j+1));
					
				}
				else
				{
					System.out.println("Travelling through floor: " + (j +1));
					
				}
				
				j++;
				travelTime += 10;
				floorsToTravel--;
			}

		}
		// TODO Auto-generated method stub
		
		System.out.println("Travel time: " + travelTime + " seconds");
		
	}
	
	
	private static void travelDown(List<Integer> floorsInt, int startingFloorIndex) {
		int travelTime = 0;
		
		for (int i = startingFloorIndex ; i > 0; i--) {
			System.out.println("Going down to floor: " + floorsInt.get(i-1));

			
			int floorsToTravel = Math.abs(floorsInt.get(i)-floorsInt.get(i-1));
			
			System.out.println("floorsToTravel: " + floorsToTravel);
			
			int j =floorsInt.get(i);
			
			while (floorsToTravel > 0) {
				
				if(j-1 ==floorsInt.get(i-1))
				{
					System.out.println("Ding Dong, time to exit your floor " + (j-1));
					
				}
				else
				{
					System.out.println("Travelling through floor: " + (j -1));
					
				}
				j--;
				travelTime += 10;
				floorsToTravel--;
			}

		}
		
		
		System.out.println("Travel time: " + travelTime + " seconds");
		
	}
	

}
