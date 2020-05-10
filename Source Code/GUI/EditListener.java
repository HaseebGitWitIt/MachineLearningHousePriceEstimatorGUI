package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import Model.Controller;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 * 
 * EditListener class
 * Specific action to menu item: Edit
 * class response whenever item Edit is pressed in the menu bar
 */
public class EditListener extends Controller implements ActionListener {

	/**
	* Edits the chosen knnComparible when Edit is pressed
	* @param agr0: The ActionEvent that called this method
	*/ 
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Obtain the current JList
		JList attributeJList = super.getAttributeJList();
		// Get the current selectezd value
		int index = (int) attributeJList.getSelectedIndex();
		super.replace(index);
		
	}

}
