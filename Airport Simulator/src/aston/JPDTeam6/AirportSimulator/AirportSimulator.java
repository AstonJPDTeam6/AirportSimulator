package aston.JPDTeam6.AirportSimulator;

import java.util.ArrayList;
import java.util.Collections;

import aston.JPDTeam6.AirportSimulator.Model.Airport;
import aston.JPDTeam6.AirportSimulator.Model.Planes.*;
import aston.JPDTeam6.SimulatorLibrary.Configuration;
import aston.JPDTeam6.SimulatorLibrary.Simulator;
import aston.JPDTeam6.SimulatorLibrary.Model.Actor;
import aston.JPDTeam6.SimulatorLibrary.View.View;

public class AirportSimulator extends Simulator {

	private float commercialProbability;
	public Airport airport;
	
	public AirportSimulator(Configuration configuration, View[] views) throws Exception {
		super(configuration, views);
		this.commercialProbability = (float)configuration.getOption("commercial probability");
	}

	protected void resetSimulation()
	{
		super.resetSimulation();
		airport = new Airport(this);
	}

	public boolean doTick()
	{
	    spawnPlanes();
	    
		super.doTick();
		
		return getTick() < 2880; //Maximum number of ticks to stop
	}
	
	private void spawnPlanes()
	{
		float probability = getRandom().nextFloat();
		
		ArrayList<Plane> planesToAddTakeOff = new ArrayList<Plane>();
		ArrayList<Plane> planesToAddLanding = new ArrayList<Plane>();
		
		if(probability < Glider.getSpawnProbability())
		{
		    if(getRandom().nextBoolean())
		        planesToAddTakeOff.add(new Glider(this));
		    else
		        planesToAddLanding.add(new Glider(this));
		}
		
		if(probability < Light.getSpawnProbability())
		{
		    if(getRandom().nextBoolean())
		        planesToAddTakeOff.add(new Light(this));
		    else
		        planesToAddLanding.add(new Light(this));
		}
		
		if(probability < commercialProbability)
		{
		    if(getRandom().nextBoolean())
		        planesToAddTakeOff.add(new Commercial(this));
		    else
		        planesToAddLanding.add(new Commercial(this));
		}
		
		Collections.shuffle(planesToAddTakeOff);
		Collections.shuffle(planesToAddLanding);
		
		airport.takeOffQueue.addAll(planesToAddTakeOff);
		airport.landingQueue.addAll(planesToAddLanding);
	}
	
	public void deleteActor(Actor actor)
	{
		airport.takeOffQueue.remove(actor);
		airport.landingQueue.remove(actor);
		super.deleteActor(actor);
	}
	
}
