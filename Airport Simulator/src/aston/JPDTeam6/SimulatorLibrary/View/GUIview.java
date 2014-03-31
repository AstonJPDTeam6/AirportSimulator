package aston.JPDTeam6.SimulatorLibrary.View;

import aston.JPDTeam6.SimulatorLibrary.Simulator;
import java.util.ArrayList;

public class GUIview implements View {
	ArrayList<Simulator> sims;
	boolean table;
	boolean graph;
	AirportSimulatorGUIView hi;
	
	public GUIview (Simulator simulator){
		table=false;
		graph=false;
		update(simulator);
		
		//begin to run the GUI
	}
	
	public void update(Simulator simulator){
		sims.add(simulator);
	}
	
	
	//methods to run the GUI here;
}