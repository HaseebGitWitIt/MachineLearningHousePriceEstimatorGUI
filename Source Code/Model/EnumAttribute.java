package Model;
/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 * 
 * Class EnumAttribute
 * -Provides details for implementation of enum attributes of objects
 */
public class EnumAttribute implements Attribute {
	
	//Name of the attribute and value
	private String attributeName;
	private Enum<?> enumValue;
	
	/*
	 * Default Constructor
	 * 
	 * @param name : Name of the attribute
	 * @param value : Value of the attribute
	 * 
	 */
	public EnumAttribute(String name, Enum<?> value) {
		
		this.attributeName = name;
		this.enumValue = value;
		
	}
	
	/*
	 * Method to set the attribute value
	 * @param newVal : The new value of the attribute
	 * 
	 */
	public void setValue(Enum<?> newVal) {
		
		enumValue = newVal;
		
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
		
		return ((double)enumValue.ordinal());
		
	}

	/*
	 * Method to return string value
	 * @return : Value of attribute as string
	 */
	@Override
	public String getStringValue() {
		
		return enumValue.toString();
		
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
