package JUnitTesting;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Model.StringAttribute;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 *
 * JUnitTestStringAttribute Class
 * - Contains all of the test cases for StringAttribute
 * 
 */
public class JUnitTestStringAttribute {
	StringAttribute stringAttribute;
	
	/*
	 * Initialize stringAttribute for every function to use
	 */
	@Before
	public void setUp() throws Exception {
		stringAttribute = new StringAttribute("Test Name", "Test Value");
	}
	
	/*
	 * Make sure all the functions of type get work
	 * Make sure have correct attribute name, attribute value
	 */
	@Test
	public void testStringAttributeGetters() {
		assertTrue(stringAttribute.getAttributeName().equals("Test Name"));
		assertTrue(stringAttribute.getStringValue().equals("Test Value"));
	}
	
	/*
	 * Make sure all the function of type set work
	 * Make sure have correct attribute name, attribute value
	 */
	@Test
	public void testStringAttributeSetters() {
		assertTrue(stringAttribute.getAttributeName().equals("Test Name"));
		assertTrue(stringAttribute.getStringValue().equals("Test Value"));
		
		stringAttribute.setAttributeName("Test Test Name");
		stringAttribute.setValue("New New Value");
		
		assertTrue(stringAttribute.getAttributeName().equals("Test Test Name"));
		assertTrue(stringAttribute.getStringValue().equals("New New Value"));
	}
	
	/*
	 * Make sure outputs correct attribute name along with correct attribute value
	 */
	@Test
	public void testToStringMethod() {
		assertTrue(stringAttribute.toString().equalsIgnoreCase("Attribute Name: Test Name Value: Test Value"));
	
		stringAttribute.setAttributeName("Test Test Name");
		stringAttribute.setValue("New New Value");
		
		assertTrue(stringAttribute.toString().equalsIgnoreCase("Attribute Name: Test Test Name Value: New New Value"));
	}
}
