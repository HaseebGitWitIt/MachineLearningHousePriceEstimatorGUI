package Model;
/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 * 
 * Class StringAttribute
 * -Provides details for implementation of string attributes of objects
 */
public class StringAttribute implements Attribute{
	
	//Name of the attribute and value
	private String attributeName;
	private String attributeValue;
	
	/*
	 * Default Constructor
	 * 
	 * @param name : Name of the attribute
	 * @param value : Value of the attribute
	 * 
	 */
	public StringAttribute(String name, String value) {
		
		this.attributeName = name;
		this.attributeValue = value;
		
	}
	
	/*
	 * Method to set the attribute value
	 * @param newVal : The new value of the attribute
	 * 
	 */
	public void setValue(String newVal) {
		
		attributeValue = newVal;
		
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
	 * Method to return double value of a string
	 * @return : String to double
	 * 
	 * DO NOT NEED TO USE 
	 * 
	 */
	@Override
	public Double getValue() {
		double toReturn;
		
		try {
			toReturn = Double.parseDouble(attributeValue);
		} catch (Exception e){
			toReturn = -1;
		}
		
		
		return(toReturn);
		
	}

	/*
	 * Method to return string value
	 * @return : Value of attribute as string
	 */
	@Override
	public String getStringValue() {

		return attributeValue;
		
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
