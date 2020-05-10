package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Model.Algorithm;
import Model.Attribute;
import Model.Controller;
import Model.DoubleAttribute;
import Model.EnumAttribute;
import Model.Enums;
import Model.StringAttribute;
import Model.TestingExample;
import Model.TrainingExample;
import Model.knnComparible;


/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 * 
 * AddListener class
 * Specific action to menu item: Estimate
 * class response whenever item Estimate is pressed in the menu bar
 */
public class EstimateListener extends Controller implements ActionListener {
	// ArrayLists containing all of the attribute names
	private ArrayList<String> stringAttributeNames = super.getStringAttributeNames();
	private ArrayList<String> enumAttributeNames = super.getEnumAttributeNames();
	private ArrayList<String> doubleAttributeNames = super.getDoubleAttributeNames();
	
	private String name; // Names for the object
	private String estimatingAttributeName = ""; // Key to calculate
	private HashMap<String, Attribute> storingHashMap; // HashMap to store the entered values
	
	private Double expectedValue = null;
	private Double calculatedError;
	
	private TestingExample toTest; // TestingExample to test
	private String typeToCalculate; // Type of attribute to calculate
	
	// ArrayLists containing the GUI components
	private ArrayList<JRadioButton> calculateSelection;
	private JTextField objectNameInput;
	private ArrayList<JTextField> attributeValues;
	private ArrayList<JComboBox> attributeValuesEnums;
	private ArrayList<JComboBox> attributeDistanceMethods;
	private JTextField expectedValueField; 
	
	private JFrame f;
	
	// String arrays for combo boxes
	private String[] distanceCalculators = {"Manhatten", "Euclidean", "Minkowski (3)", "Minkowski (4)", "Minkowski (5)", "Polar"};
	private String[] enumValues = Enums.getValues();
		
	/**
	* Gets a TestingExample and calculates the chosen Attribute when Estimate is pressed
	* @param e: The ActionEvent that called this method
	*/ 
	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<knnComparible> temp = super.getComparibleList();
		
		// Check if any TrainingExamples have been created yet
		if (temp.isEmpty()) { // No TrainingExamples
			JOptionPane.showMessageDialog(super.getFrame(), "You must create at least one Training Example before you can estimate.");
		} else { // At least one TrianingExample
			// Reset the storing ArrayLists
			calculateSelection = new ArrayList<JRadioButton>();
			attributeValues = new ArrayList<JTextField>();
			attributeValuesEnums = new ArrayList<JComboBox>();
			
			attributeDistanceMethods = new ArrayList<JComboBox>();
			
			getTestingExampleAttributes(); // Get the values
		}
		
	}
	
	public void getTestingExampleAttributes() {	
		// Create a new frame for getting the attribute values
		// Will be simpler than the one used for getting the attribute names and values
		int width = 600;
		int height = 600;
		
		ArrayList<String> attributeNames = super.getNamesToDisplay();
		ArrayList<String> typesToDisplay = super.getTypesToDisplay();
		ArrayList<Integer> compositeLevel = super.getCompositeLevels();
		
		int a = 0;
		int maxCompositeLevel = 0;
		
		// Find the maximum composite for displaying reasons
		while (a < compositeLevel.size()) {
			int curr = compositeLevel.get(a);
			
			if (curr > maxCompositeLevel) {
				maxCompositeLevel = curr;
			}
			
			a = a + 1;
		}
		
		int numRows = attributeNames.size() + 3;
		int numCols = 5 + maxCompositeLevel;
		
		f = new JFrame("Add Testing Example");
				
		f.setSize(width, height);
				 
		// Use a grid layout
		GridLayout layout = new GridLayout(numRows, numCols);
				
		f.setLayout(layout);
				
		// Print a label and textField for getting the name
		f.add(new JLabel("<html>Please enter the name of the object: </html>"));
		objectNameInput = new JTextField();
		objectNameInput.setText("");
		f.add(objectNameInput);

		a = 0;
		
		while (a < numCols - 2) {
			f.add(new JLabel(""));
			a = a + 1;
		}
				
		// Print some labels for instructions
		f.add(new JLabel("<html>Attribute Names:</html>"));	
		
		a = 0;
		
		// Space the labels properly
		while (a < numCols - 5) {
			f.add(new JLabel(""));
			a = a + 1;
		}
		
		f.add(new JLabel("<html>Attribute Type:</html>"));
		f.add(new JLabel("<html>Please enter the value in the textbox:</html>"));
		
		f.add(new JLabel("<html>Please select the attribute to compute:</html>"));
		f.add(new JLabel("<html>Please select the distance calculator to use:</html>"));
		
		int i = 0;
		
		// Create a new button group for selecting the attribute to compute
		ButtonGroup group = new ButtonGroup();
				
		// Display every attribute name to enter a value for
		while (i < attributeNames.size()) {
			a = 0;
			
			System.out.println("On the left: " + compositeLevel.get(i));
			
			// Indent the current row
			while (a < compositeLevel.get(i)) {
				f.add(new JLabel(""));
				a = a + 1;
			}
			
			// Add labels for the attribute name and type
			f.add(new JLabel(attributeNames.get(i)));
			
			a = 0;
			
			System.out.println("On the right: " + (numCols - 3 - compositeLevel.get(i)));
			
			// Add space before the radio buttons and combo boxes
			while (a < numCols - 5 - compositeLevel.get(i)) {
				f.add(new JLabel(""));
				a = a + 1;
			}
			
			f.add(new JLabel(typesToDisplay.get(i)));
					
			// If the attribute is an enum, add a drop down menu
			// Otherwise, add a text field
			if (typesToDisplay.get(i).equals("Enum")) {
				JComboBox temp = new JComboBox(enumValues);				
				attributeValuesEnums.add(temp);				
				f.add(temp);				
				attributeValues.add(null);
			} else {
				if (typesToDisplay.get(i).equals("Composite")) {
					// Attribute is composite, so do not enter a vlue for it
					f.add(new JLabel(""));
				} else {
					// Attribute is a string or double, so add a TextBox
					JTextField temp = new JTextField();				
					attributeValues.add(temp);				
					f.add(temp);				
					attributeValuesEnums.add(null);
				}
			}
			
			if (!typesToDisplay.get(i).equals("Composite")) {
				// Not a composite attribute, so add a radio button to select as the value to calculate
				JRadioButton temp = new JRadioButton();
				temp.addActionListener(new ActionListener() {
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// When the radio button is selected,
						// deactivate the corresponding textBox/Combobox
						// and reactivate every other textBox/ComboBox
						int i = 0;
						while (i < calculateSelection.size()) {
							if (calculateSelection.get(i).equals(arg0.getSource())) {
								if (attributeValues.get(i) != null) {
									attributeValues.get(i).setEnabled(false);
								}
								
								if (attributeValuesEnums.get(i) != null) {
									attributeValuesEnums.get(i).setEnabled(false);
								}
							} else {
								if (attributeValues.get(i) != null) {
									attributeValues.get(i).setEnabled(true);
								}
								
								if (attributeValuesEnums.get(i) != null) {
									attributeValuesEnums.get(i).setEnabled(true);
								}
							}
							i = i + 1;
						}
						
					}
					
				});
				
				// Add the radio button to the button group
				f.add(temp);
				calculateSelection.add(temp);
				group.add(temp);
			
			} else {
				// Attribute is composite, so add a blank label
				f.add(new JLabel(""));
			}
			
			// If attribute is a composite, or is not a part of a composite,
			// add a ComboBox for choosing the distance metric to use
			if ((typesToDisplay.get(i).equals("Composite")) || (compositeLevel.get(i) == 0)) {
				JComboBox tempBox = new JComboBox(distanceCalculators);
				tempBox.setSelectedIndex(0);
				attributeDistanceMethods.add(tempBox);
				f.add(tempBox);
			} else {
				attributeDistanceMethods.add(null);
				f.add(new JLabel(""));
			}
			
			i = i + 1;
		}
		
		// Set the first radio button to pressed
		calculateSelection.get(0).setSelected(true);
		if (attributeValues.get(0) != null) {
			attributeValues.get(0).setEnabled(false);
		}
		
		if (attributeValuesEnums.get(0) != null) {
			attributeValuesEnums.get(0).setEnabled(false);
		}
		
		f.add(new JLabel("<html>(Optional) Expected Value:</html>"));
		
		expectedValueField = new JTextField();
		f.add(expectedValueField);
			
		// Add a button for saving the attribute values
		JButton finished = new JButton("Finished");
		finished.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (checkForFinish()) {
					f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
					toTest = createTestingExample();
					
					Attribute val = calculateEstimate(toTest);
					
					if (expectedValue != null) {
						JOptionPane.showMessageDialog(f, "The value was calculated to be: " + val.getStringValue()
						+ "\nThe error was calculated to be: " + calculatedError.toString() + "%");
					} else {
						JOptionPane.showMessageDialog(f, "The value was calculated to be: " + val.getStringValue());
					}
				}
			}			
		});
		
		f.add(finished);
				
		a = 0;
		
		while (a < numCols - 3) {
			f.add(new JLabel(""));
			a = a + 1;
		}
		
		f.setVisible(true);	
	}
	
	/**
	* Checks that the user is done entering informatino
	* Checks that all attribute have values, except the one to calculate
	* @return: true if all attributes have values, false otehrwise
	**/
	public boolean checkForFinish() {
		boolean check = true;
		
		ArrayList<String> attributeTypes = super.getAllAttributeTypes();
		
		//Check if a name was entered
		if (objectNameInput.getText().equals("")) {
			JOptionPane.showMessageDialog(f, "Please enter a name for the TrainingExample");
			check = false;
		}
			
		int i = 0;
		
		// Check if all attributes have a value
		while (i < attributeTypes.size()) {
			if (!calculateSelection.get(i).isSelected()) {
				String type = attributeTypes.get(i);
				
				// Only check if the attribute is not an enum
				// Enum uses a drop down menu, so it is always forced to have a value
				if (!(type.equals("Enum"))) {
					/**
					if (attributeValues.get(i).getText().equals("")) { // Attribute does not have a value
						JOptionPane.showMessageDialog(f, "Please enter a value for all attributes");
						check = false;
						break;
					}
					**/
					
					if (type.equals("Double")) { // Check that doubles have numerical values
						if (!attributeValues.get(i).getText().equals("")) { // Value is entered
							if (!attributeValues.get(i).getText().matches("-?\\d+(\\.\\d+)?")) { // Value is not a double
								JOptionPane.showMessageDialog(f, "Please enter a number for all doubles");
								check = false;
								break;
							}
						}
					}
				}
			}
				
			i = i + 1;
		}
		
		return(check);
	}
	
	/**
	* Creates a TestingExample from the entered information
	* @return: The created TestingExample
	**/
	public TestingExample createTestingExample() {
		int i = 0;
		
		ArrayList<String> attributeNames = super.getAllAttributes();
		ArrayList<String> attributeTypes = super.getAllAttributeTypes();
		
		name = objectNameInput.getText();
		
		//Create the hashmap to use to create the new Training Example
		storingHashMap = new HashMap<String, Attribute>();
				
		i = 0;
				
		// Get the attribute values
		while (i < attributeNames.size()) {
			String name = attributeNames.get(i); // Get the attribute name
			String type = attributeTypes.get(i);
				
			Attribute toAdd = null;
					
			// For each attribute, get the attribute value
			// Create an attribute of the corresponding type
			if (type.equals("Double")) { // Attribute is a double
				Double val;
				if (calculateSelection.get(i).isSelected()) {
					// If not value is entered, set the value to 0
					val = null;
				} else {
					if (attributeValues.get(i).getText().equals("")) {
						val = null;
					} else {
						val = Double.valueOf(attributeValues.get(i).getText());
					}
				}
					
				toAdd = new DoubleAttribute(name, val);
			} else if (type.equals("Enum")) { // Attribute is an enum
				String val;
				if (calculateSelection.get(i).isSelected()) {
					val = Enums.valueOf("OLD").toString();
				} else {
					val = (Enums.getValues()[attributeValuesEnums.get(i).getSelectedIndex()]).toString();
				}
				
				toAdd = new EnumAttribute(name, Enums.valueOf(val));
			} else if (type.equals("String")) { // Attribute is a String
				String val;
				if (calculateSelection.get(i).isSelected()) {
					val = "";
				} else {
					val = attributeValues.get(i).getText();
				}
				
				toAdd = new StringAttribute(name, val);
			}
			
			storingHashMap.put(name, toAdd); // Add the attribute to the storing HashMap
					
			i = i + 1;
		}
		
		i = 0;
		
		String toCalculate = "";
		
		// Get the value and type of attribute to calculate
		while (i < calculateSelection.size()) {
			if (calculateSelection.get(i).isSelected()) {
				toCalculate = attributeNames.get(i);
				typeToCalculate = attributeTypes.get(i);
			}
			i = i + 1;
		}
		
		if (!expectedValueField.getText().equals("")) {
			if (typeToCalculate.equals("Double")) {
				expectedValue = Double.valueOf(expectedValueField.getText());
			} else if (typeToCalculate.equals("Enum")) {
				expectedValue = Double.valueOf(Enums.getIndex(expectedValueField.getText()));
			}			
		}
		
		// Create the TestingExample to return
		TestingExample toReturn = new TestingExample(name, storingHashMap, toCalculate);
			
		estimatingAttributeName = toCalculate;
		
		i = 0;
		
		HashMap<String, ArrayList<String>> calculators = new HashMap<String, ArrayList<String>>();
		
		// Get all of the calculators to use for each attribute
		while (i < attributeDistanceMethods.size()) {
			String calcName = distanceCalculators[attributeDistanceMethods.get(i).getSelectedIndex()];
			
			ArrayList<String> keys = new ArrayList<String>();
			
			if (attributeDistanceMethods.get(i) != null) {
				if ((attributeDistanceMethods.size() - 1 == i) || (attributeDistanceMethods.get(i + 1) != null)) {
					ArrayList<String> names = super.getNamesToDisplay();
					String name = names.get(i);
					keys.add(name);
				}
			}
			
			i = i + 1;
			
			while ((i < attributeDistanceMethods.size()) && (attributeDistanceMethods.get(i) == null)) {
				keys.add(super.getNamesToDisplay().get(i));
				i = i + 1;
			}
			
			if (calculators.containsKey(calcName)) {
				ArrayList<String> names = calculators.get(calcName);
				names.addAll(keys);
			} else {
				calculators.put(calcName, keys);
			}
			
		}
		
		knnComparible.setCalculatorTypes(calculators);
		
		return(toReturn);
	}
	
	/**
	* Calculates the selected value
	* @param toCalculate: The TestingExample to calculate the value for
	* @return: A new attribute containing the calculated value
	**/ 
	public Attribute calculateEstimate(TestingExample toCalculate) {
		// Calculate the value
		Algorithm alg = new Algorithm();
		Double val = alg.computeEstimate(super.getComparibleList(), toCalculate);
		
		if (expectedValue != null) {
			calculatedError = alg.calcError(val, expectedValue);
		}
		
		Attribute toReturn = null;
		
		// Create teh corresponding attribute to store the value
		if (typeToCalculate.equals("Double")) {
			toReturn = new DoubleAttribute(toCalculate.getKeyToCalculate(), val);
		} else if (typeToCalculate.equals("String")) {
			toReturn = new StringAttribute(toCalculate.getKeyToCalculate(), String.valueOf(val));
		} else if (typeToCalculate.equals("Enum")) {
			String key = toCalculate.getKeyToCalculate();
			int i = 0;
			String value = "";
			
			// Determine the enum set to use
			TrainingExample toCheck = (TrainingExample) super.getComparibleList().get(0);
			
			while (i < toCheck.getAttributeNames().size()) {
				if (toCheck.getAttributeNames().get(i).equals(key)){
					value = toCheck.getDisplayValues().get(i).getStringValue();
				}
				i = i + 1;
			}
			
			Enum[] containingSet = Enums.getSetContainingEnum(value);			
			
			// Return the enum attribute
			toReturn = new EnumAttribute(toCalculate.getKeyToCalculate(), (containingSet[(int) Math.round(val)]));
		}
		
		storingHashMap.put(estimatingAttributeName, toReturn);
		super.addTestingExample(new TestingExample(name, storingHashMap, estimatingAttributeName));
		
		knnComparible.setCalculatorTypes(null);
		
		return(toReturn);
		
	}

}
