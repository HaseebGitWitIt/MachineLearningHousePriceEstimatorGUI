package JUnitTesting;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.DoubleAttribute;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 *
 * JUnitTestDoubleAttribute Class
 * - Contains all of the test cases for the Double Attribute class
 * 
 */
public class JUnitTestDoubleAttribute {
	private DoubleAttribute doubleAttribute;
	
	/**
	 * Creates a new doubleAttribute for testing
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		doubleAttribute = new DoubleAttribute("Test Value", 3.4);
	}
	
	/**
	 * Resets the doubleAttribute to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		doubleAttribute = null;
	}
	
	/**
	 * Test the getAttributeName method in the DoubleAttribute class
	 * Should return "Test Value" as a String
	 */
	@Test
	public void testDoubleAttributeGetAttributeName() {
		assertTrue(doubleAttribute.getAttributeName().equals("Test Value"));
	}
	
	/**
	 * Test the getDoubleValueString method in the DoubleAttribute class
	 * Should return "3.4" as a String
	 */
	@Test 
	public void testDoubleAttributeGetDoubleValueString() {
		assertTrue(doubleAttribute.getDoubleValueString().equals("3.4"));
	}
	
	/**
	 * Test the getStingValue method in the DoubleAttribute class
	 * Should return "3.4" as a String
	 */
	@Test
	public void testDoubleAttributeGetStringValue() {
		assertTrue(doubleAttribute.getStringValue().equals("3.4"));
	}
	
	/**
	 * Test the getValue method in the DoubleAttribute class
	 * Should return 3.4 as a double
	 */
	@Test
	public void testDoubleAttributeGetValue() {
		assertTrue(doubleAttribute.getValue() == 3.4);
	}
	
	/**
	 * Tests the setAttrributeName method in the DoubleAttribute class
	 * New value should be "Test Value 2"
	 */
	@Test
	public void testDoubleAttributeSetAttributeName() {		
		doubleAttribute.setAttributeName("Test Value 2");
		
		assertTrue(doubleAttribute.getAttributeName().equals("Test Value 2"));
	}
	
	/** 
	 * Tests the setValue method in the Double Attribute class
	 * New value should be 4.2
	 */
	@Test
	public void testDoubleAttributeSetValue() {
		doubleAttribute.setValue(4.2);
		
		assertTrue(doubleAttribute.getDoubleValueString().equals("4.2"));
		assertTrue(doubleAttribute.getStringValue().equals("4.2"));
		assertTrue(doubleAttribute.getValue() == 4.2);
	}
	
	/**
	 * Make sure outputs correct attribute name along with correct attribute value
	 */
	@Test
	public void testToStringMethod() {
		assertTrue(doubleAttribute.toString().equalsIgnoreCase("Attribute Name: Test Value Value: 3.4"));
		
		doubleAttribute.setAttributeName("Test Value 2");
		doubleAttribute.setValue(4.2);
		
		assertTrue(doubleAttribute.toString().equalsIgnoreCase("Attribute Name: Test Value 2 Value: 4.2"));
	}
}
