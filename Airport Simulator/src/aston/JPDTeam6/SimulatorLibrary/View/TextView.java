package aston.JPDTeam6.SimulatorLibrary.View;

import aston.JPDTeam6.SimulatorLibrary.Simulator;

public abstract class TextView implements View
{

    public TextView()
    {
    }

    public abstract void update(Simulator simulator);

    public abstract void end(Simulator simulator);

    public void print(String text)
    {
        System.out.print(text);
    }

    public void println(String text)
    {
        System.out.println(text);
    }
}
