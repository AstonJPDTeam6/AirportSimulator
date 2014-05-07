package aston.JPDTeam6.AirportSimulator.Model.Planes;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;

public class TestPlane extends Plane
{

    public TestPlane(AirportSimulator simulator, String planeName, PlaneIntent intent, long timeToLand, long timeToTakeOff, long minFlyingTime, long maxFlyingTime)
    {
        super(simulator, planeName, intent, timeToLand, timeToTakeOff, minFlyingTime, maxFlyingTime);
    }

    public void setHasTakenOff(boolean b)
    {
        this.hasTakenOff = b;
    }

    public void setHasLanded(boolean b)
    {
        this.hasLanded = b;
    }

    public void setBreakdownTime(long t)
    {
        this.breakdownTime = t;
    }

    public void setIsWaiting(boolean b)
    {
        this.isWaiting = b;
    }
}
