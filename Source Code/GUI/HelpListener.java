package GUI;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
* Class to handle the Help button being pressed
**/
public class HelpListener implements MenuListener{
	// Components for the GUI
	private JFrame f;
	private JScrollPane pane;
	private JTextArea area;
	
	/**
	* Constructor
	* Creates a new HelpListener
	**/
	public HelpListener() {
		
	}
	
	/**
	* Hanles the menu being cancelled
	* @param arg0: The event that called this method
	**/
	@Override
	public void menuCanceled(MenuEvent arg0) {
	}

	/**
	* Handles the item being deselected
	* @param arg0: The event that called this method
	**/
	@Override
	public void menuDeselected(MenuEvent arg0) {
	}

	/**
	* Handles the item being selected
	* @param arg0: The event that called this method
	**/
	@Override
	public void menuSelected(MenuEvent arg0) {
		f = new JFrame("Help");
		f.setSize(600, 630);
		
		area = new JTextArea(300,230);
		
		//Create the instructions and add to the area
		String s = "Add Training Example: \n\nTo add a new Training Example to the database, "
				+ "click Add Training Example under menu.\nOn the new frame that appears, enter the name at the top.\n"
				+ "If this is your first time adding a Training Example, enter the names in the text boxes in the "
				+ "left column.\nIf this is your first time adding a Training Example you also have to choose the "
				+ "attribute type \nfrom the drop down menus in the middle column.\nThe attribute types and values"
				+ " are:\n\tDouble: Enter a number in the text boxes in the right column\n\tEnum: Choose the enum"
				+ " value from the drop box in the right column\n\tString: Enter the text in the text boxes in the "
				+ "right column\n\tComposite: Allows you to create a new composite attribute. \n\t\tComposite attributes "
				+ "are made up of sub-attributes \n\t\twhich can be added to and deleted from the composite attribute "
				+ "\n\t\tusing the buttons that appear under the composite attribute.\n\n";
		
		String s2 = "Add Testing Example: \n\n(NOTE: This will only be available after adding at least "
				+ "one Training Example). \nTo add a new Testing Example to test the algorithm, "
				+ "click Add Testing Example under menu.\nEnter the name of the Testing Example in the text box"
				+ " at the top of the frame that appears.\nEnter the values for each attribute, or select"
				+ " the value from the dropdown menu if the attribute \nis an enum, from the centre text boxes/"
				+ "drop down menus.\nPress the radio button beside the attribute you want to calculate using the "
				+ "algorithm.\nSelect the distance metric you want to use for each attribute from the drop down"
				+ " menu on the \nright column of the frame.\nYou can also enter the expected value for the attribute"
				+ " in the text box at the bottom of the frame.\nOnce you're done, press 'Finished' at the bottom "
				+ "of the frame.\n\n";
		
		String s3 = "Estimate (Selected): \n\nTo estimate an attribute in a Testing Example, click on the Testing "
				+ "Example in the list on the frame \nand select Estimate (Selected) from menu.\nOn the frame "
				+ "that appears,"
				+ " select the attribute you want to calculate by selecting the corresponding\nradio button.\nYou"
				+ "can also select the distance metric you want to use for each attribute by choosing it from the\n"
				+ "drop down menus on the right of the frame.\nFinally, you can also enter the expected value in the "
				+ "text box at the bottom of the frame.\nWhen you're done, press 'Esimate' at the bottom of the "
				+ "frame.\n\n";
		
		String s4 = "Estimate (Set): \n\nTo estimate a selected attribute in every Testing Example and get a "
				+ "total accuracy, press Estimate (Set).\nOn the frame that appears, select the attribute you want "
				+ "to calculate by selecting the corresponding\nradio button.\nYou can also select the distance"
				+ " metric you want to use for each attribute by choosing it from the\ndrop down menus on the right "
				+ "of the frame.\nFinally, you can also enter the expected value for each Testing Example in the "
				+ "corresponding text box \nat the bottom of the frame.\nWhen you're done, press 'Estimate' at the "
				+ "bottom of the frame.\n\n";
		
		String s5 = "EDIT: \n\nTo modify a object, select an object from the displayed list and click EDIT from menu."
				+ " \n You will be prompted to enter the new values\n\n";
		
		String s6 = "CLEAR: \n\nTo delete all the data, click CLEAR under menu\n\n";
		
		String s7 = "TEST: \n\nTo run some examples, select Test under menu.\n "
				+ "The results will be displayed in the console\n\n";
		
		String s8 = "SAVE: \n\nTo save the current state of the program (All Training Examples and all Testing "
				+ "Examples), press Save \non the menu.\nIn the dialog box that appears, enter the name for the "
				+ "file you want to create.\nThe program will create an xml file with the given name and "
				+ "save all of the data in the file.\n\n";
		
		String s9 = "LOAD: \n\nTo load a previously saved program state, press Load.\nIn the popup box that appears,"
				+ " enter the name of the file you want to load.\nIf the file is found, the program state will "
				+ "be restored to the given one.\n\n";
	
		String s10 = "QUIT: \n\nOnce you are done with the program, you can quit by "
				+ "closing the window or by clicking Quit\n under the menu. Thank you for using our program! \n\n";
		
		area.append(s);
		area.append(s2);
		area.append(s3);
		area.append(s4);
		area.append(s5);
		area.append(s6);
		area.append(s7);
		area.append(s8);
		area.append(s9);
		area.append(s10);

		pane = new JScrollPane(area);
	
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		f.add(pane);
		f.setVisible(true);
	}
	
}
	
