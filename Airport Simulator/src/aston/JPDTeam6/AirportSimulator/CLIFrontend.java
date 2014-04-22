package aston.JPDTeam6.AirportSimulator;

import aston.JPDTeam6.SimulatorLibrary.Configuration;
import aston.JPDTeam6.SimulatorLibrary.View.View;
import aston.JPDTeam6.AirportSimulator.View.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class CLIFrontend {

    private static class CLIFrontendParameters
    {
        @Parameter
        private List<String> parameters = new ArrayList<String>();
        
        @Parameter(names = { "-t", "--simulation-length" }, description = "Number of ticks to run the simulation for")
        private Long simulationLength = 10l;
        
        @Parameter(names = { "--commercial-probability" }, description = "Decimal probability of a commercial plane being spawned")
        private Float commercialProbability = 0.01f;
        
        @Parameter(names = { "-s", "--seed" }, description = "The seed to use for the random number generator")
        private Long seed = (new Random()).nextLong();
        
        @Parameter(names = { "-v", "--view" }, description = "Views to use. Valid options are: text, graph and table")
        private List<String> views = new ArrayList<String>(Arrays.asList("text"));
    }

    
	public static void main(String[] args) {
	    CLIFrontendParameters cfp = new CLIFrontendParameters();
	    new JCommander(cfp, args);
	    
	    ArrayList<View> enabledViews = new ArrayList<View>();
	    if(cfp.views.contains("text"))
	    {
	        enabledViews.add(new AirportSimulatorTextView());
	    }
	    if(cfp.views.contains("table"))
	    {
	        enabledViews.add(new AirportSimulatorTable());
	    }
	    if(cfp.views.contains("graph"))
	    {
	        enabledViews.add(new AirportSimulatorGraph());
	    }
		
		Configuration configs = new Configuration();
		configs.setOption("simulation length", cfp.simulationLength.longValue());
		configs.setOption("commercial probability", cfp.commercialProbability.floatValue());
		configs.setOption("random seed", cfp.seed.longValue());
		
		AirportSimulator airportSimulator;
        try
        {
            View[] views = enabledViews.toArray(new View[enabledViews.size()]);
            airportSimulator = new AirportSimulator(configs, views);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
        
		airportSimulator.runSimulation();
	}

}
