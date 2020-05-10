package Model;
/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 * 
 * Interface Attribute
 * -Provides method signature for methods each class that implements should have. Further details in classes that implement.
 */
public interface Attribute {
	
	/**
	* Returns the name of the attriubte
	* @return: String containing the name of the attribute
	*/
	public String getAttributeName();
	
	/**
	* Sets the name of the attribute
	* @param newName: The new name of the attribute
	*/
	public void setAttributeName(String newName);
	
	/**
	* Returns the value of the attribute as a double
	* @return: double containing the value of the Attribute
	*/
	public Double getValue();
	
	/**
	* Returns the value of the Attribute as a String for printing
	* @return: String containing the value of the Attribute
	*/
	public String getStringValue();
	
	/**
	* Returns the name and value of the Attribute as a String for printing
	* @return: String containing the name and value of the Attribute
	*/
	public String toString();

}
