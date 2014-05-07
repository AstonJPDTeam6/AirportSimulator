package aston.JPDTeam6.AirportSimulator.Model.AirTrafficControllers;

import java.util.List;
import java.util.Random;

import aston.JPDTeam6.AirportSimulator.Model.Airport;
import aston.JPDTeam6.AirportSimulator.Model.Planes.Plane;

public class RandomPick extends AirTrafficController
{
    private Random rng;

    public RandomPick()
    {
    }

    public RandomPick(Airport airport)
    {
        super(airport);
        rng = airport.getSimulator().getRandom();
    }

    private Random getRNG()
    {
        if (rng != null)
        {
            return rng;
        }
        else
        {
            rng = airport.getSimulator().getRandom();
            return rng;
        }
    }

    @Override
    public boolean getTakeoffOrLanding()
    {
        return getRNG().nextBoolean();
    }

    private Plane pickRandom(List<Plane> planes)
    {
        if (planes.size() == 0)
        {
            return null;
        }

        int randK = getRNG().nextInt(planes.size());
        return planes.get(randK);
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
