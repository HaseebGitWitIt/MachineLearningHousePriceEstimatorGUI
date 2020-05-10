package JUnitTesting;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.Attribute;
import Model.DoubleAttribute;
import Model.EnumAttribute;
import Model.Enums;
import Model.EstimatingModel;
import Model.StringAttribute;
import Model.TrainingExample;

public class JUnitTestEstimatingModel {
	private TrainingExample test1, test2, test3;
	private EstimatingModel model;
	
	@Before
	public void setUp() throws Exception {
		model = new EstimatingModel();
		
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
	}
	
	@After
	public void tearDown() {
		model = null;
	}
	
	/**
	 * Test XML
	 */
	@Test
	public void testXML() {
		
		String temp = "";
		String test1ShouldBe = "<Model><TrainingExample><Name>h1</Name><Attribute><AttributeName>Age</AttributeName><EnumValue>NEW</EnumValue></Attribute><Attribute><AttributeName>Price</AttributeName><DoubleValue>500000.0</DoubleValue></Attribute><Attribute><AttributeName>Square Foot</AttributeName><DoubleValue>1200.0</DoubleValue></Attribute><Attribute><AttributeName>X - Coordinate</AttributeName><DoubleValue>12.0</DoubleValue></Attribute><Attribute><AttributeName>Y - Coordinate</AttributeName><DoubleValue>25.0</DoubleValue></Attribute></TrainingExample></Model>";
		String test123ShouldBe = "<Model><TrainingExample><Name>h1</Name><Attribute><AttributeName>Age</AttributeName><EnumValue>NEW</EnumValue></Attribute><Attribute><AttributeName>Price</AttributeName><DoubleValue>500000.0</DoubleValue></Attribute><Attribute><AttributeName>Square Foot</AttributeName><DoubleValue>1200.0</DoubleValue></Attribute><Attribute><AttributeName>X - Coordinate</AttributeName><DoubleValue>12.0</DoubleValue></Attribute><Attribute><AttributeName>Y - Coordinate</AttributeName><DoubleValue>25.0</DoubleValue></Attribute></TrainingExample><TrainingExample><Name>h2</Name><Attribute><AttributeName>Age</AttributeName><EnumValue>NEW</EnumValue></Attribute><Attribute><AttributeName>Price</AttributeName><DoubleValue>500000.0</DoubleValue></Attribute><Attribute><AttributeName>Square Foot</AttributeName><DoubleValue>1200.0</DoubleValue></Attribute><Attribute><AttributeName>X - Coordinate</AttributeName><DoubleValue>12.0</DoubleValue></Attribute><Attribute><AttributeName>Y - Coordinate</AttributeName><DoubleValue>25.0</DoubleValue></Attribute></TrainingExample><TrainingExample><Name>h2</Name><Attribute><AttributeName>Age</AttributeName><EnumValue>OLD</EnumValue></Attribute><Attribute><AttributeName>Price</AttributeName><DoubleValue>300000.0</DoubleValue></Attribute><Attribute><AttributeName>Square Foot</AttributeName><DoubleValue>1000.0</DoubleValue></Attribute><Attribute><AttributeName>X - Coordinate</AttributeName><DoubleValue>10.0</DoubleValue></Attribute><Attribute><AttributeName>Y - Coordinate</AttributeName><DoubleValue>50.0</DoubleValue></Attribute></TrainingExample></Model>";
		
		model.addObject(test1);
	    temp = model.toXML();
		assertTrue(temp.equals(test1ShouldBe));
		
		//Should be empty
		model.clearList();
		temp = model.toXML();
		assertTrue(temp.equals("<Model></Model>"));
		
		//Add 3
		model.addObject(test1);
		model.addObject(test2);
		model.addObject(test3);
	    temp = model.toXML();
		assertTrue(temp.equals(test123ShouldBe));
		
	}
	
	/**
	 * Test save
	 */
	@Test
	public void testSave() {
		
		File tmp = new File("TrainingModel.xml");
		model.addObject(test1);
		model.save("TrainingModel");
		assertTrue(tmp.exists());
		
	}
	
	/**
	 * Test load
	 */
	@Test
	public void testLoad() {
		
		//Make sure list is empty
		model.clearList();
		assertTrue(model.getObjectListSize() == 0);
		
		
		//Add objects to save
		model.addObject(test1);
		model.addObject(test2);
		model.addObject(test3);
		assertTrue(model.getObjectListSize() == 3);
		EstimatingModel comp = model;
		
		//Save then clear, then load and make sure size 3 again
		model.save("Test");
		model.clearList();
		assertTrue(model.getObjectListSize() == 0);
		model.load("Test");
		assertTrue(model.getObjectListSize() == 3);
		
		//Make sure same name and attirbutes
		for (int a = 0; a < comp.getObjectList().size(); a++) {
			
			assertTrue(model.getObjectList().get(a).getName().equals(comp.getObjectList().get(a).getName()));
			assertTrue(model.getObjectList().get(a).getDisplayValues().equals(comp.getObjectList().get(a).getDisplayValues()));
			
		}
		
	}
	
	/**
	 * Test model with no objects
	 */
	@Test
	public void testEmptyEstimatingModel() {
		assert(EstimatingModel.getObjectListSize() == 0);
	}
	
	/**
	 * Test model with one object
	 */
	@Test
	public void testWithOneEstimatingModel() {
		EstimatingModel.addObject(test1);
		assert(EstimatingModel.getObjectListSize() == 1);
	}
	
	/**
	 * Test model with two object
	 */
	@Test
	public void testWithTwoEstimatingModel() {
		EstimatingModel.addObject(test1);
		EstimatingModel.addObject(test2);
		assert(EstimatingModel.getObjectListSize() == 2);
	}
	
	/**
	 * Test model by adding object without passing training example
	 */
	@Test
	public void testAddWithoutTrainingExampleEstimatingModel() {
		String name = "abc";
		HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();		
		attributes.put("Name", new StringAttribute("name", "h1"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 12));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 25));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 1200));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", 500000));
		
		EstimatingModel.addObject(name, attributes);
		assert(EstimatingModel.getObjectListSize() == 1);
	}
	
	/**
	 * Test model by clearing objects
	 */
	@Test
	public void testClearEstimatingModel() {
		EstimatingModel.addObject(test1);
		EstimatingModel.addObject(test2);
		EstimatingModel.clearList();
		assert(EstimatingModel.getObjectListSize() == 0);
	}
	
	/**
	 * Test model by removing one object
	 */
	@Test
	public void testRemoveEstimatingModel() {
		EstimatingModel.addObject(test1);
		EstimatingModel.removeObject(0);
		assert(EstimatingModel.getObjectListSize() == 0);
	}
	
	/**
	 * Test model by removing object at specific location
	 */
	@Test
	public void testRemoveAtPositionEstimatingModel() {
		EstimatingModel.addObject(test1);
		EstimatingModel.addObject(test2);
		EstimatingModel.addObject(test1);
		EstimatingModel.removeObject(1);
		assert(EstimatingModel.getObjectListSize() == 2);
	}
	
	/**
	 * Test model by editing Object
	 */
	@Test
	public void testEditEstimatingModel() {
		EstimatingModel.addObject(test1);
		EstimatingModel.addObject(test2);
		EstimatingModel.editObject(0, test3);
		assert(EstimatingModel.getObjectList().get(0) != test1);
	}
	
	/**
	 * Test model by editing Object without training example
	 */
	@Test
	public void testEditWithoutTrainingExampleEstimatingModel() {
		EstimatingModel.addObject(test1);
		EstimatingModel.addObject(test2);
		
		String name = "abc";
		HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();		
		attributes.put("Name", new StringAttribute("name", "h1"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 12));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 25));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 1200));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", 500000));
		
		EstimatingModel.editObject(0, name, attributes);
		assert(EstimatingModel.getObjectList().get(0) != test1);
	}
	
	/**
	 * Test model by getting Object list
	 */
	@Test
	public void testgetObjectListEstimatingModel() {
		EstimatingModel.addObject(test1);
		EstimatingModel.addObject(test2);
		EstimatingModel.getObjectList();
		
		assert(EstimatingModel.getObjectList().get(0) == test1);
		assert(EstimatingModel.getObjectList().get(1) == test2);
	}
	
	/**
	 * Test model by getting unknown attribute
	 */
	@Test
	public void testgetUnknownAttributeEstimatingModel() {
		assert(EstimatingModel.getUnknownAttribute().equals("Price"));
	}
	
	/**
	 * Test model by setting unknown attribute
	 */
	@Test
	public void testSetUnknownAttributeEstimatingModel() {
		EstimatingModel.setUnknownAttribute("Test");
		assert(EstimatingModel.getUnknownAttribute().equals("Test"));
	}
}
