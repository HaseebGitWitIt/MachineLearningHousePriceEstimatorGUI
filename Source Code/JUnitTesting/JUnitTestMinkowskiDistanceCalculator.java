package JUnitTesting;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.Attribute;
import Model.DoubleAttribute;
import Model.EnumAttribute;
import Model.Enums;
import Model.MinkowskiDistanceCalculator;
import Model.StringAttribute;
import Model.TrainingExample;

public class JUnitTestMinkowskiDistanceCalculator {

	private TrainingExample test1, test2, test3;
	private MinkowskiDistanceCalculator calc1, calc2;
	
	/** 
	 * Create TrainingExamples for testing
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();		
		attributes.put("Name", new StringAttribute("name", "h1"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 12));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 25));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 1200));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", 500000));
		
		test1 = new TrainingExample(attributes.get("Name").getStringValue(), attributes);
		
		attributes = new HashMap<String, Attribute>();		
		attributes.put("Name", new StringAttribute("name", "h2"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 12));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 25));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 1200));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", 500000));
		
		test2 = new TrainingExample(attributes.get("Name").getStringValue(), attributes);
		
		attributes = new HashMap<String, Attribute>();		
		attributes.put("Name", new StringAttribute("name", "h2"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 10));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 50));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 1000));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("OLD")));
		attributes.put("Price", new DoubleAttribute("price", 300000));
		
		test3 = new TrainingExample(attributes.get("Name").getStringValue(), attributes);
		
		calc1 = new MinkowskiDistanceCalculator(test1, test2, "Price", 2);
		
		calc2 = new MinkowskiDistanceCalculator(test1, test3, "Price", 2);
	}
	
	/**
	 * Set every variable to null
	 */
	@After
	public void tearDown() {
		test1 = null;
		test2 = null;
		test3 = null;
		
		calc1 = null;
		calc2 = null;
	}
	
	/**
	 * Test the constructor for MinkowskiDistanceCalculator with two object with the same parameters
	 * Should return 0
	 */
	@Test
	public void testMinkowskiDistanceCalculatorDistanceZero() {	
		assertTrue(0.0 == calc1.getDistance());		
	}
	
	/**
	 * Test the constructor for MinkowskiDistanceCalculator with two objects with different parameters
	 * Should return something other than 0
	 */
	@Test
	public void testMinkowskiDistanceCalculatorNonZero() {		
		assertTrue(0.0 != calc2.getDistance());
	}
	
	/**
	 * Test the getObj1 and getObj2 functions in MinkowskiDistanceCalculator
	 * Should return test1 and test2 respectively
	 */
	@Test
	public void testMinkowskiDistanceCalculatorGetFunctions() {
		assertEquals(test1, calc1.getObj1());
		assertEquals(test2, calc1.getObj2());
	}
	
	/**
	 * Test the setObj1 and setObj2 functions in MinkowskiDistanceCalculator
	 */
	@Test
	public void testMinkowskiDistanceCalculatorUpdateObjects() {
		assertTrue(0.0 != calc2.getDistance());	
		
		calc2.setObj2(test2);
		
		assertTrue(0.0 == calc2.getDistance());	
	}

}
