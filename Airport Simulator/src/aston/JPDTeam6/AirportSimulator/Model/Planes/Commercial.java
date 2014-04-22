package aston.JPDTeam6.AirportSimulator.Model.Planes;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;

public class Commercial extends Plane
{

    private static long commercialID = 0;
    
    private static final long TAKEOFF_TICKS = 4;
    private static final long LANDING_TICKS = 6;

    public Commercial(AirportSimulator simulator, PlaneIntent intent)
    {
        super(simulator, "Commercial #" + commercialID++, intent, LANDING_TICKS, TAKEOFF_TICKS, 0);
        this.maxFlyingTime = 40 + simulator.getRandom().nextInt(40);
    }

}
