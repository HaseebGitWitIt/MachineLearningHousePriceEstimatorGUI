package GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;

import Model.Controller;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 * 
 * WindowsGUI class
 * Makes the view for the program
 */
public class WindowsGUI extends Controller{

	// Local instance variables
	private JFrame frame;
	private JPanel pane;

	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu help;
	
	private JMenuItem addTraining; //Add a new house
	private JMenuItem clear; //Clear the list houses
	private JMenuItem addTesting; //Estimate the house
	private JMenuItem estimateSelected;
	private JMenuItem estimateSet; //Estimate several houses at once
	private JMenuItem test; //Run the tests
	private JMenuItem quit; //Quit the program
	private JTextArea output; //Output the current information in the map
	private JMenuItem edit;	//Edit the information
	private JMenuItem save;	//Save the information
	private JMenuItem load;	//load the information
	
	private AddListener addListen;
	
	private JList trainingList;
	private JList testingList;

	/**
	* Constructor 
	* Creates a new GUI
	*/
	public WindowsGUI(JList trainingExampleList, JList testingExampleList){
		super();
		addListen = new AddListener();
		
		trainingList = trainingExampleList;
		testingList = testingExampleList;
		
		initUI();
	}
	
	/**
	* Initializes and adds everything to the GUI
	*/ 
	private void initUI() {
		
		frame = new JFrame();
		
		/*
		 * Create and assign listeners to a JMenu Items
		 */
		menuBar = new JMenuBar();
		
		menu = new JMenu("Menu");
		
		help = new JMenu("Help");
		help.addMenuListener(new HelpListener());
		
		addTraining = new JMenuItem("Add Training Example"); 
		addTraining.addActionListener(addListen);
		
		clear = new JMenuItem("Clear"); 
		clear.addActionListener(new ClearListener());
		
		addTesting = new JMenuItem("Add Testing Example"); 
		addTesting.addActionListener(new EstimateListener());
		
		estimateSelected = new JMenuItem("Estimate (Selected)");
		estimateSelected.addActionListener(new EstimateSelectedListener());
		
		estimateSet = new JMenuItem("Estimate (Set)");
		estimateSet.addActionListener(new EstimateSetListener());
		
		test = new JMenuItem("Test"); 
		test.addActionListener(new TestListener());
		
		quit = new JMenuItem("Quit"); 
		quit.addActionListener(new QuitListener());
		
		edit = new JMenuItem("Edit");
		edit.addActionListener(new EditListener());
		
		save = new JMenuItem("Save");
		save.addActionListener(new SaveListener());
		
		load = new JMenuItem("Load");
		load.addActionListener(new LoadListener());
		
		output = new JTextArea();

		/*
		 * Add the item into menu
		 */
		menu.add(addTraining);
		menu.add(addTesting);
		menu.add(estimateSelected);
		menu.add(estimateSet);
		menu.add(edit);
		menu.add(clear);		
		menu.add(test);		
		menu.add(save);
		menu.add(load);
		menu.add(quit);
		
		edit.setEnabled(false);
		estimateSelected.setEnabled(false);
		estimateSet.setEnabled(false);
		//Add the menu into menu bar
		menuBar.add(menu);
		menuBar.add(help);
		pane = new JPanel();
		
		//Frame properties
		pane.setVisible(true);
		frame.add(pane);
		frame.setLayout(new FlowLayout());
		frame.setJMenuBar(menuBar);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		GridLayout layout = new GridLayout(4, 1);
		
		pane.setLayout(layout);
		
		pane.add(new JLabel("Training Examples: \n"));
		pane.add(trainingList);
		pane.add(new JLabel("Testing Examples: \n"));
		pane.add(testingList);
		
		super.setFrame(frame);
		super.setPanel(pane);
		super.setEdit(edit);
		super.setEstimateSelected(estimateSelected);
		super.setEstimateSet(estimateSet);
	}

	/**
	* Returns the addListener object to access add functions
	*/
	public AddListener getAddListener() {
		
		return addListen;
	}
	
}
