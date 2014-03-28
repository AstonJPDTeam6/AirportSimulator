package aston.JPDTeam6.AirportSimulator.Model.AirTrafficControllers;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;
import aston.JPDTeam6.AirportSimulator.Model.Airport;
import aston.JPDTeam6.AirportSimulator.Model.AirportEvent;
import aston.JPDTeam6.AirportSimulator.Model.AirportState;
import aston.JPDTeam6.AirportSimulator.Model.Planes.Plane;

public abstract class AirTrafficController
{

    protected Airport airport;
    
    public AirTrafficController(Airport airport)
    {
        this.airport = airport;
    }

    public abstract Plane getNextTakingOff();
    public abstract Plane getNextLanding();
    
    public AirportEvent getNextEvent()
    {
        Plane currentPlane;
        AirportState airportState;
        
        //Randomly pick whether a plane should be scheduled to take off or land
        boolean nextTakeOff = airport.getSimulator().getRandom().nextBoolean();
        
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
    
}
