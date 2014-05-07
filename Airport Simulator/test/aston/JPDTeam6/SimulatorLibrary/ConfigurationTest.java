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
public class ConfigurationTest
{
    Configuration configuration;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        configuration = new Configuration();
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.SimulatorLibrary.Configuration#getOption(java.lang.String)}
     * .
     */
    @Test
    public void testGetOptionString()
    {
        configuration.setOption("test option", "value");

        assertEquals("value", configuration.getOption("test option"));
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.SimulatorLibrary.Configuration#getOption(java.lang.String, java.lang.Object)}
     * .
     */
    @Test
    public void testGetOptionStringObject()
    {
        assertEquals("default value", configuration.getOption("unset option", "default value"));
    }

    /**
     * Test method for
     * {@link aston.JPDTeam6.SimulatorLibrary.Configuration#setOption(java.lang.String, java.lang.Object)}
     * .
     */
    @Test
    public void testSetOption()
    {
        testGetOptionString();
    }

}
