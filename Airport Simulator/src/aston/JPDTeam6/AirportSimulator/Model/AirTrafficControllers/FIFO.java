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
    
    public Plane getNextTakingOff()
    {
        List<Plane> planes = airport.getPlanesTakingOff();
        
        if(planes.size() == 0) return null;
        
        Plane oldestPlane = planes.get(0);

        for (Plane plane : planes)
        {
            if (plane.getSpawnTime() > oldestPlane.getSpawnTime())
            {
                oldestPlane = plane;
            }
        }

        return oldestPlane;
    }

    public Plane getNextLanding()
    {
        List<Plane> planes = airport.getPlanesLanding();
        
        if(planes.size() == 0) return null;
        
        Plane oldestPlane = planes.get(0);

        for (Plane plane : planes)
        {
            if (plane.getSpawnTime() > oldestPlane.getSpawnTime())
            {
                oldestPlane = plane;
            }
        }

        return oldestPlane;
    }

}
