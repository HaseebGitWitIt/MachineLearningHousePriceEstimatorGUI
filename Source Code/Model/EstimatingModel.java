package Model;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

@SuppressWarnings({"rawtypes", "unchecked", "static-access", "resource"})
public class EstimatingModel {
	
	private static ArrayList<knnComparible> objectList; //List containing all objects  and their attributes
	private static String unknownAttribute; //String containing the name of the unknown attribute -> Default is price
	private static DefaultListModel dlm; //Stores all the objects and their attributes
	private ArrayList<knnComparible> newModel = null; //Stores the new model brought in from data
	private ArrayList<knnComparible> newModelTesting = null; //Stores the new model brought in from data
	private static ArrayList<knnComparible> testingExampleList;
	private static DefaultListModel testingExampleDLM;
	String fileName = ""; //Stores the name of the file that we want to save or load
		
	public EstimatingModel() {
		
		objectList = new ArrayList<knnComparible>(); 
		testingExampleList = new ArrayList<knnComparible>();
		unknownAttribute = "Price";
		dlm = new DefaultListModel();
		testingExampleDLM = new DefaultListModel();
		
	}
	
	/**
	* Gets the attributes for an object and stores into TrainingExample
	* @param: Name of object
	* @param: Object attributes
	*/
	public static void addObject(String name, HashMap<String, Attribute> map) {
		
		objectList.add(new TrainingExample(name, map));
		//setDefaultListModel();
		updateJLists();
		
	}
	
	/**
	* Adds training example to the objectList
	* @param: TrainingExample to add to object list
	*/
	public static void addObject(TrainingExample example) {
		
		objectList.add(example);
		//setDefaultListModel();
		updateJLists();
		
	}
	
	/**
	* Removes object
	* @param: The object position we want to remove
	*/
	public static void removeObject(int position) {
		
		objectList.remove(position);
		
		if (objectList.size() == 0) {
			
			dlm.clear();
			
		} else {
			
			//setDefaultListModel();
			updateJLists();
			
		}
		
		
		
	}
	
	/**
	* Edits object
	* @param: The object position we want to remove
	* @param: TrainingExample example we want to replace it with
	*/
	public static void editObject(int position, TrainingExample example) {
		
		removeObject(position);
		addObject(example);
		//setDefaultListModel();
		updateJLists();
		
	}
	
	/**
	* Edits object
	* @param: The object position we want to remove
	* @param: Name of the object
	* @param: Hashmap containing attirbutes of the object
	*/
	public static void editObject(int position, String name,  HashMap<String, Attribute> map) {
		
		removeObject(position);
		addObject(name, map);
		//setDefaultListModel();
		updateJLists();
		
	}
	
	/*
	 * Returns the ArrayList containing all of the objects
	 * @return: The array list object list which contains all the objects
	 */
	public static ArrayList<knnComparible> getObjectList() {
		
		return objectList;
		
	}
	
	/*
	 * Returns the ArrayList containing all of the objects
	 * @return: The array list object list which contains all the objects
	 */
	public static String getUnknownAttribute() {
		
		return unknownAttribute;
		
	}
	
	/*
	 * Sets the unknown attribute
	 * @param: The name of the attribute we want to estimate
	 */
	public static void setUnknownAttribute(String estimatingAttribute) {
		
		unknownAttribute = estimatingAttribute;
		
	}
	
	/*
	 * Clears the objectList to start from scratch
	 */
	public static void clearList() {
		
		objectList.clear();
		testingExampleList.clear();
		updateJLists();
		
	}
	
	/*
	 * Return the size of the object list
	 * @return: Number of objects in objectList
	 */
	public static int getObjectListSize() {
		
		return objectList.size();
		
	}
		
	/*
	 * Returns the DefaultListModel used by the JList to display
	 * @ return: DefaultListModel model to display using JList
	 */
	public DefaultListModel getDefaultListModel() {
		
		return dlm;
		
	}
	
	/*
	 * Add a testingExample to the arrayList containing the others
	 * @param toAdd: Contains the testingExample to add to the arrayLists and DLM
	 */
	public void addTestingExample(TestingExample toAdd) {
		testingExampleList.add(toAdd);
		testingExampleDLM.addElement(toAdd.toString());
	}
	
	/*
	 * Gets the tesingExample arrayList
	 * @return testingExampleList: Return the arrayList containing the testingExamples
	 */
	public ArrayList<knnComparible> getTestingExampleList() {
		return(testingExampleList);
	}
	
	/*
	 * Gets the tesingExample DLM
	 * @return testingExampleDLM: Return the DLM list containing the testingExamples
	 */
	public DefaultListModel getTestingExampleListDLM() {
		return(testingExampleDLM);
	}
	
	/*
	 * Gets a testingExample from the arrayList
	 * @param index: Contains the position of the testingExample we want
	 * @return testingExample: Return the testingExample at the given index
	 */
	public knnComparible getTestingExample(int index) {
		return(testingExampleList.get(index));
	}
	
	
	/*
	 * Update the JLists for the GUI display
	 */
	public static void updateJLists() {
		testingExampleDLM.clear();;
		
		for (knnComparible toAdd : testingExampleList) {
			testingExampleDLM.addElement(toAdd.toString());
		}
		
		dlm.clear();;
		
		for (knnComparible toAdd : objectList) {
			dlm.addElement(toAdd.toString());
		}
	}
	
	/*
	 * Save the current data in the model
	 */
	public void save(String fileName) {
		
		try {
			
			this.fileName = fileName;
			this.fileName += ".xml";
			//Write XML to the buffered writer
			BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName));
			bw.write(toXML());
			bw.close();
			
			
		} catch (IOException e1) {
			
			System.out.println("Failed exporting to XML...");
			e1.printStackTrace();
			
		}
		
		
	}
	/*
	 * Read the XML file to load the previously saved data
	 */
	public void load(String fileName) {
		
		try {
			
			this.fileName = fileName;
			this.fileName += ".xml";
			//Read the XML file for training
			readSAX(new File(this.fileName));
			objectList = newModel;
			testingExampleList = newModelTesting;
									
			updateJLists();
			
		} catch (Exception e) {
			
			System.out.println("Failed to load the previous saved data...");
			//e.printStackTrace();
		}
		
	}

	/*
	 * Read the XML file created
	 * @param: f is the name of the XML file that we are loading
	 */
	public void readSAX(File f) throws SAXException, IOException, ParserConfigurationException {
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser s = spf.newSAXParser();	
		
		DefaultHandler dh = new DefaultHandler() {	
			
			String nextElement = "";
			ArrayList<knnComparible> tempTrainingModel = null;
			ArrayList<knnComparible> tempTestingModel = null;
			knnComparible tempExample = null;
			HashMap<String, Attribute> tempAttribute = null;
			String attributeName = null;
			DoubleAttribute doubVal = null;
			StringAttribute strVal = null;
			EnumAttribute eVal = null;
			String exampleName = null;
			String keyToCalculate = null;
			
			//End elements so create whatever needs to be created
			public void endElement(String uri, String localName, String qName) {
				
				if (qName.equals("Model")) {
					
					newModel = tempTrainingModel;
					newModelTesting = tempTestingModel;
					
					
				} else if (qName.equals("TrainingExample")) {
					
					tempExample = new TrainingExample(exampleName, tempAttribute);
					tempTrainingModel.add(tempExample);
					
				} else if (qName.equals("StringValue")) {
					
					strVal.setAttributeName(attributeName);
					tempAttribute.put(strVal.getAttributeName(), strVal);
					
				} else if (qName.equals("DoubleValue")) {
					
					doubVal.setAttributeName(attributeName);
					tempAttribute.put(doubVal.getAttributeName(), doubVal);
					
				} else if (qName.equals("EnumValue")) {
					
					eVal.setAttributeName(attributeName);
					tempAttribute.put(eVal.getAttributeName(), eVal);
					
				} else if (qName.equals("TestingExample")) {
					tempExample = new TestingExample(exampleName, tempAttribute, keyToCalculate);
					tempTestingModel.add(tempExample);
				}
			}
			
			//Start element so initialize whatever needs to be initialized
			public void startElement(String u, String ln, String qName, Attributes a) {
				
				if (qName.equals("Model")) {
					
					tempTrainingModel = new ArrayList<knnComparible>();
					tempTestingModel = new ArrayList<knnComparible>();
					
				} else if (qName.equals("TrainingExample")) {
					
					tempExample = new TrainingExample("Temp");
					tempAttribute = new HashMap<String, Attribute>();
					
				} else if (qName.equals("TestingExample")) {
					tempExample = new TestingExample("Temp", null);
					tempAttribute = new HashMap<String, Attribute>();
					
				} else if (qName.equals("Name")) {
					
					nextElement = qName;
					
				} else if (qName.equals("AttributeName")) {
					
					nextElement = qName;
					
				} else if (qName.equals("StringValue")) {
					
					strVal = new StringAttribute(null, null);
					nextElement = qName;
					
				} else if (qName.equals("DoubleValue")) {
					
					doubVal = new DoubleAttribute(null, 0);
					nextElement = qName;
					
				} else if (qName.equals("EnumValue")) {
					
					eVal = new EnumAttribute(null, null);
					nextElement = qName;
					
				} else if (qName.equals("KeyToCalculate")) {
					nextElement = qName;
				}
			}
			
			//Get the values from the tags that are needed
			public void characters(char[] ch, int start, int length) {
				
				String val = new String(ch, start, length);
				
				if (nextElement.equals("Name")) {
					
					exampleName = val;
					
				} else if (nextElement.equals("AttributeName")) {
					
					attributeName = val;
					
				} else if (nextElement.equals("StringValue")) {
					
					strVal.setValue(val);
				
				} else if (nextElement.equals("DoubleValue")) {
					if (val.equals("NULL")) {
						doubVal.setValue(null);
					} else {
						doubVal.setValue(Double.valueOf(val));
					}	
				} else if (nextElement.equals("EnumValue")) {
					
					eVal.setValue(Enums.valueOf(val));
				
				} else if (nextElement.equals("KeyToCalculate")) {
					keyToCalculate = val;
				}
			}
		};
		s.parse(f, dh);
		
	}
	
	/*
	 * Takes the objectList and creates an XML string used to save the data of the current model
	 */
	public String toXML() {
		
		String openAttributeType = null;
		String closeAttributeType = null;
		String temp = "";
		
		temp += "<Model>";
		
		//For every training example in the model
		for (knnComparible object: objectList) {
			
			
			temp += "<TrainingExample>";
			temp += "<Name>" + object.getName() + "</Name>";
			for (int a = 0; a < object.getDisplayValues().size(); a++) {
				
				//Get the correct attirubte type for the tag
				if (object.getDisplayValues().get(a) instanceof DoubleAttribute) {
					
					openAttributeType = "<DoubleValue>";
					closeAttributeType = "</DoubleValue>";
							
				} else if (object.getDisplayValues().get(a) instanceof StringAttribute) {
					
					openAttributeType = "<StringValue>";
					closeAttributeType = "</StringValue>";
					
				} else if (object.getDisplayValues().get(a) instanceof EnumAttribute) {
					
					openAttributeType = "<EnumValue>";
					closeAttributeType = "</EnumValue>";
					
				} else {
					
					System.out.println("THIS TYPE DOES NOT EXIST");
					
				}
			
				//Add the attribute names and values
				temp += "<Attribute>";
				temp += "<AttributeName>" + object.getDisplayNames().get(a)  + "</AttributeName>";
				temp += openAttributeType + object.getDisplayValues().get(a).getStringValue() + closeAttributeType + "";
				temp += "</Attribute>";
				
			}
			temp += "</TrainingExample>";
			
		}
		
		for (knnComparible object : testingExampleList) {
			temp += "<TestingExample>";
			temp += "<Name>" + object.getName() + "</Name>";
			for (int a = 0; a < object.getDisplayValues().size(); a++) {
				
				//Get the correct attirubte type for the tag
				if (object.getDisplayValues().get(a) instanceof DoubleAttribute) {
					
					openAttributeType = "<DoubleValue>";
					closeAttributeType = "</DoubleValue>";
							
				} else if (object.getDisplayValues().get(a) instanceof StringAttribute) {
					
					openAttributeType = "<StringValue>";
					closeAttributeType = "</StringValue>";
					
				} else if (object.getDisplayValues().get(a) instanceof EnumAttribute) {
					
					openAttributeType = "<EnumValue>";
					closeAttributeType = "</EnumValue>";
					
				} else {
					
					System.out.println("THIS TYPE DOES NOT EXIST");
					
				}
			
				//Add the attribute names and values
				temp += "<Attribute>";
				temp += "<AttributeName>" + object.getDisplayNames().get(a)  + "</AttributeName>";
				temp += openAttributeType + object.getDisplayValues().get(a).getStringValue() + closeAttributeType + "";
				temp += "</Attribute>";
				
			}
			
			temp += "<KeyToCalculate>";
			temp += ((TestingExample) object).getKeyToCalculate();
			temp += "</KeyToCalculate>";
			
			temp += "</TestingExample>";
		}
		
		temp += "</Model>";
		
		return temp;
		
	}
	
	
	
	

}
