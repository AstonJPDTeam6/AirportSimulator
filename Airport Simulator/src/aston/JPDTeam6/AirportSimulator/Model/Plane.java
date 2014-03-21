package aston.JPDTeam6.AirportSimulator.Model;

import aston.JPDTeam5.SimulatorLibrary.Model.Actor;
import aston.JPDTeam5.SimulatorLibrary.Model.Simulator;

public abstract class Plane extends Actor {

	private long spawnTick;
	
	public long planeEventTick = 0;
	
	public Plane(Simulator simulator) {
		super(simulator);
		spawnTick = simulator.getTick();
	}

	@Override
	public boolean onTick()
	{
		return true;
	}
	
	public abstract long getTimeToLand();
	public abstract long getTimeToTakeOff();
	public abstract long getMaxFlyingTime();
	public abstract void onTakenOff();
	public abstract void onLanded();
	public abstract void onCrash();
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
		return planeEventTick + getTimeToTakeOff() < getSimulator().getTick();
	}
	
	public void startLanding()
	{
		planeEventTick = getSimulator().getTick();
	}
	public boolean hasLanded()
	{
		return planeEventTick + getTimeToLand() < getSimulator().getTick();
	}
	
	public long getFlyingTimeLeft()
	{
		return (spawnTick + getMaxFlyingTime()) - getSimulator().getTick();
	}
	
	public boolean canFly()
	{
		return getFlyingTimeLeft() > 0;
	}
	
}
