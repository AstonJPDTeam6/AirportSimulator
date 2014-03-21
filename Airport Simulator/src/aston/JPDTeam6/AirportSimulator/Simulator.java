package aston.JPDTeam6.AirportSimulator;

import java.util.ArrayList;
import java.util.List;

public abstract class Simulator {

	private View view;
	//private boolean runSimulation = false;
	
	protected long currentTick;
	
	public List<Actor> actors;
	
	public Simulator(View view) {
		this.view = view;
		
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
		while(onTick());
		
		view.update(this);
		
	}
	
	public boolean onTick()
	{
		currentTick++;
		
		for(Actor actor : actors)
		{
			// If any onTicks return false, end the simulation
			if(!actor.onTick())
			{
				return false;
			}
		}
		
		return true;
	}
	
}
