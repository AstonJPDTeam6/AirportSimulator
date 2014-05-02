package aston.JPDTeam6.AirportSimulator.View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map.Entry;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;
import aston.JPDTeam6.SimulatorLibrary.Counter;
import aston.JPDTeam6.SimulatorLibrary.Simulator;
import aston.JPDTeam6.SimulatorLibrary.View.GUIview;

public class AirportSimulatorTable extends GUIview
{

    private final int      blankSpace = 6;
    private JTable table;

    public AirportSimulatorTable()
    {
        displayTable();
    }

    private void displayTable()
    {
        table = new JTable();

        // this section sets up the rest of the GUI.
        final JFrame tableFrame = new JFrame("Airport Table");
        JButton quitButton = new JButton("Quit");

        quitButton.setMaximumSize(new Dimension(10, 10));

        JPanel buttonsPanel = new JPanel();

        JScrollPane tableScroll = new JScrollPane(table);

        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(quitButton);

        tableFrame.setLayout(new BorderLayout());
        ((JPanel) tableFrame.getContentPane()).setBorder(new EmptyBorder(blankSpace, blankSpace, blankSpace, blankSpace));

        tableFrame.add(tableScroll, BorderLayout.CENTER);
        tableFrame.add(buttonsPanel, BorderLayout.SOUTH);

        tableFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        quitButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    tableFrame.dispose();
                }
            });

        tableFrame.pack();
        tableFrame.setVisible(true);
    }

    public void update(Simulator simulator)
    {
        AirportSimulator sim = (AirportSimulator) simulator;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Counter counter = sim.getCounter();

        ArrayList<Object> rowData = new ArrayList<Object>();

        rowData.add(sim.getTick());

        if (model.findColumn("Tick #") == -1)
        {
            model.addColumn("Tick #");
        }

        for (Entry<String, Long> row : counter.entrySet())
        {
            Long val = row.getValue();
            String key = row.getKey();

            if (model.findColumn(key) == -1)
            {

                model.addColumn(key);
            }

            rowData.add(val);
        }

        model.addRow(rowData.toArray());
    }

    public void end(Simulator simulator)
    {
        update(simulator);
    }
}