/**
 * 
 */
package aston.JPDTeam6.AirportSimulator.Model.Planes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;
import aston.JPDTeam6.AirportSimulator.Model.AirTrafficControllers.AirTrafficController;
import aston.JPDTeam6.SimulatorLibrary.Configuration;
import aston.JPDTeam6.SimulatorLibrary.Simulator;
import aston.JPDTeam6.TestSimulator.TestSimulator;

/**
 * @author antoine
 * 
 */
public class PlaneTest
{
    TestPlane testPlane;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        Configuration cfg = new Configuration();
        cfg.setOption("commercial probability", 0.01f);
        cfg.setOption("simulation length", 1l);

        AirTrafficController atc = new aston.JPDTeam6.AirportSimulator.Model.AirTrafficControllers.FIFO();

        AirportSimulator testSim = new AirportSimulator(cfg, null, atc);
        testPlane = new TestPlane(testSim, "Test Plane", Plane.PlaneIntent.LANDING, 5, 6, 10, 15);
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#getTimeToLand()}
     * .
     */
    @Test
    public void testGetTimeToLand()
    {
        assertEquals(5, testPlane.getTimeToLand());
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#getTimeToTakeOff()}
     * .
     */
    @Test
    public void testGetTimeToTakeOff()
    {
        assertEquals(6, testPlane.getTimeToTakeOff());
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#getFlyingTime()}
     * .
     */
    @Test
    public void testGetFlyingTime()
    {
        long flyingTime = testPlane.getFlyingTime();

        assertTrue(flyingTime < 15);
        assertTrue(flyingTime >= 10);
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#getQueuedTime()}
     * .
     */
    @Test
    public void testGetQueuedTime()
    {
        assertEquals(0, testPlane.getQueuedTime());
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#canStart()}.
     */
    @Test
    public void testCanStart()
    {
        assertTrue(testPlane.canStart());

        testPlane.setBreakdownTime(Long.MAX_VALUE);

        assertFalse(testPlane.canStart());

        testPlane.setBreakdownTime(Long.MIN_VALUE);
        testPlane.setIsWaiting(false);

        assertFalse(testPlane.canStart());

        testPlane.setBreakdownTime(Long.MAX_VALUE);

        assertFalse(testPlane.canStart());
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#start()}.
     */
    @Test
    public void testStart()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#hasTakenOff()}.
     */
    @Test
    public void testHasTakenOff()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#hasLanded()}.
     */
    @Test
    public void testHasLanded()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#hasBrokendown()}
     * .
     */
    @Test
    public void testHasBrokendown()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#hasRepaired()}.
     */
    @Test
    public void testHasRepaired()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#getFlyingTimeLeft()}
     * .
     */
    @Test
    public void testGetFlyingTimeLeft()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#canFly()}.
     */
    @Test
    public void testCanFly()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#isDone()}.
     */
    @Test
    public void testIsDone()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#setIntent(aston.JPDTeam6.AirportSimulator.Model.Planes.Plane.PlaneIntent)}
     * .
     */
    @Test
    public void testSetIntent()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#getIntent()}.
     */
    @Test
    public void testGetIntent()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.AirportSimulator.Model.Planes.Plane#isWaiting()}.
     */
    @Test
    public void testIsWaiting()
    {
        fail("Not yet implemented");
    }

}
