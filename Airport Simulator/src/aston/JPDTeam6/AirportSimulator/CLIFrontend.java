package aston.JPDTeam6.AirportSimulator;

import aston.JPDTeam6.SimulatorLibrary.Configuration;
import aston.JPDTeam6.SimulatorLibrary.View.View;

public class CLIFrontend {

	public static void main(String[] args) {
		View[] view = new View[1]; //make this variable using whatever
		view[0] = new aston.JPDTeam6.AirportSimulator.View.AirportSimulatorGraph();
		Configuration configs = new Configuration();
		configs.setOption("commercial probability", 0.6f);
		
		AirportSimulator airportSimulator;
        try
        {
            airportSimulator = new AirportSimulator(configs, view);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
		
		airportSimulator.runSimulation();
	}

}
