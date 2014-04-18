package aston.JPDTeam6.AirportSimulator;

import java.util.ArrayList;
import java.util.Collections;

import aston.JPDTeam6.AirportSimulator.Model.Airport;
import aston.JPDTeam6.AirportSimulator.Model.Planes.*;
import aston.JPDTeam6.SimulatorLibrary.Configuration;
import aston.JPDTeam6.SimulatorLibrary.Counter;
import aston.JPDTeam6.SimulatorLibrary.Simulator;
import aston.JPDTeam6.SimulatorLibrary.Model.Actor;
import aston.JPDTeam6.SimulatorLibrary.View.View;

public class AirportSimulator extends Simulator {

	private float commercialProbability;
	private long  simulationLength;
	public Airport airport;
	
	public AirportSimulator(Configuration configuration, View[] views) throws Exception {
		super(configuration, views);
		this.commercialProbability = (float)configuration.getOption("commercial probability");
		this.simulationLength      = ((Long)configuration.getOption("simulation length")).longValue();
		
		setSeed(((Long)configuration.getOption("random seed")).longValue());
		
        airport = new Airport(this);
	}

	public boolean doTick()
	{
	    super.doTick();
        updateCounts();
	    spawnPlanes();
	    
		return getTick() < simulationLength; //Maximum number of ticks to stop
	}
	protected void updateCounts()
	{
	    Counter c = getCounter();
	    c.put("number of planes", (long) (airport.takeOffQueue.size() + airport.landingQueue.size()));
	    c.put("planes taking off", (long) airport.takeOffQueue.size());
	    c.put("planes landing", (long) airport.landingQueue.size());
	}
	
	private void spawnPlanes()
	{
		float probability = getRandom().nextFloat();
		
		ArrayList<Plane> planesToAddTakeOff = new ArrayList<Plane>();
		ArrayList<Plane> planesToAddLanding = new ArrayList<Plane>();
		
		probability = 0f;
		
		if(probability < Glider.getSpawnProbability())
		{
		    Glider g = new Glider(this);
		    addActor(g);
		    if(getRandom().nextBoolean())
		        planesToAddTakeOff.add(g);
		    else
		        planesToAddLanding.add(g);
		}
		
		if(probability < Light.getSpawnProbability())
		{
		    Light l = new Light(this);
		    addActor(l);
		    if(getRandom().nextBoolean())
		        planesToAddTakeOff.add(l);
		    else
		        planesToAddLanding.add(l);
		}
		
		if(probability < commercialProbability)
		{
		    Commercial c = new Commercial(this);
		    addActor(c);
		    if(getRandom().nextBoolean())
		        planesToAddTakeOff.add(c);
		    else
		        planesToAddLanding.add(c);
		}
		
		Collections.shuffle(planesToAddTakeOff, getRandom());
		Collections.shuffle(planesToAddLanding, getRandom());
		
		for(Plane p : planesToAddTakeOff)
		{
		    airport.queueTakeOff(p);
		}
		for(Plane p : planesToAddLanding)
		{
		    airport.queueLanding(p);
		}
	}
	
	public void deleteActor(Actor actor)
	{
		airport.takeOffQueue.remove(actor);
		airport.landingQueue.remove(actor);
		super.deleteActor(actor);
	}
	
}
