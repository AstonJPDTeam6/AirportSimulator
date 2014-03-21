package aston.JPDTeam6.AirportSimulator.Model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import aston.JPDTeam6.AirportSimulator.Model.Planes.Plane;
import aston.JPDTeam6.SimulatorLibrary.Model.Actor;
import aston.JPDTeam6.SimulatorLibrary.Model.Simulator;

public class Airport extends Actor {

	public Queue<Plane> takeOffQueue = new ArrayDeque<Plane>();
	public Queue<Plane> landingQueue = new ArrayDeque<Plane>();
	
	private char airportState = '\0'; //t=takeoff, l=landing, other=nothing
	private Plane currentPlane = null;
	
	public Airport(Simulator simulator)
	{
		super(simulator);
	}

	@Override
	public boolean onTick()
	{
		checkCrashedPlanes();
		
		if((airportState != 't' && airportState != 'l') || currentPlane == null)
		{
			scheduleNextTakeOffLanding();
		}
		else if(airportState == 't')
		{
			handleTakeOffTick();
		}
		else if(airportState == 'l')
		{
			handleLandingTick();
		}
		
		return false;
	}

	public void queueTakeOff(Plane plane)
	{
		takeOffQueue.add(plane);
	}
	
	public void queueLanding(Plane plane)
	{
		landingQueue.add(plane);
	}
	
	private Plane getNextTakingOff()
	{
		return takeOffQueue.poll();
	}
	
	private Plane getNextLanding()
	{
		return landingQueue.poll();
	}
	
	private void scheduleNextTakeOffLanding()
	{
		//Randomly pick whether a plane should be scheduled to take off or land
		boolean nextTakeOff = getSimulator().getRandom().nextBoolean();
		
		if(nextTakeOff)
		{
			currentPlane = getNextTakingOff();
		}
		else
		{
			currentPlane = getNextLanding();
		}
		
		if(currentPlane == null)
		{
			airportState = '\0';
			currentPlane.planeEventTick = 0;
		}
		else
		{
			airportState = nextTakeOff ? 't' : 'l';
			currentPlane.planeEventTick = getSimulator().getTick();
			
			if(nextTakeOff)
			{
				currentPlane.startTakeOff();
				handleTakeOffTick();
			}
			else
			{
				currentPlane.startLanding();
				handleLandingTick();
			}
		}
		
	}
	
	private void handleTakeOffTick()
	{
		if(currentPlane.hasTakenOff())
		{
			currentPlane.onTakenOff();
		}
	}
	
	private void handleLandingTick()
	{
		if(currentPlane.hasLanded())
		{
			currentPlane.onLanded();
		}
	}
	
	private void checkCrashedPlanes()
	{
		ArrayList<Plane> planesToRemove = new ArrayList<Plane>();
		for(Plane plane : landingQueue)
		{
			if(!plane.canFly())
			{
				plane.onCrash();
				planesToRemove.add(plane);
			}
		}
		
		//Remove crashed planes
		for(Plane plane : planesToRemove)
		{
			landingQueue.remove(plane);
			
			if(currentPlane.equals(plane))
			{
//				currentPlane
			}
		}
	}
	
}
