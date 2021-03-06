package aston.JPDTeam6.AirportSimulator.Model.AirTrafficControllers;

import java.util.List;

import aston.JPDTeam6.AirportSimulator.Model.Airport;
import aston.JPDTeam6.AirportSimulator.Model.Planes.Plane;

public class ShortestTimeFirst extends AirTrafficController
{
    private boolean lastTakeoffOrLand = false;

    public ShortestTimeFirst()
    {
    }

    public ShortestTimeFirst(Airport airport)
    {
        super(airport);
    }

    @Override
    public boolean getTakeoffOrLanding()
    {
        boolean planesTakingOff = airport.getPlanesTakingOff().size() > 0;
        boolean planesLanding = airport.getPlanesLanding().size() > 0;

        if (planesTakingOff && planesLanding)
        {
            lastTakeoffOrLand = !lastTakeoffOrLand;
            return lastTakeoffOrLand;
        }
        else
        {
            if (planesTakingOff)
                return true;
            else
                return false;
        }
    }

    private Plane getLeastFuelled(List<Plane> planes)
    {
        Plane leastFuelledPlane = null;

        for (Plane plane : planes)
        {
            if ((leastFuelledPlane == null || (plane.getFlyingTimeLeft() < leastFuelledPlane.getFlyingTimeLeft())) && plane.canStart() && plane.isWaiting())
            {
                leastFuelledPlane = plane;
            }
        }

        return leastFuelledPlane;
    }

    @Override
    public Plane getNextTakingOff()
    {
        List<Plane> planes = airport.getPlanesTakingOff();
        return getLeastFuelled(planes);
    }

    @Override
    public Plane getNextLanding()
    {
        List<Plane> planes = airport.getPlanesLanding();
        return getLeastFuelled(planes);
    }

}
