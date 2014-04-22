package aston.JPDTeam6.AirportSimulator.Model.Planes;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;
import aston.JPDTeam6.SimulatorLibrary.Event;
import aston.JPDTeam6.SimulatorLibrary.Model.Actor;

public abstract class Plane extends Actor
{

    public static enum PlaneIntent
    {
        TAKING_OFF, LANDING
    }

    protected boolean     hasTakenOff    = false;
    protected boolean     hasLanded      = false;
    protected boolean     hasCrashed     = false;
    protected boolean     isWaiting      = true;

    protected PlaneIntent currentIntent;

    protected String      planeName;
    
    protected long        spawnTick;

    protected long        timeToLand;
    protected long        timeToTakeOff;
    protected long        maxFlyingTime;

    public long           planeEventTick = 0;

    public Plane(AirportSimulator simulator, String planeName, PlaneIntent intent, long timeToLand, long timeToTakeOff, long maxFlyingTime)
    {
        super(simulator);
        spawnTick = simulator.getTick();

        this.timeToLand = timeToLand;
        this.timeToTakeOff = timeToTakeOff;
        this.maxFlyingTime = maxFlyingTime;
        this.planeName = planeName;
        this.currentIntent = intent;
        
        Event ev = new Event("Plane Created: " + planeName, this, "planecreate");
        getSimulator().getEventLog().addEvent(ev);
    }

    @Override
    public boolean onTick()
    {
        if (!canFly())
        {
            onCrash();
        }
        else if (!isWaiting())
        {
            if (hasTakenOff())
            {
                onTakenOff();
            }
            if (hasLanded())
            {
                onLanded();
            }
        }
        return isDone();
    }

    public long getTimeToLand()
    {
        return timeToLand;
    }

    public long getTimeToTakeOff()
    {
        return timeToTakeOff;
    }

    public long getMaxFlyingTime()
    {
        return maxFlyingTime;
    }

    public static float getSpawnProbability() throws Exception
    {
        throw new Exception("Must be implemented in child");
    }

    public void start()
    {
        planeEventTick = getSimulator().getTick();
        isWaiting = false;
    }

    public boolean hasTakenOff()
    {
        return (planeEventTick + timeToTakeOff) < getSimulator().getTick();
    }

    public boolean hasLanded()
    {
        return (planeEventTick + timeToLand) < getSimulator().getTick();
    }

    public long getFlyingTimeLeft()
    {
        return (spawnTick + maxFlyingTime) - getSimulator().getTick();
    }

    public boolean canFly()
    {
        return getFlyingTimeLeft() > 0;
    }

    public void onTakenOff()
    {
        hasTakenOff = true;
        
        Event ev = new Event("Plane Taken Off: " + planeName, this, "takeoff");
        getSimulator().getEventLog().addEvent(ev);
    }

    public void onLanded()
    {
        hasLanded = true;
        Event ev = new Event("Plane Landed: " + planeName, this, "landed");
        getSimulator().getEventLog().addEvent(ev);
    }

    public void onCrash()
    {
        hasCrashed = true;
        Event ev = new Event("Plane Crashed: " + planeName, this, "crashed");
        getSimulator().getEventLog().addEvent(ev);
    }

    public boolean isDone()
    {
        return hasTakenOff || hasLanded || hasCrashed;
    }

    public void setIntent(PlaneIntent intent)
    {
        currentIntent = intent;
    }

    public PlaneIntent getIntent()
    {
        return currentIntent;
    }

    public boolean isWaiting()
    {
        return isWaiting;
    }
}
