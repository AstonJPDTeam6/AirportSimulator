package aston.JPDTeam6.AirportSimulator.Model.Planes;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;

public class Light extends Plane
{

    private static long        lightID           = 0;

    private static final long  TAKEOFF_TICKS     = 4;
    private static final long  LANDING_TICKS     = 6;
    private static final long  MIN_FLYING_TIME   = 20;
    private static final long  MAX_FLYING_TIME   = 40;
    private static final float SPAWN_PROBABILITY = 0.005f;

    public Glider              towedGlider       = null;

    public Light(AirportSimulator simulator, PlaneIntent intent)
    {
        super(simulator, "Light #" + lightID++, intent, LANDING_TICKS, TAKEOFF_TICKS, MIN_FLYING_TIME, MAX_FLYING_TIME);
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
            getSimulator().getCounter().incr("light landing after glider takeoff");

            setIntent(PlaneIntent.LANDING);
            towedGlider = null;
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
