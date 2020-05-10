package JUnitTesting;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.Attribute;
import Model.DoubleAttribute;
import Model.EnumAttribute;
import Model.Enums;
import Model.PolarCoordinateDistanceCalculator;
import Model.StringAttribute;
import Model.TrainingExample;

public class JUnitTestPolarCoordinateDistanceCalculator {

	private TrainingExample test1, test2, test3;
	private PolarCoordinateDistanceCalculator calc1, calc2;
	
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
		
		calc1 = new PolarCoordinateDistanceCalculator(test1, test2, "Price");
		calc2 = new PolarCoordinateDistanceCalculator(test1, test3, "Price");
	}

	/*Clear all vars to null*/
	@After
	public void tearDown() throws Exception {
		test1 = null;
		test2 = null;
		test3 = null;
		
		calc1 = null;
		calc2 = null;
	}

	/**
	 * Test the constructor for PolarCoordinateDistanceCalculator with two object with the same parameters
	 * Should return 0
	 */
	@Test
	public void testCalcDistanceZero() {
		assertTrue(0.0 == calc1.getDistance());		
	}
	
	/**
	 * Test the calculator by creating two arraylist with values
	 * Compare the return value with expected
	 */
	@Test
	public void testDistanceMetric() {
		//First input is 20, 45 degrees
		ArrayList<Double> arr1 = new ArrayList<Double>();
		arr1.add(45.0);	
		arr1.add(20.0);	
		
		//Second input is 10, 135 degrees
		ArrayList<Double> arr2 = new ArrayList<Double>();
		arr2.add(135.0);	
		arr2.add(10.0);	
		
		double tempDist = calc1.distanceMetric(arr1, arr2);
		assert(tempDist >= 26);		//Expected value is approx 26.06
	}

}
