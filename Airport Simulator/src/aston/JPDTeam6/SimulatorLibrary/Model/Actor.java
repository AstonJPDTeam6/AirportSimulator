package aston.JPDTeam6.SimulatorLibrary.Model;

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
	
	protected Simulator getSimulator()
	{
		return simulator;
	}
	
	public abstract boolean onTick();

}
