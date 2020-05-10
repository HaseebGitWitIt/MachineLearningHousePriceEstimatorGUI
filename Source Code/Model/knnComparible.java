package Model;
import java.util.*;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 * 
 * knnComparible Class
 * - The interface that objects must implement to be analyzed using the algorithm class.
*/ 
public abstract class knnComparible {
	private String name; // Name of the object
	private ArrayList<String> attributeNames;
	//private double price; // Price of the object (Price is whatever the algorithm is calculating, not necessarily a price)
	
	private static HashMap<String, ArrayList<String>> calculatorTypes = null;
	
	private ArrayList<Double> standardizableValues; // All values that can be set to a value between 0 and 1
	
	private ArrayList<Attribute> displayValues;
	private ArrayList<String> displayNames;
	
	/**
	* Constructor
	* Makes a new super knnComparible
	* @param name: The name of the knnComparible
	*/
	public knnComparible(String name) {
		this.name = name;
		attributeNames = new ArrayList<String>();
		standardizableValues = new ArrayList<Double>();
	}
	
	/**
	* Sets the attribute names to the given ArrayList
	* @param newAttributeNames: The new attribute names for the knnComparible
	*/
	public void setAttributeNames(ArrayList<String> newAttributeNames) {
		attributeNames = newAttributeNames;
	}
	
	/**
	* Returns the attribute names for the knnComparible
	* @return: ArrayList<String> containing the names for the attribute
	*/
	public ArrayList<String> getAttributeNames(){
		return(attributeNames);
	}
	
	/**
	* Returns an arraylist containing all values that can be standardized. 
	* The vlaues that can be standardized are any that can have a numeric value
	* such as: Integers, doubles, floats, enum's ordinals, etc.
	* @return: ArrayList<Double> that contains all of the standardized values
	*/
	public ArrayList<Double> getStandardizableValues(){
		return(standardizableValues);
	}
	
	/**
	* Returns the standardized value at the given index
	* @param index: The standardized value to return
	* @return: The standardized value at the given index
	*/
	public Double getStandardizedValue(int index) {
		return(standardizableValues.get(index));
	}
	
	/**
	* Sets all of the standardizable values equal to the the given value from
	* the given arraylist in the order of the values in the constructor. 
	* For example, if the constructor is: class(double val1, double val2, double val3),
	* then this method will set the values in the order:
	* 	val1 = toCopy.get(0);
	*	val2 = toCopy.get(1);
	*	val3 = toCopy.get(2);
	* @param toCopy: An arraylist of doubles containing the new vlaues for each attribute.
	*/
	public void setStandardizableValues(ArrayList<Double> toCopy) {
		int n = 0;
		
		while (n < toCopy.size()) {
			if (n >= standardizableValues.size()) {
				standardizableValues.add(toCopy.get(n));
			} else {
				standardizableValues.set(n, toCopy.get(n));
			}
			n = n + 1;
		}
	}
	
	/**
	* Sets the value in the setStandardizableValue array at the given index
	* eqaul to the given value.
	* @param newVal: The new value for the element at the given index
	* @param index: The index to change
	*/
	public void setStandardizableValue(int index, double newVal) {
		if ((index >= 0) && (index <= standardizableValues.size() - 1)){
			standardizableValues.set(index, newVal);
		}
	}
	
	/**
	* Returns the name of the element.
	* @return: String continaing the name of the element.
	*/
	public String getName() {
		return(name);
	}
	
	/**
	* Returns a string containing the details of the element that is displayed in the menu class.
	* @return: String containing the details of the element.
	*/
	public String toString() {
		String toReturn = "";
		
		toReturn = toReturn + "Name: " + this.getName();
		
		int n = 0;
		
		if (displayValues.size() <= displayNames.size()) {
			while (n < displayValues.size()) {
				toReturn = toReturn + " " + displayNames.get(n) + ": " + displayValues.get(n).getStringValue();
				n = n + 1;
			}
		} else {
			while (n < displayNames.size()) {
				toReturn = toReturn + " " + displayNames.get(n) + ": " + displayValues.get(n).getStringValue();
				n = n + 1;
			}
		}
		
		return(toReturn);
	}
	
	/**
	* Returns the euclidean distance between this object and another object of the same type.
	* Uses every attribute from this element in the equation for calculating the distance.
	* Uses the following method to calculate the distance:
	*	Math.sqrt((x1 + y1) ^ 2 + (x2 + y2) ^ 2 + (x3 + y3) ^ 2 + ... + (xn + yn) ^ 2)
	* @param compareTo: The object of the same type that this object is being compared to
	* @return: double containg the distance between this object and the given object
	*/
  	public double distance(knnComparible compareTo, double[] weights, String keyToExclude) {
  		if (calculatorTypes == null) {
  			calculatorTypes = new HashMap<String, ArrayList<String>>();
  			calculatorTypes.put("Euclidean", this.getAttributeNames());
  		}
  		
  		Calculator calc = new Calculator(this, compareTo, keyToExclude, calculatorTypes);
		
		return calc.getDistance();
  		
  		//"Manhatten", "Euclidean", "Minkowski (3)", "Minkowski (4)", "Minkowski (5)"
  		
  		
  	}
  	
	/**
	* Returns a copy of this element.
	* Creates and returns a new object of this type 
	* with all of the same attributes.
	* @return: A duplicate object of this object.
	*/
	public knnComparible copy() {
		TrainingExample toReturn = new TrainingExample(this.getName());
		toReturn.setAttributeNames(this.getAttributeNames());
		toReturn.setStandardizableValues(this.getStandardizableValues());
		return(toReturn);
	}
	
	/**
	* Sets the list of names to display
	* @param newDisplayNames: The new list of names to display
	**/
	public void setDisplayNames(ArrayList<String> newDisplayNames) {
		displayNames = newDisplayNames;
	}
	
	/**
	* Sets the list of values to display
	* @param newDispalyValues: The new list of values to display
	**/
	public void setDisplayValues(ArrayList<Attribute> newDisplayValues) {
		displayValues = newDisplayValues;
	}
	
	/**
	* Returns the list of values to display
	* @return: The list of values to display
	**/
	public ArrayList<Attribute> getDisplayValues() {
		return(displayValues);
	}
	
	/**
	* Sets the list of calculators to use for calculations
	* @param newCalculatorTypes: The new list of calculators to use for each attribute
	**/
	public static void setCalculatorTypes(HashMap<String, ArrayList<String>> newCalculatorTypes) {
		calculatorTypes = newCalculatorTypes;
	}
	
	/**
	* Returns the value corresponding to the given name and 0  if it could not be found
	* @param name: The name to look for
	* @return: The value corresponding to the given name
	**/
	public Double getValueFromName(String name) {
		int i = 0;
		
		while (i < attributeNames.size()) {
			if (attributeNames.get(i).equals(name)) {
				return (standardizableValues.get(i));
			}
			i = i + 1;
		}
		
		return(Double.valueOf(0));
	}
	
	public void setValue(Attribute newAttribute) {
		int i = 0;
		
		while (i < attributeNames.size()) {
			if(attributeNames.get(i).equals(newAttribute.getAttributeName())) {
				displayValues.set(i, newAttribute);
				standardizableValues.set(i, newAttribute.getValue());
			}
			i = i + 1;
		}
	}
	
	/**
	* Returns the list of names to display
	* @return: The list of names to display
	**/
	public ArrayList<String> getDisplayNames() {
		return(displayNames);
	}
	
	/*
	 * Sets the name of the knnCOmparible
	 * @param: newName is the name that we want to set 
	 */
	public void setName(String newName) {
		
		this.name = newName;
		
	}
	
}
