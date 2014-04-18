package aston.JPDTeam6.AirportSimulator;

import aston.JPDTeam6.SimulatorLibrary.Configuration;
import aston.JPDTeam6.SimulatorLibrary.View.View;
import aston.JPDTeam6.AirportSimulator.View.*;

public class CLIFrontend {

	public static void main(String[] args) {
		View[] view = new View[] {
//				new AirportSimulatorGraph(),
//				new AirportSimulatorTable(),
				new AirportSimulatorTextView()
		};
		
		Configuration configs = new Configuration();
		configs.setOption("simulation length", 1000l);
		configs.setOption("commercial probability", 0.6f);
		configs.setOption("random seed", 12345l);
		
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
