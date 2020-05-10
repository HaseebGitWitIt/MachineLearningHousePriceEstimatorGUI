package JUnitTesting;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import Model.Attribute;
import Model.DoubleAttribute;
import Model.EnumAttribute;
import Model.Enums;
import Model.StringAttribute;
import Model.TestingExample;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 *
 * JUnitTestTestingExample Class
 * - Contains all of the test cases for the TestingExample class
 * 
 */
public class JUnitTestTestingExample {
	private TestingExample testingExample;
	
	/**
	* Create a testingExample for testing purposes
	*/
	@Before
	public void setUp() throws Exception {
		HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();		
		attributes.put("Name", new StringAttribute("name", "h3"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 30));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 100));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 800));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", 400000));
		testingExample = new TestingExample(attributes.get("Name").getStringValue(), attributes, "Price");
	}
	
	/**
	* Test the getKeyToCalculate method in the TestingExample class
	* Should return "Price"
	*/ 
	@Test
	public void testTestingExampleGetters() {
		assertTrue(testingExample.getKeyToCalculate().equals("Price"));
	}
	
	/**
	* Test the setIndexToCalculate method in the TestingExample class
	* New value should be "Square Foot" after the setter call
	*/
	@Test
	public void testTestingExampleSetters() {
		assertTrue(testingExample.getKeyToCalculate().equals("Price"));
		
		testingExample.setIndexToCalculate("Square Foot");
		
		assertTrue(testingExample.getKeyToCalculate().equals("Square Foot"));
	}
}
