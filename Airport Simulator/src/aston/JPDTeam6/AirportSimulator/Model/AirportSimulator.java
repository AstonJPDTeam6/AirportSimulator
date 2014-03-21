package aston.JPDTeam6.AirportSimulator.Model;

import java.util.ArrayList;

import aston.JPDTeam6.SimulatorLibrary.Model.Actor;
import aston.JPDTeam6.SimulatorLibrary.Model.Simulator;
import aston.JPDTeam6.SimulatorLibrary.View.View;

public class AirportSimulator extends Simulator {

	private float commercialProbability;
	public Airport airport;
	
	public AirportSimulator(View view, float commercialProbability) {
		super(view);
		this.commercialProbability = commercialProbability;
	}

	protected void resetSimulation()
	{
		super.resetSimulation();
		airport = new Airport(this);
	}

	public boolean doTick()
	{
		super.doTick();
		
		return getTick() < 2880; //Maximum number of ticks to stop
	}
	
	private void spawnPlanes()
	{
		
	}
	
	public void deleteActor(Actor actor)
	{
		ArrayList<Actor> toDelete = new ArrayList<Actor>();
		for(Actor a : airport.takeOffQueue)
		{
			if(actor.equals(a))
			{
				toDelete.add(a);
			}
		}
		
		for(Actor a : toDelete) {
			airport.takeOffQueue.remove(a);
		}
		toDelete.clear();


		for(Actor a : airport.landingQueue)
		{
			if(actor.equals(a))
			{
				toDelete.add(a);
			}
		}
		
		for(Actor a : toDelete) {
			airport.landingQueue.remove(a);
		}
		
		super.deleteActor(actor);
	}
	
}
