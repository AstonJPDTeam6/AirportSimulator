package aston.JPDTeam6.AirportSimulator.Model.AirTrafficControllers;

import java.util.List;

import aston.JPDTeam6.AirportSimulator.Model.Airport;
import aston.JPDTeam6.AirportSimulator.Model.Planes.Plane;

public class FIFO extends AirTrafficController
{

    private boolean lastTakeoffOrLand = false;

    public FIFO(Airport airport)
    {
        super(airport);
    }

    public FIFO()
    {
        super();
    }

    /**
     * @return true means taking off, false means landing
     */
    public boolean getTakeoffOrLanding()
    {
        boolean planesTakingOff = airport.getPlanesTakingOff().size() > 0;
        boolean planesLanding   = airport.getPlanesLanding().size() > 0;
        
        if(planesTakingOff && planesLanding) {
            lastTakeoffOrLand = !lastTakeoffOrLand;
            return lastTakeoffOrLand;
        }
        else
        {
            if(planesTakingOff)
                return true;
            else
                return false;
        }
    }

    private Plane getOldest(List<Plane> planes)
    {
        Plane oldestPlane = null;

        for (Plane plane : planes)
        {
            if ((oldestPlane == null || (plane.getQueuedTime() < oldestPlane.getQueuedTime())) && plane.canStart())
            {
                oldestPlane = plane;
            }
        }

        return oldestPlane;
    }
    
    public Plane getNextTakingOff()
    {
        List<Plane> planes = airport.getPlanesTakingOff();
        return getOldest(planes);
    }

    public Plane getNextLanding()
    {
        List<Plane> planes = airport.getPlanesLanding();
        return getOldest(planes);
    }

}
