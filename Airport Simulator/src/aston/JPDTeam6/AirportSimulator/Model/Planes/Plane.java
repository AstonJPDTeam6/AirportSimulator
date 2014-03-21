package aston.JPDTeam6.AirportSimulator.Model.Planes;

import aston.JPDTeam6.AirportSimulator.Model.AirportSimulator;
import aston.JPDTeam6.SimulatorLibrary.Model.Actor;
import aston.JPDTeam6.SimulatorLibrary.Model.Simulator;

public abstract class Plane extends Actor {

	protected long spawnTick;
	
	protected long timeToLand;
	protected long timeToTakeOff;
	protected long maxFlyingTime;
	
	public long planeEventTick = 0;
	
	public Plane(AirportSimulator simulator, long timeToLand, long timeToTakeOff, long maxFlyingTime) {
		super(simulator);
		spawnTick = simulator.getTick();
		
		this.timeToLand = timeToLand;
		this.timeToTakeOff = timeToTakeOff;
		this.maxFlyingTime = maxFlyingTime;
	}

	@Override
	public boolean onTick()
	{
		return true;
	}
	
	public long getTimeToLand()
	{
		return timeToLand;
	}
	public long getTimeToTakeOff()
	{
		return timeToTakeOff;
	}
	public long getMaxFlyingTime()
	{
		return maxFlyingTime;
	}

	public static float getSpawnProbability() throws Exception
	{
		throw new Exception("Must be implemented in child");
	}

	public void startTakeOff()
	{
		planeEventTick = getSimulator().getTick();
	}
	public boolean hasTakenOff()
	{
		return planeEventTick + timeToTakeOff < getSimulator().getTick();
	}
	
	public void startLanding()
	{
		planeEventTick = getSimulator().getTick();
	}
	public boolean hasLanded()
	{
		return planeEventTick + timeToLand < getSimulator().getTick();
	}
	
	public long getFlyingTimeLeft()
	{
		return (spawnTick + maxFlyingTime) - getSimulator().getTick();
	}
	
	public boolean canFly()
	{
		return getFlyingTimeLeft() > 0;
	}
	
	public void onTakenOff()
	{
		//do stuff to delete self
	}
	public void onLanded()
	{
		//do stuff to delete self
	}
	public void onCrash()
	{
		//do stuff to delete self
	}
	
}
