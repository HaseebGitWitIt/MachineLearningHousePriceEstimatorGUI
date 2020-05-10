package Model;
import java.util.ArrayList;
import java.util.HashMap;

/**
* A calculator that will calculate a distance when
* given a list of calculators to use for each attribute.
**/
public class Calculator extends DistanceCalculator {
	// A list of the type of calculator and the attributes to use it on
	// Ex. <"Euclidean", {"Square Feet", "Age", "Price"}>
	private HashMap<String, ArrayList<String>> calculatorTypes = null;
	
	/**
	* Constructor
	* Make a new Calculator from the given values
	**/
	public Calculator(knnComparible obj1, knnComparible obj2, String keyToExclude, HashMap<String, ArrayList<String>> newCalculatorTypes) {
		super(obj1, obj2, keyToExclude);
		calculatorTypes = newCalculatorTypes;
	}

	/**
	* Calculates the distance from the two objects using 
	* this calculator's  calculatorTypes HashMap
	**/
	@Override
	public void calcDistance(knnComparible obj1, knnComparible obj2, String keyToExclude) {
		if (calculatorTypes == null) {
			// If calculatorTypes is null, just use Euclidean for everything
  			calculatorTypes = new HashMap<String, ArrayList<String>>();
  			calculatorTypes.put("Euclidean", obj1.getAttributeNames());
  		}
		
		double distVal = 0;
		
		DistanceCalculator calc = null;
		
		// Get the attribute names for each type of calculator
  		ArrayList<String> manhatten = calculatorTypes.get("Manhatten");
  		ArrayList<String> euclidean = calculatorTypes.get("Euclidean");
  		ArrayList<String> minkowski3 = calculatorTypes.get("Minkowski (3)");
  		ArrayList<String> minkowski4 = calculatorTypes.get("Minkowski (4)");
  		ArrayList<String> minkowski5 = calculatorTypes.get("Minkowski (5)");
  		ArrayList<String> polar = calculatorTypes.get("Polar");
  		
		// If there are attributes using the manhatten calculator,
		// calculate the distances of those values using a manhatten calculator
  		if (manhatten != null) {
  		
	  		ArrayList<Double> manhattenDistance = new ArrayList<Double>();
	  		ArrayList<Double> manhattenDistance2 = new ArrayList<Double>();
	  		
			// Get all of the values for the given names
	  		for (String name : manhatten) {
	  			if (!name.equals(keyToExclude)){
		  			manhattenDistance.add(obj1.getValueFromName(name));
		  			manhattenDistance2.add(obj2.getValueFromName(name));
	  			}
	  		}
	  		
			// Create a new calculator and calculate the distance
	  		calc = new ManhattanDistanceCalculator(obj1, obj2, keyToExclude);
	  		distVal = distVal + calc.distanceMetric(manhattenDistance, manhattenDistance2);
  		}
  		
		// Repeat the same as above for Euclidean
  		if (euclidean != null) {
	  		ArrayList<Double> euclideanDistance = new ArrayList<Double>();
	  		ArrayList<Double> euclideanDistance2 = new ArrayList<Double>();
	  		
	  		for (String name : euclidean) {
	  			if (!name.equals(keyToExclude)) {
		  			euclideanDistance.add(obj1.getValueFromName(name));
		  			euclideanDistance2.add(obj2.getValueFromName(name));
	  			}
	  		}
	  		
	  		calc = new EuclideanDistanceCalculator(obj1, obj2, keyToExclude);
	  		distVal = distVal + calc.distanceMetric(euclideanDistance, euclideanDistance2);
  		}
  		
		// Repeat the same as above for Minkowski with q = 3
  		if (minkowski3 != null) {
	  		ArrayList<Double> minkowski3Distance = new ArrayList<Double>();
	  		ArrayList<Double> minkowski3Distance2 = new ArrayList<Double>();
	  		
	  		for (String name : minkowski3) {
	  			if (!name.equals(keyToExclude)) {
		  			minkowski3Distance.add(obj1.getValueFromName(name));
		  			minkowski3Distance2.add(obj2.getValueFromName(name));
	  			}
	  		}
	  		
	  		calc = new MinkowskiDistanceCalculator(obj1, obj2, keyToExclude, 3);
	  		distVal = distVal + calc.distanceMetric(minkowski3Distance, minkowski3Distance2);
  		}
  		
		// Repeat the same as above for Minkowski with q = 4
  		if (minkowski4 != null) {
	  		ArrayList<Double> minkowski4Distance = new ArrayList<Double>();
	  		ArrayList<Double> minkowski4Distance2 = new ArrayList<Double>();
	  		
	  		for (String name : minkowski4) {
	  			if (!name.equals(keyToExclude)) {
		  			minkowski4Distance.add(obj1.getValueFromName(name));
		  			minkowski4Distance2.add(obj2.getValueFromName(name));
	  			}
	  		}
	  		
	  		calc = new MinkowskiDistanceCalculator(obj1, obj2, keyToExclude, 4);
	  		distVal = distVal + calc.distanceMetric(minkowski4Distance, minkowski4Distance2);
  		}
  		
		// Repeat the same as above for Minkowski with q = 5
  		if (minkowski5 != null) {
	  		ArrayList<Double> minkowski5Distance = new ArrayList<Double>();
	  		ArrayList<Double> minkowski5Distance2 = new ArrayList<Double>();
	  		
	  		for (String name : minkowski5) {
	  			if (!name.equals(keyToExclude)) {
		  			minkowski5Distance.add(obj1.getValueFromName(name));
		  			minkowski5Distance2.add(obj2.getValueFromName(name));
	  			}
	  		} 
	  		
	  		calc = new MinkowskiDistanceCalculator(obj1, obj2, keyToExclude, 5);
	  		distVal = distVal + calc.distanceMetric(minkowski5Distance, minkowski5Distance2);
  		}
  		
		// Repeat the same as above for Polar
  		if (polar != null) {
	  		ArrayList<Double> polarDistance = new ArrayList<Double>();
	  		ArrayList<Double> polarDistance2 = new ArrayList<Double>();
	  		
	  		for (String name : polar) {
	  			if (!name.equals(keyToExclude)) {
		  			polarDistance.add(obj1.getValueFromName(name));
		  			polarDistance2.add(obj2.getValueFromName(name));
	  			}
	  		} 
	  		
	  		calc = new PolarCoordinateDistanceCalculator(obj1, obj2, keyToExclude);
	  		distVal = distVal + calc.distanceMetric(polarDistance, polarDistance2);
  		}
  		
  		distVal = Math.sqrt(distVal);
  		
		// Set the distance in the super class
  		super.distance = distVal;
		
	}

	/**
	* Calculates the distance between two ArrayLists using this
	* class' distance metric
	**/ 
	@Override
	public double distanceMetric(ArrayList<Double> set1, ArrayList<Double> set2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
