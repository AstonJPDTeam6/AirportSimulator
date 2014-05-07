package aston.JPDTeam6.SimulatorLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aston.JPDTeam6.SimulatorLibrary.Model.Actor;
import aston.JPDTeam6.SimulatorLibrary.View.View;

/**
 * The base simulator class. Any simulators must extend this
 */
public abstract class Simulator
{

    private View[]           views;
    private Random           rng;
    private Configuration    configuration;
    private Counter          counter;
    private EventLog         eventLog;

    protected long           currentTick;

    public List<Actor>       actors;
    private ArrayList<Actor> actorsToAdd;
    private ArrayList<Actor> actorsToDelete;

    /**
     * @param configuration
     * @param views
     *            An array of View objects that you want the simulator to output
     *            to
     */
    public Simulator(Configuration configuration, View[] views)
    {
        this.views = views;
        this.configuration = configuration;

        counter = new Counter();
        eventLog = new EventLog(this);

        rng = new Random();
        actors = new ArrayList<Actor>();
        actorsToAdd = new ArrayList<Actor>();
        actorsToDelete = new ArrayList<Actor>();
        currentTick = 0;
    }

    public long getTick()
    {
        return currentTick;
    }

    /**
     * Call this to start the simulation
     */
    public void runSimulation()
    {
        do
        {
            boolean r = doTick();
            postTick();

            if (r)
                break;

            updateViews();
            currentTick++;
        }
        while (true);

        endViews();
    }

    private void updateViews()
    {
        for (View view : views)
        {
            view.update(this);
        }
    }

    private void endViews()
    {
        for (View view : views)
        {
            view.end(this);
        }
    }

    private void postTick()
    {
        addWaitingActors();
        deleteWaitingActors();

        for (Actor actor : actors)
        {
            // If any onTicks return false, end the simulation
            if (actor.onTick())
            {
                deleteActor(actor);
            }
        }

        deleteWaitingActors();

        // currentTick++;
    }

    /**
     * This method is called each tick, any simulation behaviour should be
     * implemented here
     * 
     * @return false = continue, true = stop
     */
    public abstract boolean doTick();

    /**
     * Use this for any instances of Random required. It is seeded by
     * configuration.
     * 
     * @return simulator seeded random
     */
    public Random getRandom()
    {
        return rng;
    }

    public Configuration getConfiguration()
    {
        return configuration;
    }

    public Counter getCounter()
    {
        return counter;
    }

    public EventLog getEventLog()
    {
        return eventLog;
    }

    public void addActor(Actor actor)
    {
        actorsToAdd.add(actor);
    }

    public void deleteActor(Actor actor)
    {
        actorsToDelete.add(actor);
    }

    private void addWaitingActors()
    {
        actors.addAll(actorsToAdd);
        actorsToAdd.clear();
    }

    private void deleteWaitingActors()
    {
        actors.removeAll(actorsToDelete);
        actorsToDelete.clear();
    }

    public void setSeed(long seed)
    {
        rng.setSeed(seed);
    }
}
