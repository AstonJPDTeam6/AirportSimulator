package aston.JPDTeam6.AirportSimulator;

public abstract class Actor {

	private Simulator simulator;
	
	public Actor(Simulator simulator) {
		this.simulator = simulator;
	}
	
	protected Simulator getSimulator()
	{
		return simulator;
	}
	
	public abstract boolean onTick();

}
