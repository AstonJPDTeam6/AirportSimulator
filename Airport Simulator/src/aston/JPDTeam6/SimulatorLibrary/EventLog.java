package aston.JPDTeam6.SimulatorLibrary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventLog
{
    private Simulator                  simulator;
    private HashMap<Long, List<Event>> events = new HashMap<Long, List<Event>>();

    public EventLog(Simulator simulator)
    {
        this.simulator = simulator;
    }

    public void addEvent(Event event)
    {
        long ct = simulator.getTick();
        ArrayList<Event> currentTick = (ArrayList<Event>) events.get(ct);
        if (currentTick == null)
        {
            currentTick = new ArrayList<Event>();
            events.put(ct, currentTick);
        }

        currentTick.add(event);
    }

    public final List<Event> getEventsForTick(long tick)
    {
        List<Event> currentTick = (ArrayList<Event>) events.get(tick);

        if (currentTick == null)
        {
            return Arrays.asList();
        }
        else
        {
            return currentTick;
        }
    }

    public final Map<Long, List<Event>> getAllEvents()
    {
        return events;
    }
}
