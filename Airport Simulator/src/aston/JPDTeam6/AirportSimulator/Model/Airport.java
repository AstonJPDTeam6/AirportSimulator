package aston.JPDTeam6.AirportSimulator.Model;

import java.util.ArrayList;
import java.util.List;
import aston.JPDTeam6.AirportSimulator.Model.AirTrafficControllers.AirTrafficController;
import aston.JPDTeam6.AirportSimulator.Model.Planes.Plane;
import aston.JPDTeam6.SimulatorLibrary.Simulator;
import aston.JPDTeam6.SimulatorLibrary.Model.Actor;

public class Airport extends Actor
{
    private AirTrafficController airTrafficController;
    public AirportEvent          currentAirportEvent;

    public Airport(Simulator simulator)
    {
        this(simulator, new aston.JPDTeam6.AirportSimulator.Model.AirTrafficControllers.FIFO());
    }

    public Airport(Simulator simulator, AirTrafficController atc)
    {
        super(simulator);
        airTrafficController = atc;
        airTrafficController.setAirport(this);
    }

    @Override
    public boolean onTick()
    {
        if (currentAirportEvent == null || (currentAirportEvent.getCurrentPlane() != null && currentAirportEvent.getCurrentPlane().isDone()) || currentAirportEvent.getAirportState() == AirportState.WAITING)
        {
            scheduleNextEvent();

            if (currentAirportEvent.getAirportState() != AirportState.WAITING)
            {
                currentAirportEvent.getCurrentPlane().start();
            }
        }

        return false;
    }

    private void scheduleNextEvent()
    {
        currentAirportEvent = airTrafficController.getNextEvent();
    }

    public List<Plane> getPlanesTakingOff()
    {
        ArrayList<Plane> planes = new ArrayList<Plane>();

        for (Actor actor : getSimulator().actors)
        {
            if (actor instanceof Plane)
            {
                Plane plane = (Plane) actor;
                if ((plane.getIntent() == Plane.PlaneIntent.TAKING_OFF) && (plane.isWaiting()))
                {
                    planes.add(plane);
                }
            }
        }

        return planes;
    }

    public List<Plane> getPlanesLanding()
    {
        ArrayList<Plane> planes = new ArrayList<Plane>();

        for (Actor actor : getSimulator().actors)
        {
            if (actor instanceof Plane)
            {
                Plane plane = (Plane) actor;
                if ((plane.getIntent() == Plane.PlaneIntent.LANDING) && (plane.isWaiting()))
                {
                    planes.add(plane);
                }
            }
        }

        return planes;
    }
}
