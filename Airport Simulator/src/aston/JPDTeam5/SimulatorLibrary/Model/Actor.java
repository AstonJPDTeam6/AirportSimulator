package aston.JPDTeam5.SimulatorLibrary.Model;

public abstract class Actor {

	private Simulator simulator;
	
	public Actor(Simulator simulator) {
		this.simulator = simulator;
		this.simulator.actors.add(this);
	}
	
	
	protected Simulator getSimulator()
	{
		return simulator;
	}
	
	public abstract boolean onTick();

}
