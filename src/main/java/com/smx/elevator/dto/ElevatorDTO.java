/**
 * 
 */
package com.smx.elevator.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */


public class ElevatorDTO
{
	private int startingFloor;
	private int totalTravelTime;
	private List<Integer> floorstVisitRequest;
	private List<Integer> floorstTravelled;
	private String travelDirection;
	
	private List<String> messages = new ArrayList<String>();
	
	
	public int getStartingFloor() {
		return startingFloor;
	}

	public void setStartingFloor(int startingFloor) {
		this.startingFloor = startingFloor;
	}

	public int getTotalTravelTime() {
		return totalTravelTime;
	}

	public void setTotalTravelTime(int totalTravelTime) {
		this.totalTravelTime = totalTravelTime;
	}

	public List<Integer> getFloorstTravelled() {
		return floorstTravelled;
	}

	public void setFloorstTravelled(List<Integer> floorstTravelled) {
		this.floorstTravelled = floorstTravelled;
	}
	
	public void addFloorTravelled(int floor)
	{
		if(this.floorstTravelled == null)
		{
			this.floorstTravelled  = new ArrayList<Integer>();
		}
		this.floorstTravelled.add(floor);
		
	}
	
	public List<Integer> getFloorstVisitRequest() {
		return floorstVisitRequest;
	}
	
	public void setFloorstVisitRequest(List<Integer> floorstVisitRequest) {
		this.floorstVisitRequest = floorstVisitRequest;
	}

	public String getTravelDirection() {
		return travelDirection;
	}

	public void setTravelDirection(String travelDirection) {
		this.travelDirection = travelDirection;
	}
	
	public List<String> getMessages() {
		return messages;
	}
	
	public void addMessage(String message) {
		this.messages.add(message);
	}
	

	@Override
	public String toString() {
		return "ElevatorDTO [startingFloor=" + startingFloor + ", totalTravelTime=" + totalTravelTime
				+ ", floorstTravelled=" + floorstTravelled + "]";
	}
	
	

}
