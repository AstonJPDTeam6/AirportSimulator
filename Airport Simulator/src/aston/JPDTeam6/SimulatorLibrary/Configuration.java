package aston.JPDTeam6.SimulatorLibrary;

import java.util.HashMap;

public class Configuration
{

    private HashMap<String, Object> configurations = new HashMap<String, Object>();
    
    public Object getOption(String option) throws Exception
    {
        Object value = configurations.get(option);
        if(value == null)
        {
            throw new Exception("Configuration not found: " + option);
        }
        else
        {
            return value;
        }
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getOption(String option, T defaultValue)
    {
        try
        {
            return (T) getOption(option);
        }
        catch(Exception e)
        {
            return defaultValue;
        }
    }
//    public Object getOption(String option, Object defaultValue)
//    {
//        Object value = configurations.get(option);
//        if(value == null)
//        {
//            return defaultValue;
//        }
//        else
//        {
//            return value;
//        }
//    }
    
    public void setOption(String option, Object value)
    {
        configurations.put(option, value);
    }

}
