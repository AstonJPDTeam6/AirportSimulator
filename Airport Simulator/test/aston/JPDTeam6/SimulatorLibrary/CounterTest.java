/**
 * 
 */
package aston.JPDTeam6.SimulatorLibrary;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author antoine
 * 
 */
public class CounterTest
{
    Counter counter;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        counter = new Counter();
    }

    /**
     * Test method for {@link aston.JPDTeam6.SimulatorLibrary.Counter#Counter()}
     * .
     */
    @Test
    public void testCounter()
    {
        assertEquals(0, counter.get("unset key"));
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.SimulatorLibrary.Counter#incr(java.lang.String)}.
     */
    @Test
    public void testIncrString()
    {
        assertEquals(0, counter.get("key 1"));
        assertEquals(0, counter.get("key 2"));

        counter.incr("key 1");

        assertEquals(1, counter.get("key 1"));
        assertEquals(0, counter.get("key 2"));

        counter.incr("key 1");

        assertEquals(2, counter.get("key 1"));
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.SimulatorLibrary.Counter#incr(java.lang.String, long)}
     * .
     */
    @Test
    public void testIncrStringLong()
    {
        counter.incr("key", 10);

        assertEquals(10, counter.get("key"));
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.SimulatorLibrary.Counter#get(java.lang.String)}.
     */
    @Test
    public void testGetString()
    {
        counter.put("key", 15l);

        assertEquals(15, counter.get("key"));
    }

}
