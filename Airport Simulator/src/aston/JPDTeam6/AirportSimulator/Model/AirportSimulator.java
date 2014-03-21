package aston.JPDTeam6.AirportSimulator.Model;

import aston.JPDTeam5.SimulatorLibrary.Model.Simulator;
import aston.JPDTeam5.SimulatorLibrary.View.View;

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
