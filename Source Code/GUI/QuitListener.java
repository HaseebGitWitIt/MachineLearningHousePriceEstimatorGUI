package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 * 
 * AddListener class
 * Specific action to menu item: Quit
 * class response whenever item Quit is pressed in the menu bar
 */
public class QuitListener implements ActionListener{
	/**
	* Quits the program when the Quit menu item is selected
	* @param e: The ActionEvent that called this method
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

}
