package aston.JPDTeam6.AirportSimulator.Model;

import aston.JPDTeam6.AirportSimulator.Model.Planes.Plane;

public class AirportEvent
{
    
    private AirportState airportState;
    private Plane currentPlane;

    public AirportEvent(AirportState state, Plane plane)
    {
        airportState = state;
        currentPlane = plane;
    }

    public AirportState getAirportState()
    {
        return airportState;
    }

    public Plane getCurrentPlane()
    {
        return currentPlane;
    }
    
}
