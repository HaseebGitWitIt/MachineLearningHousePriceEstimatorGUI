package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
 * Team Name: HACZ Contributed Group Member: @author (Haseeb, Abraham, Calvin,
 * Ryan)
 * 
 * TestListener class Specific action to menu item: Test class response whenever
 * item Test is pressed in the menu bar
 */
public class TestListener implements ActionListener {
	private static ArrayList<knnComparible> testList = new ArrayList<knnComparible>();
	private JFrame f;
	private JScrollPane pane;
	private JTextArea area;

	public enum SoccerAction {
		NOACTION, KICK, DASH, TURN;
	}

	/**
	 * Runs the three test functions defined in the menu class
	 * 
	 * @param e:
	 *            The ActionEvent that called this method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		initGUI();
		test1();
		clearList(testList);
		test2();
		clearList(testList);
		test3();
		clearList(testList);
		tabulatedDataTest();
		clearList(testList);

		pane = new JScrollPane(area);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		f.add(pane);
		f.setVisible(true);
	}

	public void initGUI() {
		f = new JFrame("Tests");
		f.setSize(600, 630);
		// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		area = new JTextArea(300, 230);
	}

	/**
	 * First test case Given 2 house (h1 and h2) and estimate the price of the third
	 * house Example for comparison is hard coded
	 */
	public void test1() {
		area.append("\nSample 1: Estimate price of House 3 given House 1 and House 2\n");
		HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "h1"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 12));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 25));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 1200));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", 500000));

		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "h2"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 10));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 50));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 1000));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("OLD")));
		attributes.put("Price", new DoubleAttribute("price", 300000));

		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		displayTable(testList);
		// System.out.println("The calculated estimate of house 3 is...\n");
		area.append("The calculated estimate of house 3 is...\n");

		Algorithm temp_alg = new Algorithm();

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "h3"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 30));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 100));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 800));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", 0));

		double price = temp_alg.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Price"));

		// Check the error on the third house
		double error = temp_alg.calcError(price, 400000);

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "h3"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 30));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 100));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 800));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", price));

		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));
		// System.out.println("The percent error is: " + error + "%");
		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);
	}

	/**
	 * Second test case Given 3 houses and estimate the price of the fouth house
	 * Example for comparsion is hard coded
	 */
	public void test2() {
		// Add base houses
		HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "h1"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 12));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 25));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 1200));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", 500000));

		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "h2"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 10));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 50));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 1000));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("OLD")));
		attributes.put("Price", new DoubleAttribute("price", 300000));

		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "h3"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 30));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 100));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 800));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", 400000));

		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		displayTable(testList);
		// System.out.println("The calculated estimate of house 4 is...\n");
		area.append("The calculated estimate of house 4 is...\n");

		// Calculate the price of the fourth house
		Algorithm temp_alg2 = new Algorithm();

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "h4"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 15));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 20));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 1000));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", 0));

		double price = temp_alg2.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Price"));

		// Test the error on the price
		double error = temp_alg2.calcError(price, 430000);

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "h4"));
		attributes.put("X - Coordinate", new DoubleAttribute("xCord", 15));
		attributes.put("Y - Coordinate", new DoubleAttribute("yCord", 20));
		attributes.put("Square Foot", new DoubleAttribute("squareFoot", 1000));
		attributes.put("Age", new EnumAttribute("age", Enums.valueOf("NEW")));
		attributes.put("Price", new DoubleAttribute("price", price));

		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));
		// System.out.println("The percent error is: " + error + "%");
		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);
	}

	/**
	 * Third test cases Second Object Testing using: Room Giving 10 rooms and
	 * predict the price of the next 3 room (Room 11,12,13) Equation for estimation
	 * Price = 7 * value1 + 6 * value2 + value3 All example is hard coded for
	 * estimation
	 */
	public void test3() {
		// Example 3: Estimate the price of Room 11, 12, and 13 given rooms 1 - 9
		// Price equation is price = 7 * val1 + 6 * val2 + val3
		// Add the base rooms

		HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R1"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 1));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 1));
		attributes.put("Square Foot", new DoubleAttribute("val3", 1));
		attributes.put("Price", new DoubleAttribute("price", 14));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R2"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 2));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 3));
		attributes.put("Square Foot", new DoubleAttribute("val3", 4));
		attributes.put("Price", new DoubleAttribute("price", 36));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R3"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 4));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 2));
		attributes.put("Square Foot", new DoubleAttribute("val3", 1));
		attributes.put("Price", new DoubleAttribute("price", 41));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R4"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 7));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 4));
		attributes.put("Square Foot", new DoubleAttribute("val3", 19));
		attributes.put("Price", new DoubleAttribute("price", 92));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R5"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 3));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 12));
		attributes.put("Square Foot", new DoubleAttribute("val3", 3));
		attributes.put("Price", new DoubleAttribute("price", 96));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R6"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 5));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 7));
		attributes.put("Square Foot", new DoubleAttribute("val3", 9));
		attributes.put("Price", new DoubleAttribute("price", 86));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R7"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 6));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 5));
		attributes.put("Square Foot", new DoubleAttribute("val3", 12));
		attributes.put("Price", new DoubleAttribute("price", 84));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R8"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 3.3));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 2.5));
		attributes.put("Square Foot", new DoubleAttribute("val3", 13));
		attributes.put("Price", new DoubleAttribute("price", 51.099999));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R9"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 4.72));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 1.9));
		attributes.put("Square Foot", new DoubleAttribute("val3", 5));
		attributes.put("Price", new DoubleAttribute("price", 49.44));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R10"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 7.3));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 5.89));
		attributes.put("Square Foot", new DoubleAttribute("val3", 11.3));
		attributes.put("Price", new DoubleAttribute("price", 97.74));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		displayTable(testList);
		// System.out.println("The calculated estimate of Room 11 is...\n");
		area.append("The calculated estimate of Room 11 is...\n");

		// Calculate the price of the eleventh room
		Algorithm temp_alg2 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R11"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 2));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 7));
		attributes.put("Square Foot", new DoubleAttribute("val3", 14));
		attributes.put("Price", new DoubleAttribute("price", 0));
		double price = temp_alg2.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Price"));

		// Check the error on the room
		double error = temp_alg2.calcError(price, 70);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R11"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 2));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 7));
		attributes.put("Square Foot", new DoubleAttribute("val3", 14));
		attributes.put("Price", new DoubleAttribute("price", price));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		// System.out.println("The percent error is: " + error + "%");
		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);

		// System.out.println("The calculated estimate of Room 12 is...\n");
		area.append("The calculated estimate of Room 12 is...\n");

		// Calculate the price of the twelth room
		temp_alg2 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R12"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 3.4));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 3));
		attributes.put("Square Foot", new DoubleAttribute("val3", 12));
		attributes.put("Price", new DoubleAttribute("price", 0));
		price = temp_alg2.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Price"));

		// Calculate the error on the room
		error = temp_alg2.calcError(price, 53.8);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R12"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 3.4));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 3));
		attributes.put("Square Foot", new DoubleAttribute("val3", 12));
		attributes.put("Price", new DoubleAttribute("price", price));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));
		// System.out.println("The percent error is: " + error + "%");
		area.append("The percent error is: " + error + "%\n");

		// System.out.println("The calculated estimate of Room 13 is...\n");
		area.append("The calculated estimate of Room 13 is...\n");

		// Calculate the price of the thirteenth room
		temp_alg2 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R13"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 5.223));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 2.93));
		attributes.put("Square Foot", new DoubleAttribute("val3", 11.2));
		attributes.put("Price", new DoubleAttribute("price", 0));
		price = temp_alg2.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Price"));

		// Calculate the error on the room
		error = temp_alg2.calcError(price, 65.341);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "R13"));
		attributes.put("X - Coordinate", new DoubleAttribute("val1", 5.223));
		attributes.put("Y - Coordinate", new DoubleAttribute("val2", 2.93));
		attributes.put("Square Foot", new DoubleAttribute("val3", 11.2));
		attributes.put("Price", new DoubleAttribute("price", price));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		// System.out.println("The percent error is: " + error + "%");
		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);
	}

	public void tabulatedDataTest() {
		//HashMap<String, ArrayList<String>>;
		
		HashMap<String, ArrayList<String>> calculatorsToUse = new HashMap<String, ArrayList<String>>();
		
		ArrayList<String> currNames = new ArrayList<String>();
		currNames.add("BallDirection");
		currNames.add("BallDistance");
		currNames.add("GoalDirection");
		currNames.add("GoalDistance");
		currNames.add("FlagsFCTDire");
		currNames.add("FlagsFCTDist");
		currNames.add("FlagsFCBDire");
		currNames.add("FlagsFCBDist");
		
		calculatorsToUse.put("Polar", currNames);
		
		currNames = new ArrayList<String>();
		currNames.add("Action");
		
		calculatorsToUse.put("Manhatten", currNames);
		
		knnComparible.setCalculatorTypes(calculatorsToUse);
		
		HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A1"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 1.9));
		attributes.put("BallDirection", new DoubleAttribute("Direction", -167));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 63.8));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 31));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 39.1));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -41));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("KICK")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A2"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 1.9));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 50));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 63.8));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 31));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 39.1));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -41));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("KICK")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A3"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 1.8));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 2));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 61.9));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", -4));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("KICK")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A4"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 1.8));
		attributes.put("BallDirection", new DoubleAttribute("Direction", -85));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 53.5));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 17));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("KICK")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A5"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 19.2));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 24.6));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", -17));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("DASH")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A6"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 15.9));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 22.3));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", -18));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("DASH")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A7"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 14.5));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 20.7));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", -20));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("DASH")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A8"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 11));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", null));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 44.8));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -5));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("DASH")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A9"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 10));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 61.3));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", -31));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", 41.4));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", 43));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("DASH")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A10"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 45.7));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 96.6));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 2));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 55.6));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -37));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", 55.6));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", 40));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("DASH")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A11"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 50.4));
		attributes.put("BallDirection", new DoubleAttribute("Direction", -1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 101.5));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 14));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 75.4));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -24));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", 46.2));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", 40));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("TURN")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A12"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 41.4));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 0));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 90.1));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 18));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 65.1));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -27));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("TURN")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A13"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 14.5));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 15));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 60.1));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 27));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("TURN")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A14"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 41.4));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 3));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 94.7));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 4));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 55.1));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -36));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", 53.5));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", 43));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("TURN")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A15"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 23.2));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 0));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 76.9));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 2));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("TURN")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A16"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 12));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 24));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", null));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", 42.7));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", -40));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("TURN")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A17"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", null));
		attributes.put("BallDirection", new DoubleAttribute("Direction", null));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 26.3));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 2));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("TURN")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "A18"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 3.5));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 56.1));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 4));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("DASH")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		displayTable(testList);

		area.append("The calculated estimate of TestEstimate1 is...\n");
		// Calculate the Action of the Testing action 1
		Algorithm temp_alg1 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate1"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 2));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 22));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 63.8));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", -2));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("NOACTION")));

		double index = temp_alg1.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Action"));
		SoccerAction test = SoccerAction.values()[(int) Math.round(index)];

		// Check the error on the Action
		double error = temp_alg1.calcError(index, 1);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate1"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 2));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 22));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 63.8));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", -2));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("KICK")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);

		area.append("The calculated estimate of TestEstimate2 is...\n");
		// Calculate the Action of the Testing action 2
		Algorithm temp_alg2 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate2"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 2));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 22));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 63.8));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", -2));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("NOACTION")));

		index = temp_alg2.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Action"));
		test = SoccerAction.values()[(int) Math.round(index)];

		// Check the error on the Action
		error = temp_alg2.calcError(index, 1);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate2"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 2));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 22));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 63.8));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", -2));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("KICK")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);
		
		area.append("The calculated estimate of TestEstimate3 is...\n");
		// Calculate the Action of the Testing action 3
		Algorithm temp_alg3 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate3"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 1.9));
		attributes.put("BallDirection", new DoubleAttribute("Direction", -167));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 63.8));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 31));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 39.1));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -41));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("NOACTION")));

		index = temp_alg3.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Action"));
		test = SoccerAction.values()[(int) Math.round(index)];

		// Check the error on the Action
		error = temp_alg3.calcError(index, 1);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate3"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 1.9));
		attributes.put("BallDirection", new DoubleAttribute("Direction", -167));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 63.8));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 31));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 39.1));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -41));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("KICK")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);
		
		area.append("The calculated estimate of TestEstimate4 is...\n");
		// Calculate the Action of the Testing action 4
		Algorithm temp_alg4 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate4"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 14.5));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 0));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 32.8));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 5));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("NOACTION")));

		index = temp_alg4.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Action"));
		test = SoccerAction.values()[(int) Math.round(index)];

		// Check the error on the Action
		error = temp_alg4.calcError(index, 3);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate4"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 14.5));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 0));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 32.8));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 5));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("TURN")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);
		
		area.append("The calculated estimate of TestEstimate5 is...\n");
		// Calculate the Action of the Testing action 5
		Algorithm temp_alg5 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate5"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", null));
		attributes.put("BallDirection", new DoubleAttribute("Direction", null));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 55.6));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 2));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("NOACTION")));

		index = temp_alg5.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Action"));
		test = SoccerAction.values()[(int) Math.round(index)];

		// Check the error on the Action
		error = temp_alg5.calcError(index, 3);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate4"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", null));
		attributes.put("BallDirection", new DoubleAttribute("Direction", null));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 55.6));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 2));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("TURN")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);
		
		area.append("The calculated estimate of TestEstimate6 is...\n");
		// Calculate the Action of the Testing action 6
		Algorithm temp_alg6 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate6"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 41.4));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 2));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 95.6));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 1));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 55.1));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -38));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", 55.1));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", 40));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("NOACTION")));

		index = temp_alg6.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Action"));
		test = SoccerAction.values()[(int) Math.round(index)];

		// Check the error on the Action
		error = temp_alg6.calcError(index, 3);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate6"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 41.4));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 2));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 95.6));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 1));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 55.1));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -38));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", 55.1));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", 40));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("TURN")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);
		
		area.append("The calculated estimate of TestEstimate7 is...\n");
		// Calculate the Action of the Testing action 7
		Algorithm temp_alg7 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate7"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 19.2));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 11));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 85.8));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 12));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 47.5));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -34));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("NOACTION")));

		index = temp_alg7.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Action"));
		test = SoccerAction.values()[(int) Math.round(index)];

		// Check the error on the Action
		error = temp_alg7.calcError(index,3);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate7"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 19.2));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 11));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 85.8));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 12));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 47.5));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -34));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("TURN")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);
		
		
		area.append("The calculated estimate of TestEstimate8 is...\n");
		// Calculate the Action of the Testing action 8
		Algorithm temp_alg8 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate8"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 23.2));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 42.7));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", -4));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("NOACTION")));

		index = temp_alg8.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Action"));
		test = SoccerAction.values()[(int) Math.round(index)];

		// Check the error on the Action
		error = temp_alg8.calcError(index,3);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate8"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 23.2));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 42.7));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", -4));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("DASH")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);
		
		
		area.append("The calculated estimate of TestEstimate9 is...\n");
		// Calculate the Action of the Testing action 9
		Algorithm temp_alg9 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate9"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 21.1));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 41.9));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", -4));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("NOACTION")));

		index = temp_alg9.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Action"));
		test = SoccerAction.values()[(int) Math.round(index)];

		// Check the error on the Action
		error = temp_alg9.calcError(index,3);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate9"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 21.1));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 41.9));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", -4));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("DASH")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);
		
		area.append("The calculated estimate of TestEstimate10 is...\n");
		// Calculate the Action of the Testing action 10
		Algorithm temp_alg10 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate10"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 50.4));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 104.5));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 1));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 61.9));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -33));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", 61.9));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", 35));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("NOACTION")));

		index = temp_alg7.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Action"));
		test = SoccerAction.values()[(int) Math.round(index)];

		// Check the error on the Action
		error = temp_alg10.calcError(index,3);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate10"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 50.4));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 104.5));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", 1));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 61.9));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -33));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", 61.9));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", 35));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("DASH")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);
		
		area.append("The calculated estimate of TestEstimate11 is...\n");
		// Calculate the Action of the Testing action 11
		Algorithm temp_alg11 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate11"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 17.4));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 0));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 91));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", -3));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", 52.9));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", 52.9));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("NOACTION")));

		index = temp_alg11.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Action"));
		test = SoccerAction.values()[(int) Math.round(index)];

		// Check the error on the Action
		error = temp_alg11.calcError(index,2);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate11"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 17.4));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 0));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", 91));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", -3));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", 52.9));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", 39));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("TURN")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);
	
		
		area.append("The calculated estimate of TestEstimate12 is...\n");
		// Calculate the Action of the Testing action 12
		Algorithm temp_alg12 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate12"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 10));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", null));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 44.4));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -9));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("NOACTION")));

		index = temp_alg12.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Action"));
		test = SoccerAction.values()[(int) Math.round(index)];

		// Check the error on the Action
		error = temp_alg12.calcError(index,2);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate12"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 10));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", null));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 44.4));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -9));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("DASH")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);
		
		area.append("The calculated estimate of TestEstimate13 is...\n");
		// Calculate the Action of the Testing action 13
		Algorithm temp_alg13 = new Algorithm();
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate13"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 10));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", null));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 44.4));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -8));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("NOACTION")));

		index = temp_alg13.computeEstimate(testList,
				new TestingExample(attributes.get("Name").getStringValue(), attributes, "Action"));
		test = SoccerAction.values()[(int) Math.round(index)];

		// Check the error on the Action
		error = temp_alg13.calcError(index,2);
		attributes = new HashMap<String, Attribute>();
		attributes.put("Name", new StringAttribute("name", "TestEstimate13"));
		attributes.put("BallDistance", new DoubleAttribute("Distance", 10));
		attributes.put("BallDirection", new DoubleAttribute("Direction", 1));
		attributes.put("GoalDistance", new DoubleAttribute("Distance", null));
		attributes.put("GoalDirection", new DoubleAttribute("Direction", null));
		attributes.put("FlagsFCTDist", new DoubleAttribute("Distance", 44.4));
		attributes.put("FlagsFCTDire", new DoubleAttribute("Direction", -8));
		attributes.put("FlagsFCBDist", new DoubleAttribute("Distance", null));
		attributes.put("FlagsFCBDire", new DoubleAttribute("Direction", null));
		attributes.put("Action", new EnumAttribute("Action", Enums.valueOf("DASH")));
		testList.add(new TrainingExample(attributes.get("Name").getStringValue(), attributes));

		area.append("The percent error is: " + error + "%\n");
		displayTable(testList);
	}

	public boolean displayTable(ArrayList<knnComparible> list) {

		if (list.isEmpty()) {
			area.append("There are currently no houses in the database.\n");
			return false;
		} else {
			for (int a = 0; a < list.size(); a++) {
				area.append(list.get(a).toString());
				area.append("\n");
				// System.out.println(list.get(a).toString());

			}
		}
		area.append("\n\n");
		// System.out.println("\n\n");
		return true;
	}

	/**
	 * Empty the specified list
	 * 
	 * @param list:
	 *            the list to empty
	 */
	public static void clearList(ArrayList<knnComparible> list) {

		list.clear();

	}

}
