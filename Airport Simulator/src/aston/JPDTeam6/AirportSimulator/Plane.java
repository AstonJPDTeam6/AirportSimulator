package aston.JPDTeam6.AirportSimulator;

public abstract class Plane extends Actor {

	private long spawnTick;
	
	public Plane(Simulator simulator) {
		super(simulator);
		spawnTick = simulator.getTick();
	}

	@Override
	public boolean onTick() {
		return false;
	}
	
	public abstract long getTimeToLand();
	public abstract long getTimeToTakeOff();
	public abstract long getFlyingTimeLeft();
	public abstract void onTakenOff();
	public abstract void onLanded();

}
