package aston.JPDTeam6.AirportSimulator;

import aston.JPDTeam6.AirportSimulator.Model.AirportSimulator;
import aston.JPDTeam6.SimulatorLibrary.View.View;

public class CLIFrontend {

	public static void main(String[] args) {
		View view = null; //make this variable using whatever
		AirportSimulator airportSimulator = new AirportSimulator(view, 0.5f);
		
		airportSimulator.runSimulation();
	}

}
