package aston.JPDTeam6.AirportSimulator.Model.AirTrafficControllers;

import java.util.List;
import java.util.Random;

import aston.JPDTeam6.AirportSimulator.Model.Airport;
import aston.JPDTeam6.AirportSimulator.Model.Planes.Plane;

public class RandomPick extends AirTrafficController
{
    Random rng;
    
    public RandomPick(Airport airport)
    {
        super(airport);
        rng = airport.getSimulator().getRandom();
    }

    @Override
    public boolean getTakeoffOrLanding()
    {
        return rng.nextBoolean();
    }

    private Plane pickRandom(List<Plane> planes)
    {
        return planes.get(rng.nextInt(planes.size()));
    }
    
    @Override
    public Plane getNextTakingOff()
    {
        List<Plane> planes = airport.getPlanesTakingOff();
        return pickRandom(planes);
    }

    @Override
    public Plane getNextLanding()
    {
        List<Plane> planes = airport.getPlanesLanding();
        return pickRandom(planes);
    }

}
