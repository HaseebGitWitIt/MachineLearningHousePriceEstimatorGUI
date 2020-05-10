package GUI;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Attribute;
import Model.Controller;
import Model.DoubleAttribute;
import Model.EnumAttribute;
import Model.Enums;
import Model.StringAttribute;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 * 
 * AddListener class
 * Specific action to menu item: Add
 * class response whenever item Add is pressed in the menu bar
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class AddListener extends Controller implements ActionListener {
	// ArrayList for all of the attribute names
	private ArrayList<String> stringAttributeNames;
	private ArrayList<String> enumAttributeNames;
	private ArrayList<String> doubleAttributeNames;
	private ArrayList<String> namesToDisplay;
	private ArrayList<String> typesToDisplay;
	private ArrayList<Integer> compositeLevel;
	
	// ArrayList to hold the GUI components
	private ArrayList<JTextField> attributeNames;
	private ArrayList<JComboBox> attributeTypes;
	private ArrayList<JTextField> attributeValues;
	private ArrayList<JComboBox> attributeValuesEnums;
		
	private JFrame f;
	
	// Parameters for the grid layout
	private int numCols;
	private int numRows;
	private int baseNumCols = 3;
	
	private int width = 600;
	private int height = 600;
	
	private boolean firstRun = true; // true if the attribute names have not been added yet
	
	private GridLayout layout;
	
	// String arrays for the Combo boxes
	private String[] attributeTypeNames = {"Double", "Enum", "String", "Composite"};
	private String[] attributeTypeNamesWithoutComposite = {"Double", "Enum", "String"};
	
	private String[] enumTypeNames = Enums.getValues();
	
	private int attributeLimit = 20; // Maximum amount of attributes that can be entered
	private int numAttributes;
	
	// Name for the object
	private String objectName = "";
	private JTextField objectNameInput;
	
	private int compositeLimit = 2; // Maximum composite level
	private int currentHighestComposite;
	
	private ActionEvent caller; // Used to keep track when the frame is set to invisible
	
	/**
	 * Handle the "Create" button being pressed
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// If it is the first time that the button is pressed, than get the attribute names and values
		// Otherwise, only get the attribute values
		
		super.setFrameVisibility(false);
		
		if (!firstRun) {
			stringAttributeNames = super.getStringAttributeNames();
			enumAttributeNames = super.getEnumAttributeNames();
			doubleAttributeNames = super.getDoubleAttributeNames();
			namesToDisplay = super.getNamesToDisplay();
			typesToDisplay = super.getTypesToDisplay();
			compositeLevel = super.getCompositeLevels();
			
			attributeNames.clear();
			attributeTypes.clear();
			attributeValuesEnums.clear();
			attributeValues.clear();
			
			for (String name : namesToDisplay) {
				attributeNames.add(new JTextField());
				attributeTypes.add(new JComboBox(attributeTypeNames));
			}			
			
			
			int i = 0;
			
			while (i < typesToDisplay.size()) {
				if (typesToDisplay.get(i).equals("Enum")) {
					attributeValuesEnums.add(new JComboBox(enumTypeNames));
					attributeValues.add(null);
				} else {
					attributeValuesEnums.add(null);
					attributeValues.add(new JTextField());
				}
				i = i + 1;
			}
			
			getAttributes();
		} else {
			// Reset all local variables
			numCols = 3;
			numRows = 5;
			
			numAttributes = 1;
			
			currentHighestComposite = 0;
			
			stringAttributeNames = new ArrayList<String>();
			enumAttributeNames = new ArrayList<String>();
			doubleAttributeNames = new ArrayList<String>();
			namesToDisplay = new ArrayList<String>();
			typesToDisplay = new ArrayList<String>();
			compositeLevel = new ArrayList<Integer>();
			
			attributeNames = new ArrayList<JTextField>();
			attributeTypes = new ArrayList<JComboBox>();
			attributeValues = new ArrayList<JTextField>();
			attributeValuesEnums = new ArrayList<JComboBox>();
			
			objectNameInput = null;
			
			caller = null;
				
			// Get the attribute names and values
			getAttributeNames();
			
			f.setVisible(true);
		}
	}
	
	/**
	 * Get the attribute names and values
	 */
	public void getAttributeNames() {
		numAttributes = numAttributes - 1;
		numRows = numRows - 1;
		addAnotherAttribute(-1, 0);
	}
	
	/**
	 * Get only the attribute values
	 */
	public void getAttributes() {
		// Create a new frame
		f = new JFrame("Add Training Example");
		
		JFrame temp = super.getFrame();
		
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				temp.setVisible(true);
			}
		});
		
		f.setSize(width, height);
		
		int b = 0;
		currentHighestComposite = 0;
		
		while (b < compositeLevel.size()) {
			if (compositeLevel.get(b) > currentHighestComposite) {
				currentHighestComposite = compositeLevel.get(b);
			}
			b = b + 1;
		}
		
		numCols = baseNumCols + currentHighestComposite;
		numRows = 3 + attributeNames.size();
		
		// Use the gird layout
		layout = new GridLayout(numRows, numCols);
		
		f.setLayout(layout);
		
		int i = 0;
		
		// Print a label and textField for getting the name
		f.add(new JLabel("<html>Please enter the name of the object: </html>"));
		if (objectNameInput == null) {
			objectNameInput = new JTextField();
		}
		objectNameInput.setText("");
		f.add(objectNameInput);
		
		i = 0;
		
		while (i < numCols - 2) {
			f.add(new JLabel(""));
			i = i + 1;
		}
		
		
		f.add(new JLabel("Attribute Name:"));
		f.add(new JLabel("Attribute Type: "));
		f.add(new JLabel("<html>Please enter the value in the textbox:</html>"));
		
		i = 0;
		
		while (i < numCols - 3) {
			f.add(new JLabel(""));
			i = i + 1;
		}
		
		i = 0;
		
		while (i < attributeNames.size()) {
			int j = 0;
			
			while (j < compositeLevel.get(i)) {
				f.add(new JLabel(""));
				j = j + 1;
			}
			 
			f.add(new JLabel(namesToDisplay.get(i))); // Add the text field for the attribute names
			f.add(new JLabel(typesToDisplay.get(i))); // Add the drop down menu for the attribute type
			
			if (attributeTypeNames[attributeTypes.get(i).getSelectedIndex()].equals("Enum")) { // Attribute is an enum	
				JComboBox toSet = attributeValuesEnums.get(i);
				
				toSet.setSelectedIndex(0);
				f.add(toSet); // Add a drop down menu for choosing the attribute value
			} else { // Attribute is not an enum
				JTextField toSet = attributeValues.get(i);
				
				toSet.setText("");
				f.add(toSet); // Add a text field for entering the attribute value
			}
			
			j = 0;
			
			while (j + compositeLevel.get(i) + 3 < numCols) {
				f.add(new JLabel(""));
				j = j + 1;
			}
					
									
			i = i + 1;
		}
		
		i = compositeLevel.get(compositeLevel.size() - 1);
				
		i = 0;
		
		// Add a button for saving the entered attributes
		JButton finished = new JButton("Finished");
		finished.addActionListener(new AttributeGetterListener(this));
		f.add(finished);
		
		i = 0;
		
		while (i < numCols - 3) {
			f.add(new JLabel(""));
			i = i + 1;
		}
		
		f.setVisible(true);
	}
	
	/**
	 * Update the current frame using the given string as the instruction
	 * @param toDo: The method to use to updated the frame
	 */
	public void update(String toDo, ActionEvent event) {
		if ((!toDo.equals("Finished")) && (f != null) && (f.isVisible())) { // Set the frame to invisible
			f.setVisible(false);
			caller = event; // Save the event that caused the frame to go invisible
		}
		
		if (toDo.contains("Add Another Attribute")) { // Add another attribute
			String[] indexSplit1 = toDo.split("Index: ");
			String[] indexSplit2 = indexSplit1[1].split(" Composite Level: ");
			
			int index = Integer.parseInt(indexSplit2[0]);
			int compositeLevel = Integer.parseInt(indexSplit2[1]);
			
			addAnotherAttribute(index, compositeLevel);
		} else if (toDo.equals("Finished")) { // Check if finished, if so save the attribute names and values			
			finished();
		} else if (toDo.equals("Attribute Type")) { // One of the attributes was changed to/from an enum, so update the value input method
			updateAttributeValueInputMethods();
		} else if (toDo.contains("Delete Last Attribute")) { // Delete the last attribute in the list
			String[] indexSplit1 = toDo.split("Index: ");
			int index = Integer.parseInt(indexSplit1[1]);
			
			deleteAttribute(index);
		}
		
		if (!toDo.equals("Finished")) { // If not finished, update the frame
			updateFrame();
		}		
		
		if ((!toDo.equals("Finished")) && (!f.isVisible()) && ((event == caller) || (caller == null))) {
			// If this is the event that set the frame to invisible, set it back to visible
			f.setVisible(true);
		}
	}
	
	/**
	 * Update the frame by recreating it
	 */
	public void updateFrame() {		
		// Create a new frame
		f = new JFrame("Add Training Example");
		
		JFrame temp = super.getFrame();
		
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				temp.setVisible(true);
			}
		});
		
		f.setSize(width, height);
		
		int b = 0;
		currentHighestComposite = 0;
		
		while (b < compositeLevel.size()) {
			if (compositeLevel.get(b) > currentHighestComposite) {
				currentHighestComposite = compositeLevel.get(b);
			}
			b = b + 1;
		}
		
		numCols = baseNumCols + currentHighestComposite;
		
		int count = 0;
		int a = 0;
		
		while (a < attributeTypes.size()) {
			if (attributeTypeNames[attributeTypes.get(a).getSelectedIndex()].equals("Composite")) {
				count = count + 1;
			}
			a = a + 1;
		}
		
		
		// Use the gird layout
		layout = new GridLayout(numRows + count, numCols);
		
		f.setLayout(layout);
		
		int i = 0;
		
		// Print a label and textField for getting the name
		f.add(new JLabel("<html>Please enter the name of the object: </html>"));
		if (objectNameInput == null) {
			objectNameInput = new JTextField();
		}
		f.add(objectNameInput);
		
		i = 0;
		
		while (i < numCols - 2) {
			f.add(new JLabel(""));
			i = i + 1;
		}
		
		// Print some labels for instructions
		f.add(new JLabel("<html>Please enter the name for each attribute:</html>"));	
		f.add(new JLabel("<html>Please choose the attribute type from the dropdown menu:</html>"));
		f.add(new JLabel("<html>Please enter the value in the textbox:</html>"));
		
		i = 0;
		
		while (i < numCols - 3) {
			f.add(new JLabel(""));
			i = i + 1;
		}
		
		f.add(new JLabel("Attribute Name:"));
		f.add(new JLabel("Attribute Type: "));
		f.add(new JLabel("Attribute Value: "));
		
		i = 0;
		
		while (i < numCols - 3) {
			f.add(new JLabel(""));
			i = i + 1;
		}
		
		i = 0;
		
		while (i < attributeNames.size()) {
			int j = 0;
			
			while (j < compositeLevel.get(i)) {
				f.add(new JLabel(""));
				j = j + 1;
			}
			
			f.add(attributeNames.get(i)); // Add the text field for the attribute names
			f.add(attributeTypes.get(i)); // Add the drop down menu for the attribute type
			
			if (attributeTypeNames[attributeTypes.get(i).getSelectedIndex()].equals("Enum")) { // Attribute is an enum
				f.add(attributeValuesEnums.get(i)); // Add a drop down menu for choosing the attribute value
			} else { // Attribute is not an enum
				f.add(attributeValues.get(i)); // Add a text field for entering the attribute value
			}
			
			j = 0;
			
			while (j + compositeLevel.get(i) + 3 < numCols) {
				f.add(new JLabel(""));
				j = j + 1;
			}
						
			if (((compositeLevel.size() > i + 1) && (compositeLevel.get(i) > compositeLevel.get(i + 1)))) {
				int h = compositeLevel.get(i);
				
				while (h > compositeLevel.get(i + 1)) {
					j = 0;
					while (j < h) {
						f.add(new JLabel(""));
						j = j + 1;
					}
					
					// Add a button for adding another attribute
					JButton another = new JButton("Add Another Attribute");
					another.setActionCommand("Add Another Attribute Index: " + (i + 1) + " Composite Level: " + h);
					another.addActionListener(new AttributeGetterListener(this));
					f.add(another);
					
					// Add a button for deleting the last attribute
					JButton delete = new JButton("Delete Last Attribute");
					delete.setActionCommand("Delete Last Attribute Index: " + i);
					delete.addActionListener(new AttributeGetterListener(this));
					f.add(delete);
					
					j = 0;
					while (j + h + 2 < numCols) {
						f.add(new JLabel(""));
						j = j + 1;
					}
					h = h - 1;
				}
			}
			
			i = i + 1;
		}
		
		i = compositeLevel.get(compositeLevel.size() - 1);
		int start = compositeLevel.get(compositeLevel.size() - 1);
		
		while (i > 0) {
			int j = 0;
			while (j < i) {
				f.add(new JLabel(""));
				j = j + 1;
			}
			
			// Add a button for adding another attribute
			JButton another = new JButton("Add Another Attribute");
			another.setActionCommand("Add Another Attribute Index: " + (attributeNames.size()) + " Composite Level: " + i);
			another.addActionListener(new AttributeGetterListener(this));
			f.add(another);
			
			// Add a button for deleting the last attribute
			JButton delete = new JButton("Delete Last Attribute");
			delete.setActionCommand("Delete Last Attribute Index: " + (attributeNames.size() + 1 - (start - i)));
			delete.addActionListener(new AttributeGetterListener(this));
			f.add(delete);
			
			j = i;
			while (j + 2 < numCols) {
				f.add(new JLabel(""));
				j = j + 1;
			}
			
			i = i - 1;
		}
		
		i = 0;
				
		// Add a button for adding another attribute
		JButton another = new JButton("Add Another Attribute");
		another.setActionCommand("Add Another Attribute Index: " + (attributeNames.size()) + " Composite Level: " + 0);
		another.addActionListener(new AttributeGetterListener(this));
		f.add(another);
		
		// Add a button for saving the entered attributes
		JButton finished = new JButton("Finished");
		finished.addActionListener(new AttributeGetterListener(this));
		f.add(finished);
		
		// Add a button for deleting the last attribute
		JButton delete = new JButton("Delete Last Attribute");
		delete.setActionCommand("Delete Last Attribute Index: " + (attributeNames.size() + 1));
		delete.addActionListener(new AttributeGetterListener(this));
		f.add(delete);
		
		i = 0;
		
		while (i < numCols - 3) {
			f.add(new JLabel(""));
			i = i + 1;
		}
		
	}
	
	/**
	 * Add another attribute
	 */
	public void addAnotherAttribute(int index, int indent) {
		if ((index < 0) || (index > attributeNames.size())){
			index = attributeNames.size();
		}
		
		if (numAttributes < attributeLimit) { // Check if the user reached the attribute number limit
			// Increment the row count and attribute cound
			numRows = numRows + 1;
			numAttributes = numAttributes + 1;
			compositeLevel.add(index, indent);
				
			if (indent > currentHighestComposite) {
				currentHighestComposite = indent;
			}
				
			// Add the new text field for the name
			attributeNames.add(index, new JTextField());	
				
			// Add the new textfield for the value
			// Add a null value to the enum tracking array for consistency
			attributeValues.add(index, new JTextField());
			attributeValuesEnums.add(index, null);
				
			// Add the drop down menu for the attribute type
			if (indent >= compositeLimit) {
				attributeTypes.add(index, new JComboBox(attributeTypeNamesWithoutComposite));
			} else {
				attributeTypes.add(index, new JComboBox(attributeTypeNames));
			}		
			
			attributeTypes.get(index).setActionCommand("Attribute Type");
			attributeTypes.get(index).addActionListener(new AttributeGetterListener(this));
			attributeTypes.get(index).setSelectedIndex(0);
				
			//updateFrame(); // update the frame	
		}
		
		// If the maximum amount of attributes has been reached, prevent any attribute from turning to composite attributes
		if (numAttributes == attributeLimit) {
			int i = 0;
			
			while (i < attributeTypes.size()) {
				JComboBox box = attributeTypes.get(i);
				if (!(attributeTypeNames[box.getSelectedIndex()].equals("Composite"))) {
					int toSet = box.getSelectedIndex();
					box.setModel(new DefaultComboBoxModel(attributeTypeNamesWithoutComposite));
					box.setSelectedIndex(toSet);
				}
				i = i + 1;
			}
		}
	}
	
	/**
	 * Check if the user is done entering the names and values
	 * To be done:
	 *   All attributes must have a name
	 *   All attributes must have a type
	 *   All attributes must have a value
	 * @return: true if done, false otherwise
	 */
	public boolean checkForFinish() {
		boolean check = true;
		
		if (firstRun) {
			//Check if a name was entered
			if (objectNameInput.getText().equals("")) {
				JOptionPane.showMessageDialog(f, "Please enter a name for the TrainingExample");
				check = false;
			}
			
			// Check that all attributes have a name
			int i = 0;
			while (i < attributeNames.size()) {
				JTextField field = attributeNames.get(i);
				if(field.getText().equals("")) { // Attribute does not have a name
					JOptionPane.showMessageDialog(f, "Please enter a name for all attributes");
					check = false;
					break;
				}
				i = i + 1;
			}
		}
			
		int i = 0;
		
		// Check if all attributes have a value
		while (i < attributeTypes.size()) {
			JComboBox box = attributeTypes.get(i);
			
			// Only check if the attribute is not an enum
			// Enum uses a drop down menu, so it is always forced to have a value
			if ((attributeTypeNames[box.getSelectedIndex()].equals("Double")) || (attributeTypeNames[box.getSelectedIndex()].equals("String"))) {
				/**
				if (attributeValues.get(i).getText().equals("")) { // Attribute does not have a value
					JOptionPane.showMessageDialog(f, "Please enter a value for all attributes");
					check = false;
					break;
				}
				**/	
				if (attributeTypeNames[box.getSelectedIndex()].equals("Double")) { // Check that doubles have numerical values
					if (!attributeValues.get(i).getText().equals("")) { // Value is entered
						if (!attributeValues.get(i).getText().matches("-?\\d+(\\.\\d+)?")) { // Value is not a double
							JOptionPane.showMessageDialog(f, "Please enter a number for all doubles");
							check = false;
							break;
						}
					}
				}
			}
				
			i = i + 1;
		}
		
		return(check);
	}
	
	/**
	 * Save all of the attribute information to the super class
	 */
	public void saveAttributeInformation() {
		int i = 0;
		
		objectName = objectNameInput.getText();
		if (firstRun) {
			ArrayList<String> compositeNames = new ArrayList<String>();
			while (i < attributeNames.size()) {				
				if ((i != 0) && (compositeLevel.get(i) < compositeLevel.get(i - 1))) {
					int j = compositeLevel.get(i - 1) - compositeLevel.get(i);
					
					while (j > 0) {
						compositeNames.remove(compositeNames.size() - 1);
						j = j - 1;
					}
				}
				
				String toAdd = "";
				for (String add : compositeNames) {
					toAdd = toAdd + add;
				}
				
				int index = attributeTypes.get(i).getSelectedIndex(); // Get the attribute type
				String name = attributeNames.get(i).getText(); // Get the attribute name
				
				if (index != 3) {
					name = toAdd + name;
				}
					
				// Add the attribute name to the designated ArrayList
				// Add the type to the display list 
				if (attributeTypeNames[index].equals("Double")) { // Attribute is a double
					doubleAttributeNames.add(name);
					super.addDoubleAttributeName(name);
					typesToDisplay.add("Double");
				} else if (attributeTypeNames[index].equals("Enum")) { // Attribute is an enum
					enumAttributeNames.add(name);
					super.addEnumAttributeName(name);
					typesToDisplay.add("Enum");
				} else if (attributeTypeNames[index].equals("String")) { // Attribute is a string
					stringAttributeNames.add(name);
					super.addStringAttributeNames(name);
					typesToDisplay.add("String");
				} else if (attributeTypeNames[index].equals("Composite")) {
					typesToDisplay.add("Composite");
					compositeNames.add(name);
				}
				
				// Add the attribute name to the display list
				namesToDisplay.add(name);
						
				i = i + 1;
			}
		}
		//Create the hashmap to use to create the new Training Example
		HashMap<String, Attribute> storingHashMap = new HashMap<String, Attribute>();
				
		i = 0;
				
		// Get the attribute values
		while (i < namesToDisplay.size()) {
			int index = attributeTypes.get(i).getSelectedIndex(); // Get the attribute type
			
			if (index != 3) {
				String name = namesToDisplay.get(i); // Get the attribute name
					
				Attribute toAdd = null;
						
				// For each attribute, get the attribute value
				// Create an attribute of the corresponding type
				if (attributeTypeNames[index].equals("Double")) { // Attribute is a double
					Double val = null;
					
					if (!attributeValues.get(i).getText().equals("")) {
						val = Double.valueOf(attributeValues.get(i).getText());
					} 
						
					toAdd = new DoubleAttribute(name, val);
				} else if (attributeTypeNames[index].equals("Enum")) { // Attribute is an enum
					String val = (Enums.getValues()[attributeValuesEnums.get(i).getSelectedIndex()]).toString();
						
					toAdd = new EnumAttribute(name, Enums.valueOf(val));
				} else if (attributeTypeNames[index].equals("String")) { // Attribute is a String
					String val = attributeValues.get(i).getText();
					
					toAdd = new StringAttribute(name, val);
				}
						
				// Add the attribute values to the storing array in the super class
				super.addAttributeToAttributeMap(name, toAdd);
				super.addAttributeList(toAdd);
				
				storingHashMap.put(name, toAdd); // Add the attribute to the storing HashMap
			}		
			i = i + 1;
		}
			
		// If the first run, save all the names, types, and composite levels to display
		if (firstRun) {
			firstRun = false;
			super.setCompositeLevel(compositeLevel);
			super.setNamesToDisplay(namesToDisplay);
			super.setTypesToDisplay(typesToDisplay);
		}
			
		f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING)); // Close the frame
		
		super.addTrainingExample(objectName, storingHashMap); // Create the new TrainingExample
		
		super.setFrameVisibility(true);
			
		JOptionPane.showMessageDialog(f, "Training Example successfully added!");
	}
	
	/**
	 * Check if the user is finished entering attributes,
	 * If so, save the attribute information and quit the new frame
	 */
	public void finished() {
		if(checkForFinish()) {
			saveAttributeInformation();
		}	
	}
	
	/**
	 * Update the attribute value input method
	 */
	public void updateAttributeValueInputMethods() {
		int i = 0;
		
		// Loop through the attribute types
		// Check if the attribute is using the proper input method
		// If not using the proper method, swap to the proper method
		while (i < attributeNames.size()) {				
			if (attributeTypeNames[attributeTypes.get(i).getSelectedIndex()].equals("Enum")) { // Attribute is an enum
				if (attributeValuesEnums.get(i) == null) { // Check if using a drop down box
					// If not using a drop down box, swap to a drop down menu
					attributeValuesEnums.set(i, new JComboBox(enumTypeNames));
					attributeValues.set(i, null);
				} 
				
				while ((compositeLevel.size() > i + 1) && (compositeLevel.get(i + 1) > compositeLevel.get(i))) {
					deleteAttribute(i + 1);
				}
			} else if (attributeTypeNames[attributeTypes.get(i).getSelectedIndex()].equals("Composite")){ // Attribute is a composite
				if (attributeValues.get(i) == null) { // Check if using a text field
					// If not using a text field, swap to one
					attributeValuesEnums.set(i, null);
					JTextField temp = new JTextField();
					attributeValues.set(i, temp);
				}				
				attributeValues.get(i).setEnabled(false);
				if ((compositeLevel.size() <= i + 1) || (compositeLevel.get(i + 1) != compositeLevel.get(i) + 1)) {
					addAnotherAttribute(i + 1, compositeLevel.get(i) + 1);
				}
			} else { // Attribute is not an enum
				if (attributeValues.get(i) == null) { // Check if using a text field
					// If not using a text field, swap to one
					attributeValuesEnums.set(i, null);
					attributeValues.set(i, new JTextField());
				} else {
					attributeValues.get(i).setEnabled(true);
				}
				
				while ((compositeLevel.size() > i + 1) && (compositeLevel.get(i + 1) > compositeLevel.get(i))) {
					deleteAttribute(i + 1);
				}
			}
			i = i + 1;
		}
		
		//updateFrame(); // Update the frame
	}
	
	/**
	 * Delete the last attribute
	 */
	public void deleteAttribute(int index) {
		if ((index < 0) || (index > attributeNames.size() - 1)){
			index = attributeNames.size() - 1;
		}
		
		boolean checkIfLast = true;
		
		int i = index - 1;
		
		if (numAttributes == 1) {
			checkIfLast = true;
		} else {
			while ((i > 0) && (compositeLevel.get(i) >= compositeLevel.get(index))) {
				if (compositeLevel.get(i) == compositeLevel.get(index)) {
					checkIfLast = false;
				}
				i = i - 1;
			}
			
			if (!(attributeTypeNames[attributeTypes.get(i).getSelectedIndex()].equals("Composite"))) {
				checkIfLast = false;
			}
		}
		
		if (!checkIfLast){ // Check if there is an attribute to delete
			// Delete the row and decrement the attribute count
			numRows = numRows - 1;
			numAttributes = numAttributes - 1;
			
			// Remove the attribute name text field
			attributeNames.remove(index);
			
			// Remove the attribute value text field and drop down menu
			attributeValues.remove(index);
			attributeValuesEnums.remove(index);
			
			// Remove the attribute type selection drop down menu
			attributeTypes.remove(index);
			
			int temp = compositeLevel.get(index);
			
			compositeLevel.remove(index);
			
			if (temp < compositeLevel.get(index - 1)) {
				deleteAttribute(index - 1);
			}			
			
			// Update the frame
			//updateFrame();
			
			if (numAttributes == attributeLimit - 1) {
				i = 0;
				
				while (i < attributeTypes.size()) {
					JComboBox box = attributeTypes.get(i);
					int toSet = box.getSelectedIndex();
					box.setModel(new DefaultComboBoxModel(attributeTypeNames));
					box.setSelectedIndex(toSet);
					i = i + 1;
				}
			}
		}
	}
	
	/**
	* Swaps the firstRun variable to it's opposite value
	**/
	public void changeFirstRun() {
		if (firstRun) {
			firstRun = false;	
		} else {
			firstRun = true;
		}
	}
	
	
}
