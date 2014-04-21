package aston.JPDTeam6.SimulatorLibrary;
import java.util.HashMap;

public class Counter extends HashMap<String, Long> {

    private static final long serialVersionUID = -2336428496670115987L;
    
    public Counter() {}
    
    public void incr(String key) {
        incr(key, 1);
    }
    
    /**
     * Increments the key by an optional value
     * @param key
     * @param value
     */
    public void incr(String key, long value) {
        long temp = get(key);
        
        this.put(key, temp + value);
    }
    
}

//public class Counter {
//
//	public HashMap  <String, Long > counts = new HashMap <String, Long>() ;
//
//	public Counter() {}
//
//	public void incrCount(String key){
//		incrCount(key, 1);
//	}
//	public void incrCount(String key, long value)
//	{
//		long temp = counts.get(key);
//		temp += value;
//		
//		counts.put(key, temp);
//	}
//	
//	public void setCount(String key, long value)
//	{
//	    counts.put(key, value);
//	}
//	
//	public long getCount(String key)
//	{
//	 return counts.get(key);
//	}
//
//	@SuppressWarnings("unchecked")
//	public  HashMap  <String, Long >  getAllCount()
//	{
//		return (HashMap<String, Long>) counts.clone();
//	}
//
//	public void reset()
//	{
//		counts.clear();
//	}
//}
