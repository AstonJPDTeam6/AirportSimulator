package aston.JPDTeam6.AirportSimulator.Model.Planes;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;

public class Commercial extends Plane
{

    private static final long TAKEOFF_TICKS = 4;
    private static final long LANDING_TICKS = 6;
    
    public Commercial(AirportSimulator simulator)
    {
        super(simulator, LANDING_TICKS, TAKEOFF_TICKS, 0);
        this.maxFlyingTime = 40 + simulator.getRandom().nextInt(40);
    }
    
}
