package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* Handles all event from the AddListener class
* Essentially the controller where AddListener is the model
**/
public class AttributeGetterListener implements ActionListener {
	private AddListener adder;
	
	public AttributeGetterListener(AddListener adder) {
		this.adder = adder;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Update the AddListener class
		String caller = arg0.getActionCommand();
		
		adder.update(caller, arg0);
		
	}

}
