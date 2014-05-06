package aston.JPDTeam6.AirportSimulator.Model.Planes;

import java.util.Random;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;
import aston.JPDTeam6.SimulatorLibrary.Event;
import aston.JPDTeam6.SimulatorLibrary.Model.Actor;

public abstract class Plane extends Actor
{
    public static final long TIME_TO_REPAIR = 120;

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
    protected long        flyingTime;
    protected long        breakdownTime  = Long.MIN_VALUE;

    protected long        queuedTime;

    public long           planeEventTick = 0;

    public Plane(AirportSimulator simulator, String planeName, PlaneIntent intent, long timeToLand, long timeToTakeOff, long minFlyingTime, long maxFlyingTime)
    {
        super(simulator);
        spawnTick = simulator.getTick();

        this.timeToLand = timeToLand;
        this.timeToTakeOff = timeToTakeOff;
        this.planeName = planeName;
        this.currentIntent = intent;
        this.queuedTime = spawnTick;

        Random rng = simulator.getRandom();

        if (minFlyingTime != maxFlyingTime)
        {
            // this.flyingTime = minFlyingTime + (rng.nextLong() %
            // (maxFlyingTime - minFlyingTime)); //Uneven distribution
            this.flyingTime = minFlyingTime + (long) (rng.nextDouble() * (maxFlyingTime - minFlyingTime));
        }
        else
        {
            this.flyingTime = minFlyingTime;
        }

        Event ev = new Event("Plane Created: " + planeName, this, "planecreate");
        getSimulator().getEventLog().addEvent(ev);

        getSimulator().getCounter().incr("total planes created");
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
            if ((getIntent() == PlaneIntent.TAKING_OFF) && hasTakenOff())
            {
                onTakenOff();
            }

            if ((getIntent() == PlaneIntent.LANDING) && hasLanded())
            {
                onLanded();
            }
        }
        else
        {
            if (!hasBrokendown() && willBreakdown())
            {
                onBreakdown();
            }
            else if (hasRepaired())
            {
                onRepair();
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

    public long getFlyingTime()
    {
        return flyingTime;
    }

    public long getQueuedTime()
    {
        return queuedTime;
    }

    public static float getSpawnProbability() throws Exception
    {
        throw new Exception("Must be implemented in child");
    }

    public boolean canStart()
    {
        return canFly() && (!hasBrokendown()) && isWaiting();
    }

    public void start()
    {
        assert (hasBrokendown() == false);

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

    public boolean hasBrokendown()
    {
        if(breakdownTime == Long.MIN_VALUE)
        {
            return false;
        }
        else
        {
            return (getSimulator().getTick() - breakdownTime) <= TIME_TO_REPAIR;
        }
    }

    public boolean hasRepaired()
    {
        if(hasBrokendown())
        {
            return (getSimulator().getTick() - breakdownTime) == TIME_TO_REPAIR;
        }
        else
        {
            return false;
        }
    }

    public long getFlyingTimeLeft()
    {
        return (spawnTick + flyingTime) - getSimulator().getTick();
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

        getSimulator().getCounter().incr("total taken off planes");
    }

    public void onLanded()
    {
        hasLanded = true;

        Event ev = new Event("Plane Landed: " + planeName, this, "landed");
        getSimulator().getEventLog().addEvent(ev);

        getSimulator().getCounter().incr("total landed planes");
    }

    public void onCrash()
    {
        hasCrashed = true;

        Event ev = new Event("Plane Crashed: " + planeName, this, "crashed");
        getSimulator().getEventLog().addEvent(ev);

        getSimulator().getCounter().incr("total crashed planes");
    }

    public void onBreakdown()
    {
        breakdownTime = getSimulator().getTick();
        queuedTime = breakdownTime + TIME_TO_REPAIR;

        Event ev = new Event("Plane Breakdown: " + planeName, this, "breakdown");
        getSimulator().getEventLog().addEvent(ev);

        getSimulator().getCounter().incr("total breakdowns");
    }

    public void onRepair()
    {
        Event ev = new Event("Plane Repaired: " + planeName, this, "repair");
        getSimulator().getEventLog().addEvent(ev);

        getSimulator().getCounter().incr("total repairs");
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

    private boolean willBreakdown()
    {
        Random rng = getSimulator().getRandom();
        return rng.nextDouble() <= 0.0001;
    }

}
