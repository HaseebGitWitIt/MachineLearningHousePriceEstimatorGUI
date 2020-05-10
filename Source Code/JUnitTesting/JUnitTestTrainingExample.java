package JUnitTesting;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import Model.Attribute;
import Model.DoubleAttribute;
import Model.EnumAttribute;
import Model.Enums;
import Model.StringAttribute;
import Model.TrainingExample;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 *
 * JUnitTestTrainingExample Class
 * - Contains all of the tests for the TrainingExample class
 * 
 */
public class JUnitTestTrainingExample {
	private TrainingExample test1;
	private TrainingExample test2;
	
	/**
	* Create two TrainingExample objects for testing purpose
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
	}
	
	/**
	* Test the various getter methods in the TrainingExample class
	* The first attribute name should be "Age" and the first element should be 0
	*/ 
	@Test
	public void testTrainingExampleGetters() {		
		assertTrue(test1.getName().equals("h1"));
		assertTrue(test1.getAttributeNames().get(0).equals("Age"));
		assertTrue(test1.getStandardizableValues().get(0) == 0);
		assertTrue(test1.getStandardizedValue(0) == 0);
	}
	
	/**
	* Tests the various setter methods in the TrainingExample class
	*/
	@Test
	public void testTrainingExampleSetters() {
		assertTrue(test1.getName().equals("h1"));
		assertTrue(test1.getAttributeNames().get(0).equals("Age"));
		assertTrue(test1.getStandardizableValues().get(0) == 0);
		assertTrue(test1.getStandardizedValue(0) == 0);
		
		ArrayList<String> newNames = new ArrayList<String>();
		newNames.add("Val1");
		newNames.add("Val2");
		newNames.add("Val3");
		newNames.add("Val4");
		
		ArrayList<Double> newVals = new ArrayList<Double>();
		newVals.add(1.2);
		newVals.add(3.57);
		newVals.add(6.1);
		newVals.add(-2.5);
		
		test1.setAttributeNames(newNames);
		test1.setStandardizableValues(newVals);
		test1.setStandardizableValue(2, 14.7);
		
		assertTrue(test1.getAttributeNames().get(0).equals("Val1"));
		assertTrue(test1.getStandardizableValues().get(0) == 1.2);
		assertTrue(test1.getStandardizedValue(2) == 14.7);
	}
	
	/**
	* Tests the toString method in TrainingExample
	* output should be "Name: h1 Age: 0.0 Price: 500000.0 Square Foot: 1200.0 X - Coordinate: 12.0 Y - Coordinate: 25.0 "
	*/
	@Test
	public void testTrainingExampleToString() {
		String test = test1.toString();
		System.out.println(test);
		System.out.println("Name: h1 Age: NEW Price: 500000.0 Square Foot: 1200.0 X - Coordinate: 12.0 Y - Coordinate: 25.0 ");
		assertTrue(test.equals("Name: h1 Age: NEW Price: 500000.0 Square Foot: 1200.0 X - Coordinate: 12.0 Y - Coordinate: 25.0"));
	}
	
	/**
	* Tests the copy method in TrainingExample
	* The new object should have the name "h1", the first attribute name should be "Age", and the first value should be 0
	*/
	@Test
	public void testTrainingExampleCopy() {
		TrainingExample testCopy = (TrainingExample) test1.copy();
		
		assertTrue(testCopy.getName().equals("h1"));
		assertTrue(testCopy.getAttributeNames().get(0).equals("Age"));
		assertTrue(testCopy.getStandardizableValues().get(0) == 0);
		assertTrue(testCopy.getStandardizedValue(0) == 0);	
	}
}
