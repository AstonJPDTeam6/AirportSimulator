package aston.JPDTeam6.AirportSimulator.View;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;
import aston.JPDTeam6.SimulatorLibrary.Counter;
import aston.JPDTeam6.SimulatorLibrary.Simulator;
import aston.JPDTeam6.SimulatorLibrary.View.TextView;

public class AirportSimulatorTextView extends TextView
{

    private final int MIN_COL_WIDTH = 5;
    
    public AirportSimulatorTextView()
    {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update(Simulator simulator)
    {
        AirportSimulator as = (AirportSimulator) simulator;
        Counter counter = as.getCounter();
        
        StringBuilder columnSB = new StringBuilder();
        StringBuilder valuesSB = new StringBuilder();
        
        for(String key : counter.keySet())
        {
            String val = String.valueOf(counter.get(key));
            int colWidth = Math.max(Math.max(key.length(), val.length()), MIN_COL_WIDTH);
            
            columnSB.append(String.format("|%1$-"+colWidth+"s", key));
            valuesSB.append(String.format("|%1$-"+colWidth+"s", val));
        }
        columnSB.append("|");
        valuesSB.append("|");
        
        String columnString = columnSB.toString();
        String valuesString = valuesSB.toString();
        
        String hbreak = horizontalBreak(columnString.length());
        
        println("Tick #" + as.getTick());
        println(hbreak);
        println(columnString);
        println(valuesString);
        println(hbreak);
    }

    private String horizontalBreak(int length)
    {
        StringBuilder sb = new StringBuilder('+');
        
        while(length-- > 2) //Leave 2 characters free for the +s
        {
            sb.append('-');
        }
        
        sb.append('+');
        
        return sb.toString();
    }
    
    @Override
    public void end(Simulator simulator)
    {
        update(simulator);
    }

}
