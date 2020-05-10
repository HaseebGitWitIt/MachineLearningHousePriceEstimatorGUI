package JUnitTesting;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.Algorithm;
import Model.Attribute;
import Model.DoubleAttribute;
import Model.EnumAttribute;
import Model.Enums;
import Model.StringAttribute;
import Model.TestingExample;
import Model.TrainingExample;
import Model.knnComparible;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 *
 * JUnitTestAlgorithm Class
 * -Contains all of the test cases for the Algorithm class
 * 
 */
public class JUnitTestAlgorithm {
	private ArrayList<knnComparible> houseList;
	private TestingExample testingHouse1, testingHouse2, testingHouse3;
	private Algorithm test;
	
	/**
	 * Create a basic list containing 3 TrainingExamples for each test
	 * Create two TestingExamples for testing the Algorithm class 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		test = new Algorithm();
		houseList = new ArrayList<knnComparible>();
		
		HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();
		
		// Create three TrainingExamples and add them to the list
		attributes = new HashMap<String, Attribute>();		
		attributes.put("Name", new StringAttribute("name", "h1"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 12));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 25));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 1200));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", 500000));
		
		houseList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));
		
		attributes = new HashMap<String, Attribute>();		
		attributes.put("Name", new StringAttribute("name", "h2"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 10));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 50));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 1000));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("OLD")));
		attributes.put("Price", new DoubleAttribute("price", 300000));
		
		houseList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));
		
		attributes = new HashMap<String, Attribute>();		
		attributes.put("Name", new StringAttribute("name", "h3"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 30));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 100));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 800));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", 400000));
		
		houseList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));
		
		
		// Create three TestingExamples
		attributes = new HashMap<String, Attribute>();		
		attributes.put("Name", new StringAttribute("name", "h3"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 8));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 20));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 1400));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", 600000));
		
		testingHouse1 = new TestingExample(attributes.get("Name").getStringValue(), attributes, "Price");
		
		attributes = new HashMap<String, Attribute>();		
		attributes.put("Name", new StringAttribute("name", "h3"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 30));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 100));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 800));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", 400000));
		
		testingHouse2 = new TestingExample(attributes.get("Name").getStringValue(), attributes, "Price");
		
		attributes = new HashMap<String, Attribute>();		
		attributes.put("Name", new StringAttribute("name", "h4"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 15));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 20));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 1000));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", 0));
		
		testingHouse3 = new TestingExample(attributes.get("Name").getStringValue(), attributes, "Price");
	}
	
	/*
	 * Set all private variables to null
	 */
	@After
	public void tearDown() {
		houseList = null;
		testingHouse1 = null;
		testingHouse2 = null;
		testingHouse3 = null;
		test = null;
	}
	
	// Tests for Algorithm
	// Tests for knnMethod
	
	/**
	 * Tests the knn method in the Algorithm class with k = 0
	 * Should return null
	 */
	@Test
	public void testAlgorithmknnWithZero() {
		assertEquals(null, test.knn(0, houseList, testingHouse2));
	}
	
	/**
	 * Tests the knn method in the Algorithm classs with k = 1
	 * Should return an ArrayList with the closest TrainingExample
	 */
	@Test
	public void testAlgorithmknnWithOne() {		
		assertEquals(houseList.get(0), test.knn(1, houseList, testingHouse1).get(0));
	}
		
	/**
	 * Tests the knn method in the Algorithm class where k is equal to the size of the database
	 * Should return a list containing every TrainingExample in the database
	 */
	@Test
	public void testAlgorithmknnWithAll() {
		assertEquals(3, test.knn(3, houseList, testingHouse2).size());
	}
		
	/** 
	 * Tests the knn method in the Algorithm class where k is more than the size of the database
	 * Should return an ArrayList containing every TrainingExample in the database
	 */
	@Test
	public void testAlgorithmknnWithMore() {
		assertEquals(3, test.knn(6, houseList, testingHouse1).size());
	}
	
	/**
	 * Tests the generateAttributeWeights in the Algorithm class
	 * Should return an array with a length equal to the number of attributes
	 */
	@Test
	public void testAlgorithmGenerateAttributeWeights() {
		assertEquals(5, test.generateAttributeWeights(houseList).length);
	}
		
	/**
	 * Tests the minDistance method in the Algorithm class
	 * Should return the closest house. Rigged so that is should be testingHouse1.
	 */
	@Test
	public void testAlgorithmMinDistance() {
		assertEquals(houseList.get(0), test.minDistance(houseList, testingHouse1));
	}
		
	/**
	 * Tests the calcError function in the Algorithm class
	 * Should return 0.0
	 */
	@Test
	public void testAlgorithmCalcErrorZero() {
		assertTrue(0.0 == test.calcError(34.0, 34.0));
	}
		
	/**
	 * Tests the calcError function in the Algorithm class
	 * Should return something other than 0.0
	 */
	@Test
	public void testAlgorithmCalcErrorNonZero() {
		assertTrue(0.0 != test.calcError(34.0, 79.0));
	}
		
	/**
	 * Tests the standardizeValues in the Algorithm class
	 * Was tested ahead of time so output is known to be {0.0, 1.0, 1.0, 0.1, 0.0}, 
	 * so it checks that this is still the case
	 */
	@Test
	public void testAlgorithmStandardizeValues() {
		knnComparible testHouse = test.standardizeValues(houseList).get(0);
		ArrayList<Double> vals = testHouse.getStandardizableValues();
			
		assertTrue(vals.get(0) == 0.0);
		assertTrue(vals.get(1) == 1.0);
		assertTrue(vals.get(2) == 1.0);
		assertTrue(vals.get(3) == 0.1);
		assertTrue(vals.get(4) == 0.0);
	}
	
	/**
	 * Tests the calculateCoefficients in the Algorithm class
	 * Was tested ahead of time so desired coefficients are known, so they are looked for
	 */
	@Test
	public void testAlgorithmCalculateCoefficients() {
		double[] coeffs = test.calculateCoefficients(houseList, testingHouse3);
			
		assertTrue(coeffs[0] >= 0.2);
		assertTrue(coeffs[0] <= 0.3);
		assertTrue(coeffs[1] >= 0.4);
		assertTrue(coeffs[1] <= 0.5);
		assertTrue(coeffs[2] >= 0.3);
		assertTrue(coeffs[2] <= 0.4);
	}
		
	/**
	 * Test the computeEstimate method in the Algorithm class
	 * Price is known to be around 27856, so this answer is looked for
	 */
	@Test
	public void testAlgorithmComputeEstimate() {
		double price = test.computeEstimate(houseList, testingHouse3);
			
		assertTrue(427840 <= price);
		assertTrue(427850 >= price);
	}
}
