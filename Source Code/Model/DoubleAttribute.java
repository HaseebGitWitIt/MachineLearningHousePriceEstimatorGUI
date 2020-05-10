package Model;
/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 * 
 * Class DoubleAttribute
 * -Provides details for implementation of double attributes of objects
 */
public class DoubleAttribute implements Attribute{
	
	//Name of the attribute and value
	private String attributeName;
	private Double doubleValue;
	
	/*
	 * Default Constructor
	 * 
	 * @param name : Name of the attribute
	 * @param value : Value of the attribute
	 * 
	 */
	public DoubleAttribute(String name, Double value) {
		
		this.attributeName = name;
		this.doubleValue = value;
		
	}
	
	public DoubleAttribute(String name, int value) {
		this(name, Double.valueOf(value));
	}
	
	/*
	 * Method to set the attribute value
	 * @param newVal : The new value of the attribute
	 * 
	 */
	public void setValue(Double newVal) {
		
		doubleValue = newVal;
		
	}
	
	/*
	 * Method to return string value
	 * @return : Value of attribute as string
	 */
	public String getDoubleValueString() {
		
		return Double.toString(doubleValue);
		
	}

	/*
	 * Method to return attribute name
	 * @return : Name of the attribute
	 */
	@Override
	public String getAttributeName() {
		
		return attributeName;
		
	}

	/*
	 * Method to set the attribute name
	 * @param newName : New name of the attribute
	 */
	@Override
	public void setAttributeName(String newName) {

		attributeName = newName;
		
	}

	/*
	 * Method to return double value of ordinal of an enum
	 * @return : Ordinal of enum to double
	 * 
	 */
	@Override
	public Double getValue() {
		
		return doubleValue;
		
	}

	/*
	 * Method to return string value
	 * @return : Value of attribute as string
	 */
	@Override
	public String getStringValue() {
		String toReturn = "";
		
		if (doubleValue == null) {
			toReturn = "NULL";
		} else {
			toReturn =  Double.toString(doubleValue);
		}
		
		return(toReturn);
		
	}
	
	/*
	 * Method to return string name and value
	 * @return : Name and value of attribute as string
	 */
	@Override
	public String toString() {

		return "Attribute Name: " + this.getAttributeName() + " Value: " + this.getStringValue();
		
	}


}
