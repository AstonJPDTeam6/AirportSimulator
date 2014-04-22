package aston.JPDTeam6.SimulatorLibrary.Model;

import aston.JPDTeam6.SimulatorLibrary.Simulator;

public abstract class Actor
{

    private Simulator simulator;
    private boolean   shouldRun = true;
    private long      spawnTime;

    public Actor(Simulator simulator)
    {
        this.simulator = simulator;
        // this.simulator.addActor(this);
        spawnTime = simulator.getTick();
    }

    public void delete()
    {
        shouldRun = false;
    }

    public Simulator getSimulator()
    {
        return simulator;
    }

    public boolean onTick()
    {
        return shouldRun;
    }

    public long getSpawnTime()
    {
        return spawnTime;
    }

}
