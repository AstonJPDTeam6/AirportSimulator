package aston.JPDTeam6.SimulatorLibrary.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aston.JPDTeam6.SimulatorLibrary.View.View;

public abstract class Simulator {

	private View view;
	private Random rng;
	
	protected long currentTick;
	
	public List<Actor> actors;
	
	public Simulator(View view) {
		this.view = view;
		
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
			view.update(this);
		}
		while(doTick());
		
		view.update(this);
		
	}
	
	public boolean doTick()
	{		
		for(Actor actor : actors)
		{
			// If any onTicks return false, end the simulation
			if(!actor.onTick())
			{
				return false;
			}
		}
		
		currentTick++;
		return true;
	}
	
	public Random getRandom()
	{
		return rng;
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
