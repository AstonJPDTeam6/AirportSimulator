package aston.JPDTeam6.SimulatorLibrary.View;

import aston.JPDTeam6.AirportSimulator.View.AirportSimulatorGraph;
import aston.JPDTeam6.SimulatorLibrary.Simulator;

import java.util.ArrayList;

public abstract class GUIview implements View {
	
	public GUIview (){
		//begin to run the GUI
	}
	
	public abstract void update(Simulator simulator);
	
	
	//methods to run the GUI here;
}