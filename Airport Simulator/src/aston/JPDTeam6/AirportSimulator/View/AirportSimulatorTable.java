package aston.JPDTeam6.AirportSimulator.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;
import aston.JPDTeam6.SimulatorLibrary.Simulator;
import aston.JPDTeam6.SimulatorLibrary.View.GUIview;


public class AirportSimulatorTable extends GUIview {

	final int blankSpace = 6;
	private JTable table;
	private ArrayList<String> string;

	public AirportSimulatorTable(){
		string = new ArrayList<String>();
		int j=0;
		while (j<50){

			string.add("1v1 me");
			j++;
		}
		displayTable();
	}

	private void displayTable(){
		table = new JTable((string.size()/3)+2, 3);

		//This section sets the collumn names for the table.
		table.setValueAt("Tick Num:", 0, 0);
		table.setValueAt("Event:", 0, 1);
		table.setValueAt("uw0tm8?:", 0, 2);

		//this section sets the content for the cells of the table.
		int i = 0;
		int row = 1;
		int collumn = 0;
		while(i<string.size()){
			table.setValueAt(string.get(i),row,collumn);
			if(collumn==2){
				collumn=0;
				row++;
			}
			else{
				collumn++;
			}
			i++;
		}
		//this section sets up the rest of the GUI.
		final JFrame tableFrame = new JFrame("Airport Table");
		JButton quitButton = new JButton("Quit");

		quitButton.setMaximumSize(new Dimension(10, 10));

		JPanel tablePanel = new JPanel();
		JPanel buttonsPanel = new JPanel();

		tablePanel.setLayout(new FlowLayout());
		tablePanel.add(table);

		buttonsPanel.setLayout(new FlowLayout());
		buttonsPanel.add(quitButton);

		tableFrame.setLayout(new BorderLayout());
		((JPanel)tableFrame.getContentPane()).setBorder(new 
				EmptyBorder(blankSpace, blankSpace, blankSpace, blankSpace));
		tableFrame.add(tablePanel, BorderLayout.NORTH);
		tableFrame.add(buttonsPanel, BorderLayout.SOUTH);

		tableFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		model.addRow(new Object[]{(simulator.getTick()), "...", "1v1 me"});
	}


	public void end(Simulator simulator){
	}
}
