package aston.JPDTeam6.SimulatorLibrary.View;

import aston.JPDTeam6.SimulatorLibrary.Simulator;

public abstract class GUIview implements View {
	
	public GUIview() {}
	
	public abstract void update(Simulator simulator);
	
	public abstract void end(Simulator simulator);

}