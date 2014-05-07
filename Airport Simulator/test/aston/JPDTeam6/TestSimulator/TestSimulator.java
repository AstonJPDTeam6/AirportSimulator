package aston.JPDTeam6.TestSimulator;

import aston.JPDTeam6.SimulatorLibrary.Simulator;

public class TestSimulator extends Simulator
{

    public TestSimulator()
    {
        super(null, null);
    }

    @Override
    public boolean doTick()
    {
        return true; // Don't run
    }

    public void setTick(long tick)
    {
        this.currentTick = tick;
    }

}
