package aston.JPDTeam6.SimulatorLibrary.Model;

import aston.JPDTeam6.SimulatorLibrary.Simulator;

public abstract class Actor {

	private Simulator simulator;
	private boolean shouldRun = true;
	
	public Actor(Simulator simulator) {
		this.simulator = simulator;
		this.simulator.addActor(this);
	}
	
	public void delete()
	{
		shouldRun = false;
	}
	
	public Simulator getSimulator()
	{
		return simulator;
	}
	
	public boolean onTick()
	{
	    return shouldRun;
	}

}
