package aston.JPDTeam6.AirportSimulator.Model.Planes;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;
import aston.JPDTeam6.SimulatorLibrary.Model.Actor;

public abstract class Plane extends Actor {

    private boolean hasTakenOff = false;
    private boolean hasLanded   = false;
    private boolean hasCrashed  = false;
    
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
		return canFly();
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
		return (getFlyingTimeLeft() > 0 || !hasLanded || !hasTakenOff || !hasCrashed);
	}
	
	public void onTakenOff()
	{
		hasTakenOff = true;
	}
	public void onLanded()
	{
	    hasLanded = true;
	}
	public void onCrash()
	{
	    hasCrashed = true;
	}
	
}
