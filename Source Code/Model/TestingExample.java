package Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 *
 * TestingExample Class
 * - Provides knnComparibles for testing purposes.
 * - The same as TrainingExample, except they also have a key to calculate
 * 
 */
public class TestingExample extends knnComparible {
	private String keyToCalculate;

	/**
	* Constructor
	* Makes a new TestingExample
	* @param name: The name of the TestingExample
	* @param keyToCalculate: The key of the element to calculate
	*/
	public TestingExample(String name, String keyToCalculate) {
		super(name);
		this.keyToCalculate = keyToCalculate;
		// TODO Auto-generated constructor stub
	}
	
	/**
	* Constructor
	* Makes a new TestingExample with given attributes
	* Makes two ArrayList, one with the attribute names in alphabetic order and the other with the
	* attribute values in the same order
	* @param name: The name of the TestingExample
	* @param attributes: The attributes for the TestingExample
	* @param keyToCalculate: The key of the element to calculate
	*/
	public TestingExample(String name, HashMap<String, Attribute> attributes, String keyToCalculate) {
		super(name);
		this.keyToCalculate = keyToCalculate;
		
		// The two ArrayLists for the attribute names and attribute values
		ArrayList<String> attributeKeys = new ArrayList<String>();
		ArrayList<Double> attributeValues = new ArrayList<Double>();
		ArrayList<Attribute> displayAttributes = new ArrayList<Attribute>();
		
		Iterator iter = attributes.keySet().iterator();
		
		// Get all of the attribute names
		while (iter.hasNext()) {
			String key = (String) iter.next();
			if (!key.equals("Name")) {
				attributeKeys.add(key);
			}
		}
		
		// Sort the attribute names
		Collections.sort(attributeKeys);
		
		// Get all of the attribute values from the alphabetic order
		for (String key : attributeKeys) {
			if (!key.equals("Name")) {
				attributeValues.add(attributes.get(key).getValue());
				displayAttributes.add(attributes.get(key));
			}
		}
		
		// Set the names and values of the super class
		super.setAttributeNames(attributeKeys);
		super.setStandardizableValues(attributeValues);
		
		super.setDisplayNames(attributeKeys);
		super.setDisplayValues(displayAttributes);
		
	}
	
	/**
	* Sets the key to calculate
	* @param newKeyToCalculate: The new key to calculate
	*/
	public void setIndexToCalculate(String newKeyToCalculate) {
		keyToCalculate = newKeyToCalculate;
	}
	
	/**
	* Returns the key to calculate
	* @return: String containing the key to calculate
	*/
	public String getKeyToCalculate() {
		return(keyToCalculate);
	}

}
