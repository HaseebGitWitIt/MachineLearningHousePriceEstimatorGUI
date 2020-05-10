package JUnitTesting;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.EnumAttribute;
import Model.Enums;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 *
 * JUnitTestEnumAttribute Class
 * - Contains all of the test cases for the EnumAttribute class
 * 
 */
public class JUnitTestEnumAttribute {
	private EnumAttribute enumAttribute;
	
	/**
	 * Create a new EnumAttribute for testing before every test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		enumAttribute = new EnumAttribute("Test Value", Enums.valueOf("NEW"));
	}
	
	/**
	 * Reset varaibles to null
	 */
	@After
	public void tearDown() {
		enumAttribute = null;
	}
	
	/**
	 * Test the getAttributeName function in EnumAttribute
	 * The function should return "Test Value"
	 */
	@Test
	public void testEnumAttributeGetAttributeName() {
		assertTrue(enumAttribute.getAttributeName().equals("Test Value"));
	}
	
	/**
	 * Test the getStringValue function in EnumAttribute
	 * The function should return "NEW"
	 */
	@Test
	public void testEnumAttributeGetStringValue() {
		assertTrue(enumAttribute.getStringValue().equals("NEW"));
	}
	
	/**
	 * Test the getValue method in EnumAttribute
	 * Should return 0
	 */
	@Test
	public void testEnumAttributeGetValue() {
		assertTrue(enumAttribute.getValue() == 0);
	}
	
	/**
	 * Test the setAttributeName method in EnumAttribute
	 * Should return "Test Value 2"
	 */
	@Test
	public void testEnumAttributeSetAttributeName() {
		enumAttribute.setAttributeName("Test Value 2");
		
		assertTrue(enumAttribute.getAttributeName().equals("Test Value 2"));
	}
	
	/**
	 * Test the setValue method in EnumAttribute
	 * Should return "OLD" as the string value and 1 as the value
	 */
	@Test
	public void testEnumAttributeSetValue() {
		enumAttribute.setValue(Enums.valueOf("OLD"));
		
		assertTrue(enumAttribute.getStringValue().equals("OLD"));
		assertTrue(enumAttribute.getValue() == 1);
	}
	
	/**
	 * Make sure outputs correct attribute name along with correct attribute value
	 */
	@Test
	public void testToStringMethod() {
		assertTrue(enumAttribute.toString().equalsIgnoreCase("Attribute Name: Test Value Value: NEW"));
		
		enumAttribute.setAttributeName("Test Value 2");
		enumAttribute.setValue(Enums.valueOf("OLD"));
		
		assertTrue(enumAttribute.toString().equalsIgnoreCase("Attribute Name: Test Value 2 Value: OLD"));
	}
}
