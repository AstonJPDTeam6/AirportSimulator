package aston.JPDTeam6.AirportSimulator.Model.Planes;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;

public class Glider extends Plane
{

    private static long gliderID = 0;
    
    private static final long  TAKEOFF_TICKS     = 6;
    private static final long  LANDING_TICKS     = 8;
    private static final long  MIN_FLYING_TIME   = Long.MAX_VALUE;
    private static final long  MAX_FLYING_TIME   = Long.MAX_VALUE;
    private static final float SPAWN_PROBABILITY = 0.002f;

    private Light towPlane = null;
    
    public Glider(AirportSimulator simulator, PlaneIntent intent)
    {
        super(simulator, "Glider #" + gliderID++, intent, LANDING_TICKS, TAKEOFF_TICKS, MIN_FLYING_TIME, MAX_FLYING_TIME);
        
        if(intent == PlaneIntent.TAKING_OFF)
        {
            towPlane = new Light(simulator, PlaneIntent.TAKING_OFF, this);
            simulator.addActor(towPlane);
        }
    }

    public static float getSpawnProbability()
    {
        return SPAWN_PROBABILITY;
    }

    public boolean hasTakenOff()
    {
        if(towPlane != null)
            return towPlane.hasTakenOff();
        else
            return super.hasTakenOff();
    }
    
    public void onTakenOff()
    {
        getSimulator().getCounter().incr("gliders taken off");
        
        super.onTakenOff();
    }
    
}
