package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.Controller;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 * 
 * AddListener class
 * Specific action to menu item: Quit
 * class response whenever item Quit is pressed in the menu bar
 */
public class SaveListener extends Controller implements ActionListener{
	/**
	* Quits the program when the Quit menu item is selected
	* @param e: The ActionEvent that called this method
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!super.getComparibleList().isEmpty()) {
			// TODO Auto-generated method stub
			String fileName = JOptionPane.showInputDialog(super.getFrame(), "Enter the filename: ");
			super.saveData(fileName);
		} else {
			JOptionPane.showMessageDialog(super.getFrame(), "There is nothing to save. Please enter at least testing.");
		}
	}

}
