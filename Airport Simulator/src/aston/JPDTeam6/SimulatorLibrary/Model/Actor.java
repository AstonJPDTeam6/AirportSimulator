package aston.JPDTeam6.SimulatorLibrary.Model;

import aston.JPDTeam6.SimulatorLibrary.Simulator;

public abstract class Actor {

	private Simulator simulator;
	
	public Actor(Simulator simulator) {
		this.simulator = simulator;
		this.simulator.addActor(this);
	}
	
	public void delete()
	{
		this.simulator.deleteActor(this);
	}
	
	public Simulator getSimulator()
	{
		return simulator;
	}
	
	public abstract boolean onTick();

}
