package aston.JPDTeam6.SimulatorLibrary.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import aston.JPDTeam6.SimulatorLibrary.Simulator;


public class AirportSimulatorGUIView extends GUIview {

	private JFrame frame;
	private Simulator simulator;
	final int blankSpace = 6; 

	public AirportSimulatorGUIView(Simulator simulator){
		
		super(simulator);
		this.simulator = simulator;
		
		frame = new JFrame("Airport Simulator");
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	
		JPanel commandBox = new JPanel();
		JButton quitButton = new JButton();
		JButton graphButton = new JButton();
		JButton tableButton = new JButton();
		JMenuBar menuBar = new JMenuBar();


		frame.setJMenuBar(menuBar);
		frame.setLayout(new BorderLayout());
		((JPanel)frame.getContentPane()).setBorder(new 
				EmptyBorder(blankSpace, blankSpace, blankSpace, blankSpace));

		quitButton.setText("Quit");
		quitButton.setToolTipText("Quit Application");
		
		graphButton.setText("Show Graph");
		graphButton.setToolTipText("Displays a graph");
		
		frame.add(commandBox, BorderLayout.SOUTH);
		commandBox.add(quitButton, BorderLayout.SOUTH);
		commandBox.add(graphButton, BorderLayout.SOUTH);
		commandBox.add(tableButton, BorderLayout.SOUTH);	
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitApp();
			}
		});
		
		graphButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayGraph();
			}
		});
		
		tableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayTable();
			}
		});
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitApp();
			}
		});   
	}
	
	private void exitApp(){
		int response = JOptionPane.showConfirmDialog(frame, 
				"Do you really want to quit?",
				"Quit?",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	private void displayGraph(){
		final JFrame graphFrame = new JFrame("Airport Table");
		JButton quitButton = new JButton("Quit");
		JButton displayButton = new JButton("Display Graph");
		//quitButton.putClientProperty(createFrameString, createFrame);
		quitButton.setMaximumSize(new Dimension(10, 10));
		displayButton.setMaximumSize(new Dimension(10,10));
		
		JPanel tablePanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		tablePanel.setLayout(new BorderLayout());
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(displayButton);
		buttonsPanel.add(quitButton);
		
		graphFrame.setLayout(new BorderLayout());
		graphFrame.add(tablePanel, BorderLayout.NORTH);
		graphFrame.add(buttonsPanel, BorderLayout.SOUTH);
		
		graphFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent c = (JComponent) e.getSource();
				JFrame f = (JFrame) c.getClientProperty(c);
				f.dispose();
			}
		});
		
		graphFrame.pack();
		graphFrame.setVisible(true);
	}
	
	private void displayTable(){
		final JFrame tableFrame = new JFrame("Airport Table");
		JButton quitButton = new JButton("Quit");
		
		//quitButton.putClientProperty(createFrameString, createFrame);
		quitButton.setMaximumSize(new Dimension(10, 10));
		
		JPanel tablePanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		tablePanel.setLayout(new BorderLayout());
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.add(quitButton);
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
		
		tableFrame.pack();
		tableFrame.setVisible(true);
	}		
}