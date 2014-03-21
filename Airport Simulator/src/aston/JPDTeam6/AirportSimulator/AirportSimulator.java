package aston.JPDTeam6.AirportSimulator;

public class AirportSimulator extends Simulator {

	private float commercialProbability;
	private Airport airport;
	
	public AirportSimulator(View view, float commercialProbability) {
		super(view);
		this.commercialProbability = commercialProbability;
	}

	protected void resetSimulation()
	{
		super.resetSimulation();
		airport = new Airport(this);
	}

}
