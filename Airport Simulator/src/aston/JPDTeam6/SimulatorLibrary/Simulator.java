package aston.JPDTeam6.SimulatorLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aston.JPDTeam6.SimulatorLibrary.Model.Actor;
import aston.JPDTeam6.SimulatorLibrary.View.View;

public abstract class Simulator {

	private View[] views;
	private Random rng;
	private Configuration configuration;
	private Counter counter;
	private EventLog eventLog;

	private long currentTick;

	public List<Actor> actors;
	private ArrayList<Actor> actorsToAdd;
	private ArrayList<Actor> actorsToDelete;

	public Simulator(Configuration configuration, View[] views) {
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
	 * Automatically runs the simulation
	 */
	public void runSimulation()
	{
	    do
	    {
            boolean r = doTick();
            postTick();
            
            if(r) break;
            
            updateViews();
            currentTick++;
        }
		while(true);
        
		endViews();
	}

	private void updateViews()
	{
	    for(View view : views)
	    {
	        view.update(this);
	    }
	}

	private void endViews()
	{
	    for(View view : views)
	    {
	        view.end(this);
	    }
	}
	
	private void postTick()
	{
	    addWaitingActors();
	    deleteWaitingActors();
	    
        for(Actor actor : actors)
        {
            // If any onTicks return false, end the simulation
            if(actor.onTick())
            {
                deleteActor(actor); 
            }
        }

        deleteWaitingActors();

//        currentTick++;
	}
	
	public abstract boolean doTick();

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
	
	protected void setSeed(long seed)
	{
	    rng.setSeed(seed);
	}
}
