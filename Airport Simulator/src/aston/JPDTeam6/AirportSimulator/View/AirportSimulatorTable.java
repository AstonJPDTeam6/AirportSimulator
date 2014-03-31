package aston.JPDTeam6.AirportSimulator.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTable;

import aston.JPDTeam6.AirportSimulator.AirportSimulator;
import aston.JPDTeam6.SimulatorLibrary.Simulator;
import aston.JPDTeam6.SimulatorLibrary.View.GUIview;


public class AirportSimulatorTable extends GUIview {

	private JFrame frame;
	final int blankSpace = 6;
	private JTable table;

	public AirportSimulatorTable(){
		displayTable();
	}

	private void displayTable(){

		final JFrame tableFrame = new JFrame("Airport Table");
		JButton quitButton = new JButton("Quit");
		JButton displayButton = new JButton("Display Table");

		//quitButton.putClientProperty(createFrameString, createFrame);
		quitButton.setMaximumSize(new Dimension(10, 10));
		displayButton.setMaximumSize(new Dimension(10, 10));


		JPanel tablePanel = new JPanel();
		JPanel buttonsPanel = new JPanel();

		tablePanel.setLayout(new BorderLayout());
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(quitButton);
		buttonsPanel.add(displayButton);

		tableFrame.setLayout(new BorderLayout());
		tableFrame.add(tablePanel, BorderLayout.NORTH);
		tableFrame.add(buttonsPanel, BorderLayout.SOUTH);

		tableFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent c = (JComponent) e.getSource();
				JFrame f = (JFrame) c.getClientProperty(c);
				f.dispose();
			}
		});

		displayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTable table = new JTable(2,0);
				TableColumn newCol = new TableColumn();
				newCol.setHeaderValue("Tick Number:");
				table.addColumn(newCol);
				TableColumn newCol1 = new TableColumn();
				newCol1.setHeaderValue("Event");
				table.addColumn(newCol);
				TableColumn newCol2 = new TableColumn();
				newCol2.setHeaderValue("uw0tm8");
				table.addColumn(newCol);
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

}
