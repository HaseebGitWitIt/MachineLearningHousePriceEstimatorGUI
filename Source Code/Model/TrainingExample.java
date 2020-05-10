package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 *
 * TrainingExample Class
 * - Provides a knnComparible for training the algorithm.
 * - Contains values for every key
 * 
 */
@SuppressWarnings("serial")
public class TrainingExample extends knnComparible implements Serializable{

	/**
	* Constructor
	* Makes a new TrainingExample with the given name
	* @param name: The name for the training example
	*/
	public TrainingExample(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	/**
	* Constructor
	* Makes a new trainingExample with the given name and attributes
	* @param name: The name for the training example
	* @param attributes: The attributes of the training example
	*/
	public TrainingExample(String name, HashMap<String, Attribute> attributes) {
		super(name);
		
		// The two ArrayLists for the attribute names and attribute values
		ArrayList<String> attributeKeys = new ArrayList<String>();
		ArrayList<Double> attributeValues = new ArrayList<Double>();
		ArrayList<Attribute> displayAttributes = new ArrayList<Attribute>();
		
		Iterator iter = attributes.keySet().iterator();
		
		// Loop through the keys and add all keys to the attribute name ArrayList
		while (iter.hasNext()) {
			String key = (String) iter.next();
			
			if (!key.equals("Name")) {
				attributeKeys.add(key);
			}
		}
		
		// Orgainze the attribute names in alphabetical order
		Collections.sort(attributeKeys);
		
		// Get the attribute values in the same order
		for (String key : attributeKeys) {
			if (!key.equals("Name")) {
				attributeValues.add(attributes.get(key).getValue());
				displayAttributes.add(attributes.get(key));
			}
		}
		
		// Set the attribute names and attribute values in the super class
		super.setAttributeNames(attributeKeys);
		super.setStandardizableValues(attributeValues);
		
		super.setDisplayNames(attributeKeys);
		super.setDisplayValues(displayAttributes);
		
	}
	
}
