package aston.JPDTeam6.SimulatorLibrary;
import java.util.HashMap;



public class Counter {

	public HashMap  <String, Long > counts = new HashMap <String, Long>() ;

	public Counter()
	{

	}

	public void incrCount(String key){
		incrCount(key, 1);
	}
	public void incrCount(String key, long value)
	{
		long temp = counts.get(key);
		temp = temp + value;
		
		counts.put(key, temp);
	}
	
	public long getCount(String key)
	{
	 return counts.get(key);
	}

	@SuppressWarnings("unchecked")
	public  HashMap  <String, Long >  getAllCount()
	{
		return (HashMap<String, Long>) counts.clone();
	}

	public void reset()
	{
		counts.clear();
	}
}
