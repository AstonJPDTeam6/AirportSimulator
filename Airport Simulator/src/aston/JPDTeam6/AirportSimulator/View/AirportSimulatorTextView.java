package aston.JPDTeam6.AirportSimulator.View;

import java.util.List;
import java.util.Map.Entry;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;
import aston.JPDTeam6.AirportSimulator.Model.Planes.Plane;
import aston.JPDTeam6.SimulatorLibrary.Counter;
import aston.JPDTeam6.SimulatorLibrary.Event;
import aston.JPDTeam6.SimulatorLibrary.EventLog;
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

        println("Tick #" + as.getTick());

        printState(as);
        printCounts(counter);
        printEvents(as);
    }

    private void printCounts(Counter counter)
    {
        StringBuilder columnSB = new StringBuilder();
        StringBuilder valuesSB = new StringBuilder();

        for (String key : counter.keySet())
        {
            String val = String.valueOf(counter.get(key));
            int colWidth = Math.max(Math.max(key.length(), val.length()), MIN_COL_WIDTH);

            columnSB.append(String.format("|%1$-" + colWidth + "s", key));
            valuesSB.append(String.format("|%1$-" + colWidth + "s", val));
        }
        columnSB.append("|");
        valuesSB.append("|");

        String columnString = columnSB.toString();
        String valuesString = valuesSB.toString();

        String hbreak = horizontalBreak(columnString.length());

        println(hbreak);
        println(columnString);
        println(valuesString);
        println(hbreak);
    }

    private void printEvents(Simulator sim)
    {
        long ctick = sim.getTick();
        EventLog eventLog = sim.getEventLog();

        println("Events:");

        for (Event event : eventLog.getEventsForTick(ctick))
        {
            println("* " + event.getName());
        }
    }

    private void printState(AirportSimulator as)
    {
        print("Airport state: ");
        println(as.airport.currentAirportEvent.getAirportState().name());
    }

    private String horizontalBreak(int length)
    {
        StringBuilder sb = new StringBuilder("+");

        while (length-- > 2) // Leave 2 characters free for the +s
        {
            sb.append('-');
        }

        sb.append('+');

        return sb.toString();
    }

    /**
     * Calculates average waiting times for all planes in the simulation
     * 
     * @param simulator
     * @return 0: average waiting time, 1: max waiting time, 2: min waiting time
     */
    private double[] calculateWaitingTimes(Simulator simulator)
    {
        EventLog eventLog = simulator.getEventLog();

        long totalWaitingTime = 0;
        long totalPlanesWaited = 0;
        long maxWaitingTime = 0;
        long minWaitingTime = Long.MAX_VALUE;

        for (Entry<Long, List<Event>> tickEntry : eventLog.getAllEvents().entrySet())
        {
            List<Event> tickEvents = tickEntry.getValue();
            for (Event event : tickEvents)
            {
                if (event.getType().equals("takeoff") || event.getType().equals("landed"))
                {
                    long waitingTime = tickEntry.getKey() - ((Plane) event.getActor()).getQueuedTime();
                    maxWaitingTime = Math.max(waitingTime, maxWaitingTime);
                    minWaitingTime = Math.min(waitingTime, minWaitingTime);

                    totalWaitingTime += waitingTime;
                    totalPlanesWaited++;
                }
            }
        }

        return new double[] { (double) totalWaitingTime / totalPlanesWaited, maxWaitingTime, minWaitingTime };
    }

    @Override
    public void end(Simulator simulator)
    {
        update(simulator);

        double[] times = calculateWaitingTimes(simulator);

        System.out.println("Average Waiting time: " + times[0]);
        System.out.println("Max waiting time: " + times[1]);
        System.out.println("Min waiting time: " + times[2]);
    }

}
