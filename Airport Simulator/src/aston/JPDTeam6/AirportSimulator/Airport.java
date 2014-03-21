package aston.JPDTeam6.AirportSimulator;

import java.util.ArrayDeque;
import java.util.Queue;

public class Airport extends Actor {

	private Queue<Plane> takeOffQueue = new ArrayDeque<Plane>();
	private Queue<Plane> landingQueue = new ArrayDeque<Plane>();
	
	public Airport(Simulator simulator) {
		super(simulator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTick() {
		// TODO Auto-generated method stub
		return false;
	}

	public void queueTakeoff(Plane plane)
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
	
	private PlaneEvent getNextEvent()
	{
		
	}
	
	private class PlaneEvent
	{
		public char type; //t = take off, l = landing
		public Plane plane;
	}
	
}
