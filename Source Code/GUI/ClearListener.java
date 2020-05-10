package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Controller;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 * 
 * ClearListener class
 * Specific action to menu item: Clear
 * class response whenever item Clear is pressed in the menu bar
 */
public class ClearListener extends Controller implements ActionListener{
	
	/**
	* Handles events from the clear button.
	* Clears all houses from the house list
	* @param e: The action event that called this method
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// Remove all houses from list
		super.emptyList();
		super.changeFirstRun();
	}


}
