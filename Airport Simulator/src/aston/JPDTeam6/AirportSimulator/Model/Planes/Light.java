package aston.JPDTeam6.AirportSimulator.Model.Planes;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;

public class Light extends Plane {

    private static final long TAKEOFF_TICKS = 4;
    private static final long LANDING_TICKS = 6;
    private static final float SPAWN_PROBABILITY = 0.005f;
    
	public Glider towedGlider = null;
	
	public Light(AirportSimulator simulator)
	{
		super(simulator, LANDING_TICKS, TAKEOFF_TICKS, 0);
		this.maxFlyingTime = 20 + simulator.getRandom().nextInt(20);
	}

	public Light(AirportSimulator simulator, Glider glider)
	{
		this(simulator);
		
		towedGlider = glider;
	}
	
	@Override
	public void onTakenOff() {
		if(towedGlider != null)
		{
			((AirportSimulator)getSimulator()).airport.queueLanding(this);
		}
		else
		{
			super.onTakenOff();
		}
	}
	
	public static float getSpawnProbability()
	{
		return SPAWN_PROBABILITY;
	}

}
