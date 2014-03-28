package aston.JPDTeam6.AirportSimulator.Model.Planes;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;

public class Glider extends Plane {
    
    private static final long TAKEOFF_TICKS = 6;
    private static final long LANDING_TICKS = 8;
    private static final long MAX_FLYING_TIME = Long.MAX_VALUE;
    private static final float SPAWN_PROBABILITY = 0.002f;
    

	public Glider(AirportSimulator simulator) {
		super(simulator, LANDING_TICKS, TAKEOFF_TICKS, MAX_FLYING_TIME);
		// TODO Auto-generated constructor stub
	}

	public static float getSpawnProbability()
	{
	    return SPAWN_PROBABILITY;
	}
	
}
