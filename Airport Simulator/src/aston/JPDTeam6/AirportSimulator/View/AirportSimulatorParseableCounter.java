package aston.JPDTeam6.AirportSimulator.View;

import java.util.Map.Entry;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;
import aston.JPDTeam6.SimulatorLibrary.Counter;
import aston.JPDTeam6.SimulatorLibrary.Simulator;
import aston.JPDTeam6.SimulatorLibrary.View.TextView;

public class AirportSimulatorParseableCounter extends TextView
{

    public AirportSimulatorParseableCounter() {}

    @Override
    public void update(Simulator simulator)
    {
        AirportSimulator as = (AirportSimulator) simulator;
        Counter counter = as.getCounter();
        
        println("Tick #" + as.getTick());
        
        printCounts(counter);
    }
    
    private void printCounts(Counter counter)
    {
        for(Entry<String, Long> count : counter.entrySet())
        {
            System.out.println("    " + count.getKey() + ": " + count.getValue());
        }
        
        System.out.println();
    }
    
    @Override
    public void end(Simulator simulator)
    {
        update(simulator);
    }

}
