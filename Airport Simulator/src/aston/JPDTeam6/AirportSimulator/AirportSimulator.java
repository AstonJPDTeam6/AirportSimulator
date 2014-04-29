package aston.JPDTeam6.AirportSimulator;

import java.util.ArrayList;
import java.util.Collections;

import aston.JPDTeam6.AirportSimulator.Model.Airport;
import aston.JPDTeam6.AirportSimulator.Model.Planes.*;
import aston.JPDTeam6.SimulatorLibrary.Configuration;
import aston.JPDTeam6.SimulatorLibrary.Counter;
import aston.JPDTeam6.SimulatorLibrary.Simulator;
import aston.JPDTeam6.SimulatorLibrary.Model.Actor;
import aston.JPDTeam6.SimulatorLibrary.View.View;

public class AirportSimulator extends Simulator
{

    private float  commercialProbability;
    private long   simulationLength;
    public Airport airport;

    public AirportSimulator(Configuration configuration, View[] views) throws Exception
    {
        super(configuration, views);
        this.commercialProbability = (float) configuration.getOption("commercial probability");
        this.simulationLength = ((Long) configuration.getOption("simulation length")).longValue();

        setSeed(((Long) configuration.getOption("random seed")).longValue());

        airport = new Airport(this);
        addActor(airport);
    }

    public boolean doTick()
    {
        spawnPlanes();
        updateCounts();

        return getTick() >= simulationLength; // Maximum number of ticks to stop
    }

    protected void updateCounts()
    {
        Counter c = getCounter();

        long numberOfPlanes = 0;
        long takingOff = 0;
        long landing = 0;

        for (Actor actor : actors)
        {
            if (actor instanceof Plane)
            {
                Plane plane = (Plane) actor;
                numberOfPlanes++;

                if (plane.getIntent() == Plane.PlaneIntent.TAKING_OFF)
                {
                    takingOff++;
                }
                else
                {
                    landing++;
                }
            }
        }

        c.put("number of planes", numberOfPlanes);
        c.put("planes taking off", takingOff);
        c.put("planes landing", landing);

        if (airport.currentAirportEvent != null)
        {
            c.incr("airport ticks spent " + airport.currentAirportEvent.getAirportState().name());
        }
    }

    private void spawnPlanes()
    {
        float probability = getRandom().nextFloat();
        if (probability < Glider.getSpawnProbability())
        {
            Glider g;
            if (getRandom().nextBoolean())
                g = new Glider(this, Plane.PlaneIntent.TAKING_OFF);
            else
                g = new Glider(this, Plane.PlaneIntent.LANDING);
            addActor(g);
        }

        probability = getRandom().nextFloat();
        if (probability < Light.getSpawnProbability())
        {
            Light l;
            if (getRandom().nextBoolean())
                l = new Light(this, Plane.PlaneIntent.TAKING_OFF);
            else
                l = new Light(this, Plane.PlaneIntent.LANDING);
            addActor(l);
        }

        probability = getRandom().nextFloat();
        if (probability < commercialProbability)
        {
            Commercial c;
            if (getRandom().nextBoolean())
                c = new Commercial(this, Plane.PlaneIntent.TAKING_OFF);
            else
                c = new Commercial(this, Plane.PlaneIntent.LANDING);
            addActor(c);
        }

    }

    // public void deleteActor(Actor actor)
    // {
    // airport.takeOffQueue.remove(actor);
    // airport.landingQueue.remove(actor);
    // super.deleteActor(actor);
    // }

}
