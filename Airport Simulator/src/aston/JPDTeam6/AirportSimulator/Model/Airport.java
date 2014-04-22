package aston.JPDTeam6.AirportSimulator.Model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import aston.JPDTeam6.AirportSimulator.Model.AirTrafficControllers.AirTrafficController;
import aston.JPDTeam6.AirportSimulator.Model.Planes.Plane;
import aston.JPDTeam6.SimulatorLibrary.Simulator;
import aston.JPDTeam6.SimulatorLibrary.Model.Actor;

public class Airport extends Actor {

//	public Queue<Plane> takeOffQueue = new ArrayDeque<Plane>();
//	public Queue<Plane> landingQueue = new ArrayDeque<Plane>();
	
	private AirTrafficController airTrafficController;
	public AirportEvent currentAirportEvent;
	
	public Airport(Simulator simulator)
	{
		super(simulator);
		
		//TODO possibly create this elsewhere?
		airTrafficController = new aston.JPDTeam6.AirportSimulator.Model.AirTrafficControllers.FIFO(this);
	}
	
	@Override
	public boolean onTick()
	{
	    if(currentAirportEvent == null || 
	       (currentAirportEvent.getCurrentPlane() != null && currentAirportEvent.getCurrentPlane().isDone()) || 
	       currentAirportEvent.getAirportState() == AirportState.WAITING)
	    {
	        scheduleNextEvent();
	        
	        if(currentAirportEvent.getAirportState() != AirportState.WAITING)
	        {
	            currentAirportEvent.getCurrentPlane().start();
	        }
	    }
	    
	    return false;
	}
	
    private void scheduleNextEvent()
    {
        currentAirportEvent = airTrafficController.getNextEvent();
    }

    public List<Plane> getPlanesTakingOff()
    {
        ArrayList<Plane> planes = new ArrayList<Plane>();
        
        for(Actor actor : getSimulator().actors)
        {
            if(actor instanceof Plane)
            {
                Plane plane = (Plane) actor;
                if((plane.getIntent() == Plane.PlaneIntent.TAKING_OFF) && (plane.isWaiting()))
                {
                    planes.add(plane);
                }
            }
        }
        
        return planes;
    }
    
    public List<Plane> getPlanesLanding()
    {
        ArrayList<Plane> planes = new ArrayList<Plane>();
        
        for(Actor actor : getSimulator().actors)
        {
            if(actor instanceof Plane)
            {
                Plane plane = (Plane) actor;
                if((plane.getIntent() == Plane.PlaneIntent.LANDING) && (plane.isWaiting()))
                {
                    planes.add(plane);
                }
            }
        }
        
        return planes;
    }
    
//	@Override
//	public boolean onTick()
//	{
////		checkCrashedPlanes();
//		
//		if(currentAirportEvent == null || currentAirportEvent.getAirportState() == AirportState.WAITING)
//		{
//		    scheduleNextEvent();
//		}
//		
////		Plane currentPlane = currentAirportEvent.getCurrentPlane();
//		AirportState airportState = currentAirportEvent.getAirportState();
//		
//		if(airportState == AirportState.TAKEOFF)
//		{
//			handleTakeOffTick();
//		}
//		else if(airportState == AirportState.LANDING)
//		{
//			handleLandingTick();
//		}
//		
//		return false;
//	}
//
//	public void queueTakeOff(Plane plane)
//	{
//		takeOffQueue.add(plane);
//	}
//	
//	public void queueLanding(Plane plane)
//	{
//		landingQueue.add(plane);
//	}
//	
//	private void handleTakeOffTick()
//	{
//	    Plane currentPlane = currentAirportEvent.getCurrentPlane();
//		if(currentPlane.hasTakenOff())
//		{
//			currentPlane.onTakenOff();
//			scheduleNextEvent();
//		}
//	}
//	
//	private void handleLandingTick()
//	{
//	    Plane currentPlane = currentAirportEvent.getCurrentPlane();
//		if(currentPlane.hasLanded())
//		{
//			currentPlane.onLanded();
//			scheduleNextEvent();
//		}
//	}
	
	/**
	 * TODO: This could possibly be handled from within the plane classes 
	 */
//	private void checkCrashedPlanes()
//	{
//		ArrayList<Plane> planesToRemove = new ArrayList<Plane>();
//		for(Plane plane : landingQueue)
//		{
//			if(!plane.canFly())
//			{
//				plane.onCrash();
//				planesToRemove.add(plane);
//			}
//		}
//		
//		//Remove crashed planes
//		for(Plane plane : planesToRemove)
//		{
//			plane.delete();
//		}
//	}
	
}

