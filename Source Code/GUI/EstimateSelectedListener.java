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
import javax.swing.JList;
import javax.swing.JOptionPane;
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
 * Action corresponds to the selection of the Estimate (Selected) button
 */

public class EstimateSelectedListener extends Controller implements ActionListener {
	private ArrayList<JRadioButton> toEstimate; // The Radio Buttons to select the attribute to calculate
	private JTextField expectedValueField; // The expected value JTextField
	private String keyToCalculate; // The key to calculate
	private Double expectedValue; // The expected value
	private String typeToCalculate; // The type of value to calcualte
	private JFrame f; // The frame
	private ArrayList<JComboBox> attributeDistanceMethods; // The distance metrics to use for each attribute
	
	private String[] distanceCalculators = {"Manhatten", "Euclidean", "Minkowski (3)", "Minkowski (4)", "Minkowski (5)", "Polar"};

	/**
	* Method called when the Estimate (Selected) button is pressed
	* @param arg0: The ActionEvent that called this method
	**/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Reset all tracking variabls
		toEstimate = new ArrayList<JRadioButton>();
		expectedValueField = null;
		keyToCalculate = "";
		expectedValue = null;
		typeToCalculate = "";
		attributeDistanceMethods = new ArrayList<JComboBox>();
		
		// Obtain the current JList
		JList testingJList = super.getTestingJList();
		// Get the current selected value
		int index = (int) testingJList.getSelectedIndex();
		TestingExample toEstimate = (TestingExample) super.getTestingExample(index);	
		createFrame(toEstimate);
	}
	
	/**
	* Create the frame to get the data from
	* @param toSet: The TestingExample with the attribute to calculate
	**/
	public void createFrame(TestingExample toSet) {
		
		// Create a new frame for getting the attribute values
		// Will be simpler than the one used for getting the attribute names and values
		int width = 600;
		int height = 600;
				
		// Get the display information
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
		int numCols = 2 + maxCompositeLevel;
				
		f = new JFrame("Estimate Selected");
				
		f.setSize(width, height);
						 
		// Use a grid layout
		GridLayout layout = new GridLayout(numRows, numCols);
						
		f.setLayout(layout);
						
		// Print some labels for instructions
		f.add(new JLabel("<html>Attribute Names</html>"));
		
		a = 0;
		
		// Space the labels properly
		while (a < numCols - 2) {
			f.add(new JLabel(""));
			a = a + 1;
		}
		
		f.add(new JLabel("<html>Please Select The Attribute To Compute</html>"));
		f.add(new JLabel("<html>Please Select The Distance Calculator To Use</html>"));
				
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
			while (a < numCols - 2 - compositeLevel.get(i)) {
				f.add(new JLabel(""));
				a = a + 1;
			}
					
			if (!typesToDisplay.get(i).equals("Composite")) {
				// Not a composite attribute, so add a radio button to select as the value to calculate
				JRadioButton temp = new JRadioButton();
						
				// Add the radio button to the button group
				f.add(temp);
				toEstimate.add(temp);
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
		toEstimate.get(0).setSelected(true);
				
		f.add(new JLabel("<html>(Optional) Expected Value:</html>"));
			
		expectedValueField = new JTextField();
		f.add(expectedValueField);
		
		a = 0;
		
		while (a < numCols - 1) {
			f.add(new JLabel(""));
			a = a + 1;
		}
					
		// Add a button for saving the attribute values
		JButton finished = new JButton("Estimate");
		finished.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setValuesForEstimation(toSet);
				estimateValue(toSet);
				f.setVisible(false);
			}			
		});
				
		f.add(finished);
						
		a = 0;
				
		while (a < numCols - 1) {
			f.add(new JLabel(""));
			a = a + 1;
		}
				
		f.setVisible(true);
	}
	
	/**
	* Saves the entered information
	* @param toSet: The TestingExample with the attribute to calculate
	**/
	public void setValuesForEstimation(TestingExample toSet) {
		int i = 0;
		
		// Get the key to calculate by looping through all buttons
		while (i < toSet.getAttributeNames().size()) {
			if (toEstimate.get(i).isSelected()) { // Button is selected
				// Save the key and type to calculate
				keyToCalculate = toSet.getAttributeNames().get(i);
				typeToCalculate = super.getAllAttributeTypes().get(i);
			}
			i = i + 1;
		}
		
		// If the expected value is not empty, save it
		if (!expectedValueField.getText().equals("")) {
			expectedValue = Double.valueOf(expectedValueField.getText());
		}
		
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
		
	}
	
	/**
	* Estimate the attribute value in the given TestingExample
	**/
	public void estimateValue(TestingExample toSet) {
		toSet.setIndexToCalculate(keyToCalculate);
		
		// Calculate the value
		Algorithm alg = new Algorithm();
		Double val = alg.computeEstimate(super.getComparibleList(), toSet);
		
		Double calculatedError = null;
				
		if (expectedValue != null) {
			calculatedError = alg.calcError(val, expectedValue);
		}
				
		Attribute toReturn = null;
				
		// Create the corresponding attribute to store the value
		if (typeToCalculate.equals("Double")) {
			toReturn = new DoubleAttribute(toSet.getKeyToCalculate(), val);
		} else if (typeToCalculate.equals("String")) {
			toReturn = new StringAttribute(toSet.getKeyToCalculate(), String.valueOf(val));
		} else if (typeToCalculate.equals("Enum")) {
			String key = toSet.getKeyToCalculate();
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
			toReturn = new EnumAttribute(toSet.getKeyToCalculate(), (containingSet[(int) Math.round(val)]));
		}
				
		toSet.setValue(toReturn);
		
		if (expectedValue != null) {
			JOptionPane.showMessageDialog(f, "The value was calculated to be: " + toReturn.getStringValue()
			+ "\nThe error was calculated to be: " + calculatedError.toString() + "%");
		} else {
			JOptionPane.showMessageDialog(f, "The value was calculated to be: " + toReturn.getStringValue());
		}
		
		super.updateDisplay();
		
	}

}
