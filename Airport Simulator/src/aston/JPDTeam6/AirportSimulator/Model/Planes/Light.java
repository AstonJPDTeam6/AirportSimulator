package aston.JPDTeam6.AirportSimulator.Model.Planes;

import aston.JPDTeam6.AirportSimulator.Model.AirportSimulator;

public class Light extends Plane {

	public Light(AirportSimulator simulator)
	{
		super(simulator, 1, 1, 1); //TODO Put proper variables here
	}

	public Light(AirportSimulator simulator, Plane plane)
	{
		this(simulator);
	}
	
	@Override
	public void onTakenOff() {
		((AirportSimulator)getSimulator()).airport.queueLanding(this);
	}

	@Override
	public void onLanded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCrash() {
		// TODO Auto-generated method stub

	}
	
	public static float getSpawnProbability()
	{
		return 1f;
	}

}
