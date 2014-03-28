package aston.JPDTeam6.AirportSimulator.Model.AirTrafficControllers;

import aston.JPDTeam6.AirportSimulator.Model.Airport;
import aston.JPDTeam6.AirportSimulator.Model.Planes.Plane;

public class FIFO extends AirTrafficController
{

    public FIFO(Airport airport)
    {
        super(airport);
    }
    
    public Plane getNextTakingOff()
    {
        return airport.takeOffQueue.poll();
    }
    
    public Plane getNextLanding()
    {
        return airport.landingQueue.poll();
    }

}
