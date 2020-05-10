package JUnitTesting;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.Attribute;
import Model.CompositeAttribute;
import Model.DoubleAttribute;

public class JUnitTestCompositeAttribute {
	private CompositeAttribute c = null;
	private ArrayList<Attribute> subAttributes = new ArrayList<Attribute>();
	private DoubleAttribute temp = new DoubleAttribute("Abc", 123.0);
	
	@Before
	public void setUp() throws Exception {
		subAttributes.add(temp);
		c = new CompositeAttribute("TempName", subAttributes);
	}

	@After
	public void tearDown() throws Exception {
		subAttributes.clear();		//Delete all attributes in arrayList
		
	}			

	/**
	 * Test the constructor by passing two parameters
	 * */
	@Test
	public void testCompositeAttributeWithTwoParam() {
		c = new CompositeAttribute("Name2", subAttributes);
		assert(c.getAttributeName().equals("Name2"));
	}

	/**
	 * Test the constructor by passing two parameters
	 * */
	@Test
	public void testCompositeAttributeWithOneParam() {
		c = new CompositeAttribute("TempName");
		assert(c.getAttributeName().equals("TempName"));
	}

	/**
	 * Test the getAttributeName method
	 */
	@Test
	public void testGetAttributeName() {
		assert(c.getAttributeName().equals("TempName"));
	}

	/**
	 * Test the setAttributeName method by setting a new name
	 */
	@Test
	public void testSetAttributeName() {
		c.setAttributeName("newName");
		assert(c.getAttributeName().equals("newName"));
	}

	/**
	 * Test the getvalue method 
	 */
	@Test
	public void testGetValue() {
		assert(c.getValue() == null);
	}

	/**
	 * Test the getStringValue method 
	 */
	@Test
	public void testGetStringValue() {
		assert(c.getStringValue() == null);
	}

	/**
	 * Test the toString method 
	 */
	@Test
	public void testToString() {
		String s = "Attribute Name: TempName Subattributes: (Attribute Name: Abc Value: 123.0)";	//Expected output
		assert(c.toString().equals(s));
	}

}
