package aston.JPDTeam6.AirportSimulator;

import aston.JPDTeam6.SimulatorLibrary.Configuration;
import aston.JPDTeam6.SimulatorLibrary.View.View;
import aston.JPDTeam6.AirportSimulator.Model.AirTrafficControllers.*;
import aston.JPDTeam6.AirportSimulator.View.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class CLIFrontend
{
    private static HashMap<String, Class<? extends View>>                 validViews = new HashMap<String, Class<? extends View>>();
    private static HashMap<String, Class<? extends AirTrafficController>> validATC   = new HashMap<String, Class<? extends AirTrafficController>>();

    static
    {
        validViews.put("text", AirportSimulatorTextView.class);
        validViews.put("graph", AirportSimulatorGraph.class);
        validViews.put("table", AirportSimulatorTable.class);
        validViews.put("plaincounter", AirportSimulatorParseableCounter.class);

        validATC.put("fifo", FIFO.class);
        validATC.put("random", RandomPick.class);
        validATC.put("stf", ShortestTimeFirst.class);
    }

    private CLIFrontend()
    {
    }

    private static class CLIFrontendParameters
    {
        @Parameter
        private List<String> parameters            = new ArrayList<String>();

        @Parameter(names = { "-t", "--simulation-length" }, description = "Number of ticks to run the simulation for")
        private Long         simulationLength      = 10l;

        @Parameter(names = { "--commercial-probability" }, description = "Decimal probability of a commercial plane being spawned")
        private Float        commercialProbability = 0.01f;

        @Parameter(names = { "-s", "--seed" }, description = "The seed to use for the random number generator")
        private Long         seed                  = (new Random()).nextLong();

        @Parameter(names = { "-v", "--view" }, description = "Views to use. Valid options are: text, graph, plaincounter and table")
        private List<String> views                 = new ArrayList<String>(Arrays.asList("text"));

        @Parameter(names = { "-c", "--air-trafic-controller" }, description = "Options: fifo, random, stf. Default: fifo")
        private String       airTrafficController  = "fifo";

        @Parameter(names = { "--gui" }, description = "Starts with a GUI, ignores command line parameters")
        private Boolean      enableGUI             = false;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
    {
        AirportSimulator airportSimulator;
        AirTrafficController atc;
        ArrayList<View> enabledViews = new ArrayList<View>();
        CLIFrontendParameters cfp = new CLIFrontendParameters();

        new JCommander(cfp, args);

        if (cfp.enableGUI)
        {
            GUIFrontend.main(args);
            // System.exit(0);
            return;
        }

        for (String viewType : cfp.views)
        {
            if (validViews.containsKey(viewType))
            {
                enabledViews.add(validViews.get(viewType).newInstance());
            }
            else
            {
                System.err.println("Warning: unknown view: '" + viewType + "'");
            }
        }

        if (validATC.containsKey(cfp.airTrafficController))
        {
            atc = validATC.get(cfp.airTrafficController).newInstance();
        }
        else
        {
            System.err.println("Error: Unknown air traffic controller: '" + cfp.airTrafficController + "'");
            System.exit(-1);
            return;
        }

        Configuration configs = new Configuration();
        configs.setOption("simulation length", cfp.simulationLength.longValue());
        configs.setOption("commercial probability", cfp.commercialProbability.floatValue());
        configs.setOption("random seed", cfp.seed.longValue());

        try
        {
            View[] views = enabledViews.toArray(new View[enabledViews.size()]);
            airportSimulator = new AirportSimulator(configs, views, atc);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-2);
            return;
        }

        airportSimulator.runSimulation();
    }

}
