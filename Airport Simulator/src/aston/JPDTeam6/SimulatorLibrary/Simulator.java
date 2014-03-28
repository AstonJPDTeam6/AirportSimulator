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
	
	protected long currentTick;
	
	public List<Actor> actors;
	
	public Simulator(Configuration configuration, View[] views) {
		this.views = views;
		this.configuration = configuration;
		
		rng = new Random(1000); //TODO: make the seed configurable
		
		resetSimulation();
	}

	public long getTick()
	{
		return currentTick;
	}
	
	protected void resetSimulation()
	{
		currentTick = 0;
		actors = new ArrayList<Actor>();
	}
	
	/**
	 * Automatically runs the simulation
	 */
	public void runSimulation()
	{
		resetSimulation();
		
		do
		{
			updateViews();
		}
		while(doTick());
		
		updateViews();
		
	}
	
	private void updateViews()
	{
	    for(View view : views)
	    {
	        view.update(this);
	    }
	}
	
	public boolean doTick()
	{
	    ArrayList<Actor> actorsToDelete = new ArrayList<Actor>();
	    
		for(Actor actor : actors)
		{
			// If any onTicks return false, end the simulation
			if(!actor.onTick())
			{
				actors.add(actor);
			}
		}
		
		for(Actor actor : actorsToDelete)
		{
		    actor.delete();
		}
		
		currentTick++;
		return true;
	}
	
	public Random getRandom()
	{
		return rng;
	}
	
	public Configuration getConfiguration()
	{
	    return configuration;
	}
	
	public void addActor(Actor actor)
	{
		actors.add(actor);
	}
	public void deleteActor(Actor actor)
	{
		actors.remove(actor);
	}
	
}
