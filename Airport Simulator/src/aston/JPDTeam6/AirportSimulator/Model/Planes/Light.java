package aston.JPDTeam6.AirportSimulator.Model.Planes;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;

public class Light extends Plane
{

    private static long lightID = 0;
    
    private static final long  TAKEOFF_TICKS     = 4;
    private static final long  LANDING_TICKS     = 6;
    private static final float SPAWN_PROBABILITY = 0.05f;

    public Glider              towedGlider       = null;

    public Light(AirportSimulator simulator, PlaneIntent intent)
    {
        super(simulator, "Light #" + lightID++, intent, LANDING_TICKS, TAKEOFF_TICKS, 0);
        this.maxFlyingTime = 20 + simulator.getRandom().nextInt(20);
    }

    public Light(AirportSimulator simulator, PlaneIntent intent, Glider glider)
    {
        this(simulator, intent);

        towedGlider = glider;
    }

    @Override
    public void onTakenOff()
    {
        if (towedGlider != null)
        {
            setIntent(PlaneIntent.LANDING);
//            ((AirportSimulator) getSimulator()).airport.queueLanding(this);
        }
        else
        {
            super.onTakenOff();
        }
    }

    public static float getSpawnProbability()
    {
        return SPAWN_PROBABILITY;
    }

}
