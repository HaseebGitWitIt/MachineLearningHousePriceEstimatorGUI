package Model;
import java.util.ArrayList;

public class CompositeAttribute implements Attribute {
	private String name; // The name of the attribute
	private ArrayList<Attribute> subAttributes; // The sub-attributes that this attribute is made of
	
	/**
	* Constructor
	* Creates a new CompositeAttribute with the given sub-attributes
	* @param name: The name of the attribute
	* @param subAttributes: The attributes that this attribute is made of
	**/
	public CompositeAttribute(String name, ArrayList<Attribute> subAttributes) {
		this.name = name;
		this.subAttributes = subAttributes;
	}
	
	/**
	* Construcoter
	* Creates a new CompositeAttribute with an empty sub-attribute list
	* @param name: The name of the CompositeAttribute
	**/
	public CompositeAttribute(String name) {
		this(name, new ArrayList<Attribute>());
	}

	/**
	* Returns the name of this attribute
	* @return: The name of the attribute
	**/
	@Override
	public String getAttributeName() {
		return(name);
	}

	/**
	* Sets the name of the atttribute
	* @param newName: The new name of the attribute
	**/
	@Override
	public void setAttributeName(String newName) {
		name = newName;
		
	}

	/**
	* Returns null because CompositeAttributes do not have values
	* @return: Always returns null because CompositeAttributes do not have values
	**/
	@Override
	public Double getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* Returns null becuase CompositeAttributes do not have values
	* @return: Always returns null because CompositeAttributes do not have values
	**/
	@Override
	public String getStringValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	* Returns a string representation of the CompositeAttribute
	* @return: String representing the CompositeAttribute
	**/
	public String toString() {
		String toReturn = "";
		
		toReturn = toReturn + "Attribute Name: " + this.getAttributeName();
		toReturn = toReturn + " Subattributes: (";
		
		for (Attribute sub : subAttributes) {
			toReturn = toReturn + sub.toString();
		}
		
		toReturn = toReturn + ")";
		
		return(toReturn);
	}

}
