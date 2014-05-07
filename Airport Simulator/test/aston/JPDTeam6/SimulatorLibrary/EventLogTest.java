/**
 * 
 */
package aston.JPDTeam6.SimulatorLibrary;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import aston.JPDTeam6.SimulatorLibrary.Model.Actor;
import aston.JPDTeam6.TestSimulator.TestActor;
import aston.JPDTeam6.TestSimulator.TestSimulator;

/**
 * @author antoine
 * 
 */
public class EventLogTest
{
    TestSimulator testSim;
    EventLog      eventLog;
    Actor         testActor;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        testSim = new TestSimulator();

        testActor = new TestActor(testSim);

        eventLog = new EventLog(testSim);
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.SimulatorLibrary.EventLog#EventLog(aston.JPDTeam6.SimulatorLibrary.Simulator)}
     * .
     */
    @Test
    public void testEventLog()
    {
        // Ensure the initial event log is empty
        assertEquals(0, eventLog.getAllEvents().size());
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.SimulatorLibrary.EventLog#addEvent(aston.JPDTeam6.SimulatorLibrary.Event)}
     * .
     */
    @Test
    public void testAddEvent()
    {
        Event event = new Event("name", testActor, "type", "detail");
        eventLog.addEvent(event);

        Event storedEvent = eventLog.getEventsForTick(0).get(0);

        assertEquals(event, storedEvent);
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.SimulatorLibrary.EventLog#getEventsForTick(long)}.
     * <P>
     * Fills tick 0 with 10 events and tick 5 with 1 event.
     */
    @Test
    public void testGetEventsForTick()
    {
        testSim.setTick(0);

        for (int i = 0; i < 10; i++)
        {
            Event event = new Event("event" + i, testActor, "type");

            eventLog.addEvent(event);
        }

        testSim.setTick(5);

        eventLog.addEvent(new Event("event on tick 5", testActor, "type"));

        assertEquals(0, eventLog.getEventsForTick(5000).size());
        assertEquals(10, eventLog.getEventsForTick(0).size());
        assertEquals(1, eventLog.getEventsForTick(5).size());
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.SimulatorLibrary.EventLog#getAllEvents()}.
     */
    @Test
    public void testGetAllEvents()
    {
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                Event event = new Event("event #" + (i * 10) + j, testActor, "test");
                eventLog.addEvent(event);
            }
        }

        int count = 0;

        for (List<Event> tickEvents : eventLog.getAllEvents().values())
        {
            count += tickEvents.size();
        }

        assertEquals(100, count);
    }

}
