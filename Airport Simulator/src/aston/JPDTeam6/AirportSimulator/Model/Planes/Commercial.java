package aston.JPDTeam6.AirportSimulator.Model.Planes;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;

public class Commercial extends Plane
{

    private static long commercialID = 0;
    
    private static final long TAKEOFF_TICKS = 4;
    private static final long LANDING_TICKS = 6;
    private static final long MAX_FLYING_TIME = 80;
    private static final long MIN_FLYING_TIME = 40;

    public Commercial(AirportSimulator simulator, PlaneIntent intent)
    {
        super(simulator, "Commercial #" + commercialID++, intent, LANDING_TICKS, TAKEOFF_TICKS, MIN_FLYING_TIME, MAX_FLYING_TIME);
    }

}
