package aston.JPDTeam6.AirportSimulator.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
	private ArrayList<String> tableData;

	public AirportSimulatorTable(){
		tableData = new ArrayList<String>();
//		int j=0;
//		while (j<90){
//
//			tableData.add("1v1 me");
//			j++;
//		}
		
		displayTable();
	}

	private void displayTable(){
		table = new JTable();
		
		PlaneCountTableModel pcm = new PlaneCountTableModel();
		table.setModel(pcm);
//tableData.size()/2)+1
		//This section sets the collumn names for the table.
		
		
//		table.setValueAt("Tick Num:", 0, 0);
//		table.setValueAt("Event:", 0, 1);

		//this section sets the content for the cells of the table.
//		int i = 0;
//		int row = 1;
//		int collumn = 0;
//		while(i<tableData.size()){
//			table.setValueAt(tableData.get(i),row,collumn);
//			if(collumn==1){
//				collumn=0;
//				row++;
//			}
//			else{
//				collumn++;
//			}
//			i++;
//		}
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
		long landQueueCount = sim.getCounter().get("planes landing");
		long tick = sim.getTick();
		
		Event event = new Event();
		event.tick = tick;
		event.event = String.valueOf(landQueueCount);
		
		PlaneCountTableModel model = (PlaneCountTableModel)table.getModel();
		model.addRow(event);
		
		table.repaint();
//		
//		DefaultTableModel model = (DefaultTableModel) table.getModel();
//		model.addRow(new Object[]{(simulator.getTick()), "...", "1v1 me"});
	}

	public void end(Simulator simulator){
	}
	
	private class PlaneCountTableModel extends AbstractTableModel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 8430913283853273362L;
		private ArrayList<Event> events;
		
		public PlaneCountTableModel() {
			events = new ArrayList<Event>();
		}
		
		public void addRow(Event event) {
			events.add(event);
		}
		
		public String getColumnName(int column) {
			switch(column) {
			case 0:
				return "Tick";
			case 1:
				return "Count";
			default:
				return null;
			}
		}
		
		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return events.size();
		}

		@Override
		public Object getValueAt(int row, int column) {
			switch(column) {
			case 0:
				return String.valueOf(events.get(row).tick);
			case 1:
				return events.get(row).event;
			}
			return null;
		}
		
	}
	
	private class Event {
		public long tick;
		public String event;
	}
}