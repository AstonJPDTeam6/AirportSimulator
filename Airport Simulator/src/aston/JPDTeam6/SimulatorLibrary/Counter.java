package aston.JPDTeam6.SimulatorLibrary;

import java.util.LinkedHashMap;

public class Counter extends LinkedHashMap<String, Long>
{

    private static final long serialVersionUID = -2336428496670115987L;

    public Counter()
    {
        super();

        this.clear();
    }

    public void incr(String key)
    {
        incr(key, 1);
    }

    /**
     * Increments the key by an optional value
     * 
     * @param key
     * @param value
     */
    public void incr(String key, long value)
    {
        long temp = get(key);

        put(key, temp + value);
    }

    public long get(String key)
    {
        Long temp = super.get(key);
        if (temp == null)
        {
            return 0l;
        }
        else
        {
            return temp.longValue();
        }
    }

}
