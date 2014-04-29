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

    public boolean getTakeoffOrLanding()
    {
        lastTakeoffOrLand = !lastTakeoffOrLand;
        return lastTakeoffOrLand;
    }

    private Plane getOldest(List<Plane> planes)
    {
        Plane oldestPlane = null;

        for (Plane plane : planes)
        {
            if ((oldestPlane == null || (plane.getSpawnTime() < oldestPlane.getSpawnTime())) && plane.canStart())
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
