package GUI;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
* Handles the Estimate (Set) button being pressed
**/
public class EstimateSetListener extends Controller implements ActionListener {
	private ArrayList<JRadioButton> toEstimate; // The radio buttons used to choose the attribute to calculate
	private ArrayList<JTextField> expectedValueFields; // The JTextFields with the expected values
	private String keyToCalculate; // The key to calculate
	private ArrayList<Double> expectedValues; // The expected values for each TestingExample
	private String typeToCalculate; // The type of attribute to calculate
	private JFrame f; // The frame
	private ArrayList<knnComparible> allTestingExamples; // A list of every TestingExample in the system
	private ArrayList<JComboBox> attributeDistanceMethods; // The drop down menus used to choose the distance metric for every attribute
	
	private String[] distanceCalculators = {"Manhatten", "Euclidean", "Minkowski (3)", "Minkowski (4)", "Minkowski (5)", "Polar"};


	/**
	* Handles the Estimate (Set) button being pressed
	**/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Reset all of the local variable
		toEstimate = new ArrayList<JRadioButton>();
		expectedValueFields = new ArrayList<JTextField>();
		keyToCalculate = "";
		expectedValues = new ArrayList<Double>();
		typeToCalculate = "";
		allTestingExamples = new ArrayList<knnComparible>();
		attributeDistanceMethods = new ArrayList<JComboBox>();
		
		allTestingExamples = super.getTestingExampleList(); // Get the TestingExamples from the super class
		
		// Get the data for estimating the TestingExample sets
		TestingExample toEstimate = (TestingExample) super.getTestingExample(0);	
		createFrame(toEstimate);
	}
	
	/**
	* Creates a frame to get all of the information for calculating the set
	* @param toSet: An example TestingExample for displaying purposes
	**/
	public void createFrame(TestingExample toSet) {
		
		// Create a new frame for getting the attribute values
		// Will be simpler than the one used for getting the attribute names and values
		int width = 600;
		int height = 600;
				
		// The data for displaying information
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
						
		int numRows = attributeNames.size() + 3 + allTestingExamples.size();
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
				
		f.add(new JLabel("<html>Testing Example"));
		f.add(new JLabel("<html>(Optional) Expected Value:</html>"));
		
		a = 0;
		
		while (a < numCols - 1) {
			f.add(new JLabel(""));
			a = a + 1;
		}
		
		i = 0;
		
		while (i < allTestingExamples.size()) {
			f.add(new JLabel(allTestingExamples.get(i).getName()));
			JTextField expectedValueField = new JTextField();
			expectedValueFields.add(expectedValueField);
			f.add(expectedValueField);
			
			a = 0;
			
			while (a < numCols - 1) {
				f.add(new JLabel(""));
				a = a + 1;
			}
			
			i = i + 1;
		}
							
		// Add a button for saving the attribute values
		JButton finished = new JButton("Estimate");
		finished.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setValuesForEstimation(toSet);
				estimateValues();
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
	* Saves all of the entered information
	* @param toSet: A TestingExample for displaying purposes
	**/
	public void setValuesForEstimation(TestingExample toSet) {
		int i = 0;
		
		// Get the key and type to calculate by looping through
		// all of teh radio buttons
		while (i < toSet.getAttributeNames().size()) {
			if (toEstimate.get(i).isSelected()) { // Found the selected radio button
				// Save the key and type to calculate
				keyToCalculate = toSet.getAttributeNames().get(i);
				typeToCalculate = super.getAllAttributeTypes().get(i);
			}
			i = i + 1;
		}
		
		// Loop through all of the JTextFields for the expected values
		// If the JTextField is not empty, save it, otherwise save null
		for (JTextField field : expectedValueFields) {
			if (!field.getText().equals("")) {
				expectedValues.add(Double.valueOf(field.getText()));
			} else {
				expectedValues.add(null);
			}
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
	* Estimate the selected attribute in every TestingExample
	**/
	public void estimateValues() {		
		double totalAccuracy = 0; // The total accuracy of all calculations
		ArrayList<String> names = new ArrayList<String>(); // The names of each TestingExample
		ArrayList<Double> calculatedAccuracies = new ArrayList<Double>(); // The calculated accuracy for each TestingExample
		ArrayList<Double> calculatedValues = new ArrayList<Double>(); // The calculated value for each TestingExample
		
		TestingExample toSet = null;
		
		int i = 0;
		
		// Calculate the attribute in each TestingExample
		while (i < allTestingExamples.size()) {
			toSet = (TestingExample) allTestingExamples.get(i); // Get the current TestingExample
			
			// Set the key to calculate
			toSet.setIndexToCalculate(keyToCalculate);
			
			names.add(toSet.getName()); // add the name to the list
			
			// Calculate the value
			Algorithm alg = new Algorithm();
			Double val = alg.computeEstimate(super.getComparibleList(), toSet);
			
			calculatedValues.add(val);
			
			Double calculatedError = null;
					
			// If the expected value was given, save the accuracy and add it to the total
			if (expectedValues.get(i) != null) {
				calculatedError = alg.calcError(val, expectedValues.get(i));
				totalAccuracy = totalAccuracy + calculatedError;
				calculatedAccuracies.add(calculatedError);
			} else {
				calculatedAccuracies.add(null);
			}
					
			Attribute toReturn = null;
					
			// Create the corresponding attribute to store the value
			if (typeToCalculate.equals("Double")) {
				toReturn = new DoubleAttribute(toSet.getKeyToCalculate(), val);
			} else if (typeToCalculate.equals("String")) {
				toReturn = new StringAttribute(toSet.getKeyToCalculate(), String.valueOf(val));
			} else if (typeToCalculate.equals("Enum")) {
				String key = toSet.getKeyToCalculate();
				int a = 0;
				String value = "";
						
				// Determine the enum set to use
				TrainingExample toCheck = (TrainingExample) super.getComparibleList().get(0);
						
				while (a < toCheck.getAttributeNames().size()) {
					if (toCheck.getAttributeNames().get(a).equals(key)){
						value = toCheck.getDisplayValues().get(a).getStringValue();
					}
					a = a + 1;
				}
						
				Enum[] containingSet = Enums.getSetContainingEnum(value);			
						
				// Return the enum attribute
				toReturn = new EnumAttribute(toSet.getKeyToCalculate(), (containingSet[(int) Math.round(val)]));
			}
					
			toSet.setValue(toReturn);
			
			i = i + 1;
		}
		
		String toPrint = "";
		
		i = 0;
		
		int divisor = 0;
		
		// Output the calculation information for each TestingExample to the user
		while (i < calculatedAccuracies.size()) {
			toPrint += "Name: " + names.get(i);			
			
			// If the expected value was given output "unavailable"
			// Otherwise output the data
			if (calculatedAccuracies.get(i) == null) { 
				toPrint += " Expected Value: Unavailable" ;
				toPrint += " Calculated Value: Unavailable";
				toPrint += " Calcualted Error: Unavailable.\n";
			} else {
				toPrint += " Expected Value: " + expectedValues.get(i) ;
				toPrint += " Calculated Value: " + calculatedValues.get(i);
				toPrint += " Calculated Error: " + calculatedAccuracies.get(i);
				toPrint += "%.\n";
				divisor += 1;
			}
			
			i = i + 1;
		}
		
		totalAccuracy = totalAccuracy / divisor; // Get the total accuracy
		
		// Output the total accuracy
		toPrint += "The total accuracy of the set was found to be: ";
		
		if (divisor > 0) {
			 toPrint += totalAccuracy + "%.";
		} else {
			toPrint += "unavailable.";
		}
		
		JOptionPane.showMessageDialog(f, toPrint);
		
		super.updateDisplay();
		
	}

}
