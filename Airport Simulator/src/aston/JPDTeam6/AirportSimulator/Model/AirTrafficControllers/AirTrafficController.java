package aston.JPDTeam6.AirportSimulator.Model.AirTrafficControllers;

import aston.JPDTeam6.AirportSimulator.Model.Airport;
import aston.JPDTeam6.AirportSimulator.Model.AirportEvent;
import aston.JPDTeam6.AirportSimulator.Model.AirportState;
import aston.JPDTeam6.AirportSimulator.Model.Planes.Plane;

public abstract class AirTrafficController
{

    protected Airport airport;
    
    public AirTrafficController() {}
    
    public AirTrafficController(Airport airport)
    {
        this.airport = airport;
    }

    public abstract boolean getTakeoffOrLanding();
    public abstract Plane getNextTakingOff();
    public abstract Plane getNextLanding();
    
    public AirportEvent getNextEvent()
    {
        Plane currentPlane;
        AirportState airportState;
        
        boolean nextTakeOff = getTakeoffOrLanding();
        
        if(nextTakeOff)
        {
            currentPlane = getNextTakingOff();
            airportState = AirportState.TAKEOFF;
        }
        else
        {
            currentPlane = getNextLanding();
            airportState = AirportState.LANDING;
        }
        
        if(currentPlane == null)
        {
            airportState = AirportState.WAITING;
        }
        
        AirportEvent ae = new AirportEvent(airportState, currentPlane);
        
        return ae;
    }
    
    public void setAirport(Airport airport)
    {
        this.airport = airport;
    }
}
