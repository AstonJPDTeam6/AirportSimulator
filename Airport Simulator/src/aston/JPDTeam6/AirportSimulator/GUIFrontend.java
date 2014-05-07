package aston.JPDTeam6.AirportSimulator;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUIFrontend
{

    private JFrame frmAirportSimulatorSetup;
    private JLabel lblSpawnProbability;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        // EventQueue.invokeLater(new Runnable()
        // {
        // public void run()
        // {
        // try
        // {
        // GUIFrontend window = new GUIFrontend();
        // window.frmAirportSimulatorSetup.setVisible(true);
        // }
        // catch (Exception e)
        // {
        // e.printStackTrace();
        // }
        // }
        // });
        GUIFrontend window = new GUIFrontend();
        window.frmAirportSimulatorSetup.setVisible(true);
    }

    /**
     * Create the application.
     */
    public GUIFrontend()
    {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        frmAirportSimulatorSetup = new JFrame();
        frmAirportSimulatorSetup.setResizable(false);
        frmAirportSimulatorSetup.setTitle("Airport Simulator Setup");
        frmAirportSimulatorSetup.setBounds(100, 100, 639, 300);
        frmAirportSimulatorSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAirportSimulatorSetup.getContentPane().setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("left:max(59dlu;min)"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(174dlu;pref):grow"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(18dlu;default)"), FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(null);
        frmAirportSimulatorSetup.getContentPane().add(panel_1, "2, 2, 1, 15, left, fill");
        panel_1.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.LABEL_COMPONENT_GAP_COLSPEC, }, new RowSpec[] { FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("23px"), FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

        JLabel lblViews = new JLabel("Views");
        panel_1.add(lblViews, "2, 2, left, center");

        final JCheckBox chckbxText = new JCheckBox("Text");
        panel_1.add(chckbxText, "2, 4, left, top");

        final JCheckBox chckbxTable = new JCheckBox("Table");
        panel_1.add(chckbxTable, "2, 6");

        final JCheckBox chckbxGraph = new JCheckBox("Graph");
        panel_1.add(chckbxGraph, "2, 8");

        final JCheckBox chckbxPlainText = new JCheckBox("Plain Text");
        panel_1.add(chckbxPlainText, "2, 10");

        JLabel lblSimulationLength = new JLabel("Simulation Length");
        frmAirportSimulatorSetup.getContentPane().add(lblSimulationLength, "4, 2, 3, 1");

        final JSpinner spinnerSimuationLength = new JSpinner();
        spinnerSimuationLength.setModel(new SpinnerNumberModel(new Integer(2880), new Integer(0), null, new Integer(1)));
        frmAirportSimulatorSetup.getContentPane().add(spinnerSimuationLength, "4, 4, 3, 1");

        JLabel lblCommercialSpawnProbability = new JLabel("Commercial Spawn Probability");
        frmAirportSimulatorSetup.getContentPane().add(lblCommercialSpawnProbability, "4, 6, 3, 1");

        final JSlider sliderCommercialProbability = new JSlider();
        sliderCommercialProbability.setMinorTickSpacing(50);
        sliderCommercialProbability.setMajorTickSpacing(250);
        sliderCommercialProbability.setPaintTicks(true);
        sliderCommercialProbability.setMaximum(1000);
        sliderCommercialProbability.addChangeListener(new ChangeListener()
            {

                @Override
                public void stateChanged(ChangeEvent arg0)
                {
                    lblSpawnProbability.setText(String.valueOf(sliderCommercialProbability.getValue() / 1000.0));
                }

            });
        frmAirportSimulatorSetup.getContentPane().add(sliderCommercialProbability, "4, 8");

        lblSpawnProbability = new JLabel();
        lblSpawnProbability.setText(String.valueOf(sliderCommercialProbability.getValue() / 1000.0));
        frmAirportSimulatorSetup.getContentPane().add(lblSpawnProbability, "6, 8");

        JLabel lblPlaneSchedulingAlgorithm = new JLabel("Plane Scheduling Algorithm");
        frmAirportSimulatorSetup.getContentPane().add(lblPlaneSchedulingAlgorithm, "4, 10, 3, 1");

        final JComboBox<String> comboBoxATC = new JComboBox<String>();
        comboBoxATC.setModel(new DefaultComboBoxModel<String>(new String[] { "fifo", "random", "stf" }));
        frmAirportSimulatorSetup.getContentPane().add(comboBoxATC, "4, 12, 3, 1, fill, default");

        JLabel lblSeed = new JLabel("Seed");
        frmAirportSimulatorSetup.getContentPane().add(lblSeed, "4, 14, 3, 1");

        final JSpinner spinnerSeed = new JSpinner();
        spinnerSeed.setModel(new SpinnerNumberModel(new Long(0), null, null, new Long(1)));
        spinnerSeed.setValue((new Random()).nextLong());
        frmAirportSimulatorSetup.getContentPane().add(spinnerSeed, "4, 16, 3, 1");

        JButton btnStartSimulation = new JButton("Start Simulation");
        frmAirportSimulatorSetup.getContentPane().add(btnStartSimulation, "4, 18, 3, 1");
        btnStartSimulation.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0)
                {
                    List<String> enabledViews = new ArrayList<String>();
                    if (chckbxText.isSelected())
                    {
                        enabledViews.add("text");
                    }
                    if (chckbxTable.isSelected())
                    {
                        enabledViews.add("table");
                    }
                    if (chckbxGraph.isSelected())
                    {
                        enabledViews.add("graph");
                    }
                    if (chckbxPlainText.isSelected())
                    {
                        enabledViews.add("plaincounter");
                    }

                    String atc = (String) comboBoxATC.getSelectedItem();
                    long simulationLength = Long.valueOf((int) spinnerSimuationLength.getValue());
                    double commercialProbability = sliderCommercialProbability.getValue() / 1000.0;
                    long seed = (long) spinnerSeed.getValue();

                    String[] enabledViewsArr = enabledViews.toArray(new String[enabledViews.size()]);

                    startSimulation(enabledViewsArr, atc, seed, simulationLength, commercialProbability);

                }
            });
        btnStartSimulation.setVerticalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel();
        panel.setBorder(null);
        // frame.getContentPane().add(panel, "6, 18, fill, fill");
    }

    private void startSimulation(String[] enabledViews, String airTrafficController, long seed, long simulationLength, double commercialProbability)
    {
        List<String> args = new ArrayList<String>();
        if (seed != 0)
        {
            args.add("-s");
            args.add(String.valueOf(seed));
        }

        args.add("-t");
        args.add(String.valueOf(simulationLength));

        args.add("--commercial-probability");
        args.add(String.valueOf(commercialProbability));

        args.add("-c");
        args.add(airTrafficController);

        for (String ev : enabledViews)
        {
            args.add("-v");
            args.add(ev);
        }

        try
        {
            CLIFrontend.main(args.toArray(new String[args.size()]));
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
