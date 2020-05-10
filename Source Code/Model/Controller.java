package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import GUI.WindowsGUI;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 * 
 * Controller abstract class
 * Abstract class for all of the different listener classes in the GUI
 */
@SuppressWarnings({"static-access", "rawtypes", "unchecked"})
public abstract class Controller {
	// The main parts of the GUI
	private static JFrame frame;
	private static JPanel pane;
	private static JMenuItem edit, estimateSet, estimateSelected;
	
	// The names for the various attributes
	private static ArrayList<String> stringAttributeNames;
	private static ArrayList<String> enumAttributeNames;
	private static ArrayList<String> doubleAttributeNames;
	private static ArrayList<String> allAttributes;
	private static ArrayList<String> allAttributeTypes;
	private static ArrayList<String> namesToDisplay;
	private static ArrayList<String> typesToDisplay;
	private static ArrayList<Integer> compositeLevel;

	// HashMap for the attributes
	public static HashMap<String, Attribute> attributeMap = new HashMap<String, Attribute>();
	
	// Create the GUI, model and the lists needed to display the examples
	private static WindowsGUI gui;
	private static EstimatingModel model;
	private static JList trainingExampleList;
	private static JList testingExampleList;
	private static DefaultListModel trainingModel;
	private static DefaultListModel testingModel;
	
	/**
	* Constructor
	* Creates a new Controller
	*/
	public Controller() {
		// Initialize variables
		trainingModel = new DefaultListModel<Attribute>();
		stringAttributeNames = new ArrayList<String>();
		enumAttributeNames = new ArrayList<String>();
		doubleAttributeNames = new ArrayList<String>();
		attributeMap = new HashMap<String, Attribute>();
		allAttributeTypes = new ArrayList<String>();
		allAttributes = new ArrayList<String>();
		
		trainingExampleList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (trainingExampleList.getSelectedIndex() >= 0) {
					edit.setEnabled(true);
				}
			}
		});
		
		testingExampleList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (testingExampleList.getSelectedIndex() >= 0) {
					estimateSelected.setEnabled(true);
				}
			}
		});
		
	}
	
	/**
	* The main class for the GUI
	* @param args
	*/
	public static void main(String[] args){
		
		model = new EstimatingModel();
		trainingModel = model.getDefaultListModel();
		testingModel = model.getTestingExampleListDLM();
		trainingExampleList = new JList(trainingModel);
		testingExampleList = new JList(testingModel);
		gui = new WindowsGUI(trainingExampleList, testingExampleList);		
	}
	

	/**
	* Returns the arrayList of string attributes
	* @return: ArrayList : arrayList of string attributes 
	*/
	public ArrayList<String> getStringAttributeNames() {
		return stringAttributeNames;
	} 
	
	/**
	* Returns the Array List of objects entered by the user
	* @return: ArrayList<knnComparible> arrayList of all entered TrainingExampls
	*/
	public ArrayList<knnComparible> getComparibleList(){
		return(model.getObjectList());
	}
	
	/**
	* Returns the list of TestingExamples
	* @return: ArrayList containing all of the TestingExamples
	**/
	public ArrayList<knnComparible> getTestingExampleList() {
		return(model.getTestingExampleList());
	}

	/**
	* Adds a string attribute name to teh storing ArrayList
	* @param stringAttributeNames: The new name to add
	*/ 
	public void addStringAttributeNames(String stringAttributeNames) {
		this.stringAttributeNames.add(stringAttributeNames);
		allAttributes.add(stringAttributeNames);
		allAttributeTypes.add("String");
	}

	/**
	 Returns the arrayList of enum attributes
	* @return: ArrayList : arrayList of enum attributes 
	*/
	public ArrayList<String> getEnumAttributeNames() {
		return enumAttributeNames;
	}
	
	/**
	* Adds an Enum Attribute name to the list.
	* @param enumAttributeName: The name to add to the list.
	**/
	public void addEnumAttributeName(String enumAttributeName) {
		enumAttributeNames.add(enumAttributeName);
		allAttributes.add(enumAttributeName);
		allAttributeTypes.add("Enum");
	}

	/**
	 Returns the arrayList of double attributes
	* @return: ArrayList : arrayList of double attributes 
	*/
	public ArrayList<String> getDoubleAttributeNames() {
		return doubleAttributeNames;
	}
	
	/**
	* Adds the name of a DoubleAttribute to the list
	* @param doubleAttributeName: The name to add to the list
	**/
	public void addDoubleAttributeName(String doubleAttributeName) {
		doubleAttributeNames.add(doubleAttributeName);
		allAttributes.add(doubleAttributeName);
		allAttributeTypes.add("Double");
	}
	
	/**
	* Returns a list containing all of the attribute types
	* @return: ArrayList containing all of the attribute types for every attribute
	**/
	public ArrayList<String> getAllAttributeTypes() {
		return(allAttributeTypes);
	}

	/**
	 Returns the HashMap of attributes
	* @return: HashMap : HashMap of attributes 
	*/
	public HashMap<String, Attribute> getAttributeMap() {
		return attributeMap;
	}
	
	/**
	* Adds a given attribute to the attribute map
	* @param name: The name of the attribute to add
	* @param toAdd: The Attribute to add to the map
	**/
	public void addAttributeToAttributeMap(String name, Attribute toAdd) {
		attributeMap.put(name, toAdd);
	}

	/**
	 * Sets the frame of the GUI to the new frame
	 * @param : frame : new frame of the view
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
		
	}
	
	/**
	 Returns the frame of the view
	* @return: frame of the GUI
	*/
	public JFrame getFrame() {
		return this.frame;
		
	}
	
	/**
	* Sets the panel to a new given panel
	* @param panel: The new JPanel for the GUI
	*/
	public void setPanel(JPanel panel) {
		this.pane = panel;
	}
	
	/**
	* Sets the edit menu item to the given menu item
	* @param edit: The new edit menu item
	*/
	public void setEdit(JMenuItem edit) {
		this.edit = edit;
	}
	
	/**
	* Sets the estimateSet variable to the given JMenuItem
	* @param estimateSet: The JMenuItem to set the variable to
	**/
	public void setEstimateSet(JMenuItem estimateSet) {
		this.estimateSet = estimateSet;
	}
	
	/**
	* Sets the estimateSelected variable to the given JMenuItem
	* @param estimateSelected: The JMenuItem to set tehe variable to
	**/
	public void setEstimateSelected(JMenuItem estimateSelected) {
		this.estimateSelected = estimateSelected;
	}
	
	/**
	* Returns the panel of the view
	* @return: panel : panel of the GUI
	*/
	public JPanel getPanel() {
		return this.pane;
	}

	/**
	* Returns the DefaultListModel attribute list
	* @return: The DefaultListModel of all entered items
	*/
	public DefaultListModel<Attribute> getAttributeList() {
		return trainingModel;
	}
	
	/**
	 * Adds the given attribute to the attributeList
	* @param attribute : Attribute to be added 
	*/
	public void addAttributeList(Attribute attribute) {
		this.trainingModel.addElement(attribute);
	}
	
	/**
	 Returns the JList of attributes
	* @return: newList :  JList
	*/
	public JList getAttributeJList() {
		return trainingExampleList;
	}

	/**
	* Clears all storing lists
	*/
	public void emptyList() {
		trainingModel.clear();
		testingModel.clear();
		stringAttributeNames.clear();
		enumAttributeNames.clear();
		doubleAttributeNames.clear();
		attributeMap.clear();
		model.clearList();
		estimateSet.setEnabled(false);
		estimateSelected.setEnabled(false);
		edit.setEnabled(false);
	
	}
	
	/**
	* Returns the allAttributes array list
	* @return: An array list containing all attribute names
	*/
	public ArrayList<String> getAllAttributes(){
		return allAttributes;
	}
	
	/**
	* Returns true if the attribute exists, false if it does not
	* @param: Attribute name
	* @param: All attributes
	* @return: True if attribute exists, false if not
	*/
	public static boolean isItAnAttribute(String unknownAttribute, ArrayList<String> allAttributes) {
		allAttributes = new ArrayList<String>();
		
		for (String s : stringAttributeNames) {
			allAttributes.add(s);
		}
		
		for (String s : enumAttributeNames) {
			allAttributes.add(s);
		}
		
		for (String s : doubleAttributeNames) {
			allAttributes.add(s);
		}
		
		
		for(String s: allAttributes) {	
			if(s.equalsIgnoreCase(unknownAttribute)) {	
				return true;	
			}
		}
	
		return false;
				
	}
	
	/**
	* Returns string containing all attributes
	* @return: String containing all attributes
	*/
	public static String getAllNames(ArrayList<String> names) {
		
		String temp = "";
		
		for(String s: names) {
			
			temp += s;
			temp += " ";
			
		}
		
		return temp;
		
	}
	
	/**
	 * Convert default list model of attributeList to arraylist 
	 * @return objectList : ArrayList<knnComparible>
	 */
	public ArrayList<knnComparible> dlmToArrayList(){
		ArrayList<knnComparible> objectList = new ArrayList<knnComparible>();
		for (int i = 0; i < trainingModel.size(); i++) {
			objectList.add((knnComparible) trainingModel.getElementAt(i));
		}
		
		return objectList;
	}
	
	/**
	* Returns the list of double attribute names
	* @return: ArrayList containing all double attribute names
	*/
	public ArrayList<Double> getDoubleList(){
		ArrayList<Double> doubleList= new ArrayList<Double>();
		for(int i = 0; i<doubleAttributeNames.size(); i++) {
			String key = doubleAttributeNames.get(i);
			DoubleAttribute temp = (DoubleAttribute)attributeMap.get(key);
			doubleList.add(temp.getValue());
		}
		return doubleList;
	}
	
	/**
	* Returns the list of double attribute names
	* @return: ArrayList containing all double attribute names
	*/
	public ArrayList<Attribute> getDoubleAttributeList(){
		ArrayList<Attribute> doubleAttributeList= new ArrayList<Attribute>();
		for(int i = 0; i<doubleAttributeNames.size(); i++) {
			String key = doubleAttributeNames.get(i);
			doubleAttributeList.add((DoubleAttribute)attributeMap.get(key));
		}
		return doubleAttributeList;
	}
	
	/**
	* Creates a hash map from teh static doubleAttribute ArrayList
	* @return: HashMap<String, Attribute> containing all double attribute values and their names
	*/
	public HashMap<String, Attribute> createDoubleHashMap(){
		HashMap<String, Attribute> newMap = new HashMap<String, Attribute>();
		for(int i = 0; i<doubleAttributeNames.size(); i++) {
			String key = doubleAttributeNames.get(i);
			DoubleAttribute temp = (DoubleAttribute)attributeMap.get(key);
			newMap.put(key, temp);
		}
		return newMap;
	}
	
	/**
	* Adds a training example with the given name and map from the createDoubleHashMap function
	* @param name: The name of the new TrainingExample
	*/
	public void addTrainingExample(String name){
		model.addObject(new TrainingExample(name, createDoubleHashMap()));
	}
	
	/**
	* Adds a training example with the given name and HashMap
	* @param name: The name of the TrainingExample
	* @param toAdd: the HashMap to set the TrainingExample too
	*/
	public void addTrainingExample(String name, HashMap<String, Attribute> toAdd) {
		model.addObject(new TrainingExample(name, toAdd));
	}
	
	/**
	* Adds a given TestingExample to the model
	* @param toAdd: The TestingExample to add
	**/
	public void addTestingExample(TestingExample toAdd) {
		model.addTestingExample(toAdd);
		estimateSet.setEnabled(true);
	}
	
	/**
	* Returns the TestingExample at the given index from the model
	* @param index: The index of the TestingExample to return
	**/
	public knnComparible getTestingExample(int index) {
		return(model.getTestingExample(index));
	}
	
	/**
	* Returns the JList containing all of the TestingExamples
	* @return: The JList containing all of the TestingExamples
	**/
	public JList getTestingJList() {
		return(testingExampleList);
	}
	
	/**
	* Remove the selected example to replace it with a new one
	* @param removePositon: Gives the position of the example to remove from the list
	*/
	public void replace(int removePosition) {
		
		model.removeObject(removePosition);
		gui.getAddListener().getAttributes();
		edit.setEnabled(false);
		
	}
	
	/**
	* Returns the list of names to display
	* @return: ArrayList containing the names to display
	**/ 
	public ArrayList<String> getNamesToDisplay(){
		return(namesToDisplay);
	}
	
	/**
	* Sets the names to display
	* @param newSetOfNames: The new list of names to display
	**/
	public void setNamesToDisplay(ArrayList<String> newSetOfNames) {
		namesToDisplay = newSetOfNames;
	}
	
	/**
	* Returns a list of types to display
	* @return: ArrayList containing the types to display
	**/
	public ArrayList<String> getTypesToDisplay(){
		return(typesToDisplay);
	}
	
	/**
	* Sets the list of types to display
	* @param: The new list of names to display
	**/
	public void setTypesToDisplay(ArrayList<String> newSetOfTypes) {
		typesToDisplay = newSetOfTypes;
	}
	
	/**
	* Returns the list of composite levels
	* @return: ArrayList containing the composite levels for each attribute
	**/
	public ArrayList<Integer> getCompositeLevels(){
		return(compositeLevel);
	}
	
	/**
	* Sets the composite levels for each attribute
	* @param newSetOfCompositeLevels: The new composite levels for every attribute
	**/ 
	public void setCompositeLevel(ArrayList<Integer> newSetOfCompositeLevels) {
		compositeLevel = newSetOfCompositeLevels;
	}
	
	/**
	* Swaps the firstRun variable in the AddListener class
	* Used to get new attribute names after clear is pressed
	**/
	public void changeFirstRun() {
		
		gui.getAddListener().changeFirstRun();
		
	}
	
	/**
	* Saves the current state of the program to teh given filename
	* @param fileName: The name of the file to save to
	**/
	public void saveData(String fileName) {
		if (!trainingModel.isEmpty()) {
		
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)); // Create the bufferedWriter
				bw.write(toXML()); // Write the XML of the program to the file
				bw.close(); // Close the BufferedWriter
				
			} catch (IOException e1) {
				
				System.out.println("Failed exporting to XML...");
				e1.printStackTrace();
				
			}
			
			model.save(fileName); // Save the XML of the model as well
		} else {
			JOptionPane.showMessageDialog(frame, "There is nothing to save. Please enter at least testing.");
		}
		
	}
	
	/**
	* Loads the program state from the given XML file
	* @param fileName: The XML file to load from
	**/
	public void loadData(String fileName) {
		
		try {
			readSAX(new File(fileName)); // Read the XML file
			changeFirstRun(); // Toggle the firstRun variable
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Failed to load the data. File not found.");
			//e.printStackTrace();
		}
		
		model.load(fileName); // Load the saved model
		
		// Disable the Edit and Estimate (Selected) buttons
		estimateSelected.setEnabled(false); 
		edit.setEnabled(false);
		
		// If there is more than on TestingExample, enable the Estimate (Set) button
		// Otherwise, disable the button
		if (!(model.getTestingExampleList().isEmpty())) {
			estimateSet.setEnabled(true);
		} else {
			estimateSet.setEnabled(false);
		}
		
		this.changeFirstRun();
	}
	
	/**
	* Update the JLists in the model
	**/
	public void updateDisplay() {
		model.updateJLists();
	}
	
	/**
	* Sets the frame to given visibility value
	* @param val: The boolean to set the visibility to
	**/
	public void setFrameVisibility(boolean val) {
		frame.setVisible(val);
	}
	
	/**
	* Converts the Controller to XML
	* @return: The String representation containing the XML representation of the Controller
	**/
	public String toXML() {
		String toReturn = "";
		
		// Add the opening tags
		toReturn += "<Metadata>";
		
		toReturn += "<StringAttributeNames>";
		
		// Save all string attribute names
		for (String name : stringAttributeNames) {
			toReturn += "<Name>";
			toReturn += name;
			toReturn += "</Name>";
		}
		
		toReturn += "</StringAttributeNames>";
		
		toReturn += "<EnumAttributeNames>";
		
		// Save all enum attribute names
		for (String name : enumAttributeNames) {
			toReturn += "<Name>";
			toReturn += name;
			toReturn += "</Name>";
		}
		
		toReturn += "</EnumAttributeNames>";
		
		toReturn += "<DoubleAttributeNames>";
		
		// Save all double attribute names
		for (String name : doubleAttributeNames) {
			toReturn += "<Name>";
			toReturn += name;
			toReturn += "</Name>";
		}
		
		toReturn += "</DoubleAttributeNames>";
		
		toReturn += "<NamesToDisplay>";
		
		// Save all attribute names to display
		for (String name : namesToDisplay) {
			toReturn += "<Name>";
			toReturn += name;
			toReturn += "</Name>";
		}
		
		toReturn += "</NamesToDisplay>";
		
		toReturn += "<TypesToDisplay>";
		
		// Save all attribute types to display
		for (String name : typesToDisplay) {
			toReturn += "<Name>";
			toReturn += name;
			toReturn += "</Name>";
		}
		
		toReturn += "</TypesToDisplay>";
		
		toReturn += "<CompositeLevel>";
		
		// Save all composite levels for displaying purposes
		for (Integer val :  compositeLevel) {
			toReturn += "<Name>";
			toReturn += val.toString();
			toReturn += "</Name>";
		}
		
		// Add the closing tags to the XML
		toReturn += "</CompositeLevel>";
		
		toReturn += "</Metadata>";
		
		return(toReturn);
	}
	
	/**
	* Read the XML file from the given file
	* @param f: The File to read the XML from
	**/ 
	public void readSAX(File f) throws SAXException, IOException, ParserConfigurationException {
		/**
		// Check if the file exists
		if (f.exists()) {
			
			return;
		}
		**/
		// Create the SAX parser
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser s = spf.newSAXParser();	
		
		// Add the default handler
		DefaultHandler dh = new DefaultHandler() {	
			String toSet = "";
			
			//End elements so create whatever needs to be created
			public void endElement(String uri, String localName, String qName) {
				System.out.println("END: " + qName);
			}
			
			//Start element so initialize whatever needs to be initialized
			public void startElement(String u, String ln, String qName, Attributes a) {
				System.out.println("START: " + qName);
				if (qName.equals("Metadata")) {
					stringAttributeNames.clear();
					enumAttributeNames.clear();
					doubleAttributeNames.clear();
					namesToDisplay.clear();
					typesToDisplay.clear();
					compositeLevel.clear();
					allAttributes.clear();
					allAttributeTypes.clear();
				} else if (qName.equals("StringAttributeNames")) {
					toSet = qName;
				} else if (qName.equals("DoubleAttributeNames")) {
					toSet = qName;
				} else if (qName.equals("EnumAttributeNames")) {
					toSet = qName;
				} else if (qName.equals("NamesToDisplay")) {
					toSet = qName;
				} else if (qName.equals("TypesToDisplay")) {
					toSet = qName;
				} else if (qName.equals("CompositeLevel")) {
					toSet = qName;
				}
				
				
			}
			
			//Get the values from the tags that are needed
			public void characters(char[] ch, int start, int length) {
				
				String val = new String(ch, start, length);
				
				System.out.println("CHAR: " + val);
				
				if (toSet.equals("StringAttributeNames")) {
					stringAttributeNames.add(val);
					allAttributes.add(val);
				} else if (toSet.equals("EnumAttributeNames")) {
					enumAttributeNames.add(val);
					allAttributes.add(val);
				} else if (toSet.equals("DoubleAttributeNames")) {
					doubleAttributeNames.add(val);
					allAttributes.add(val);
				} else if (toSet.equals("NamesToDisplay")) {
					namesToDisplay.add(val);
				} else if (toSet.equals("TypesToDisplay")) {
					typesToDisplay.add(val);
					allAttributeTypes.add(val);
				} else if (toSet.equals("CompositeLevel")) {
					compositeLevel.add(Integer.valueOf(val));
				}
 			}
		};
		s.parse(f, dh);
		
	}
	
	
}
