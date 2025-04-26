Elevator v1.0 = ElevatorTimeForInOrderTravel

**Assumption : 10 seconds travel per each floor is set
		
		
*******Enter Data in the below Format*******
Enter the starting floor 12
Enter floors you want to visit: 2,9,1,32
*******************************************
*******************************************

Executables :

java -jar ElevatorTimeForInOrderTravel.jar
           or 
ElevatorTimeForInOrderTravel.cmd
============================================================================
============================================================================

Elevator v1.1 = ElevatorTimeForInOrderTravelAsIs
		

**Assumption : 10 seconds travel per each floor is set
		
*******Enter Data in the below Format*******
Enter floors you want to visit: 12,2,9,1,32
*******************************************
*******************************************

Executables :

java -jar ElevatorTimeForInOrderTravelAsIs.jar
           or 
ElevatorTimeForInOrderTravelAsIs.cmd

============================================================================
============================================================================


Elevator v1.3 = ElevatorTimeForInOrderTravelAsIsWithParse


**Assumption : 10 seconds travel per each floor is set		
		
*******Enter Data in the below Format*******
elevator start=12 floor=2,9,1,32
*******************************************
*******************************************

Executables :

java -jar ElevatorTimeForInOrderTravelAsIsWithParse.jar
           or 
ElevatorTimeForInOrderTravelAsIsWithParse.cmd

============================================================================
============================================================================



Elevator v1.4 = ElevatorTestForAllUseCases


**Assumption : 10 seconds travel per each floor is set

 * This class computes the travel time for the floors visited through the elevator for the below combinations
 * 1. UP - go up first	from the starting floor
 * 2. DOWN - go down first from the starting floor
 * 3. SAME - go through the floors as entered	
		
		
*******Enter Data in the below Format*******
Enter the starting floor: 12
Enter the list of floors to visit: 10,19,5,3
Type Same, if you want to go through the floors as entered or UP or DOWN to go up or down first: up
*******************************************
*******************************************

Executables :

java -jar java -jar ElevatorTimeForCompleteUseCases.jar


============================================================================
============================================================================



