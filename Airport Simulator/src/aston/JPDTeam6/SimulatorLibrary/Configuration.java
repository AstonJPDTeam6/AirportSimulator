package aston.JPDTeam6.SimulatorLibrary;

import java.util.HashMap;

public class Configuration extends HashMap<String, Object>
{
    private static final long serialVersionUID = 4957724085517174815L;

    /**
     * See {@link #getOption(String, Object)}
     */
    public Object getOption(String option)
    {
        return get(option);
    }

    /**
     * @param option
     * @param defaultValue
     *            The default value for the option, if it has not been set
     * @return value of the option
     */
    public Object getOption(String option, Object defaultValue)
    {
        if (containsKey(option))
        {
            return get(option);
        }
        else
        {
            return defaultValue;
        }
    }

    public void setOption(String option, Object value)
    {
        put(option, value);
    }
}

/*
 * public class Configuration {
 * 
 * private HashMap<String, Object> configurations = new HashMap<String,
 * Object>();
 * 
 * public Object getOption(String option) throws Exception { Object value =
 * configurations.get(option); if(value == null) { throw new
 * Exception("Configuration not found: " + option); } else { return value; } }
 * 
 * @SuppressWarnings("unchecked") public <T> T getOption(String option, T
 * defaultValue) { try { return (T) getOption(option); } catch(Exception e) {
 * return defaultValue; } } // public Object getOption(String option, Object
 * defaultValue) // { // Object value = configurations.get(option); // if(value
 * == null) // { // return defaultValue; // } // else // { // return value; // }
 * // }
 * 
 * public void setOption(String option, Object value) {
 * configurations.put(option, value); }
 * 
 * }
 */