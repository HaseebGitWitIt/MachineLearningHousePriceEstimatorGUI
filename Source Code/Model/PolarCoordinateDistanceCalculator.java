package Model;
import java.util.ArrayList;

/**
* A distance calculator that uses a polar coordinate distance metric
**/
public class PolarCoordinateDistanceCalculator extends DistanceCalculator{

	/**
	* Constructor
	* Creates a new PolarDistanceCalculator
	* @param obj1: The first object to compare
	* @param obj2: The second object to compare
	* @param keyToExclude: The key to exclude from the distance calculation
	**/
	public PolarCoordinateDistanceCalculator(knnComparible obj1, knnComparible obj2, String keyToExclude) {
		super(obj1, obj2, keyToExclude);
		// TODO Auto-generated constructor stub
	}

	/**
	* Calculates the distance of the two given objects
	* @param obj1: The first object to compare
	* @param obj2: The second object to compare
	* @param keyToExclude: The key to exclude from the calculations
	**/
	@Override
	public void calcDistance(knnComparible obj1, knnComparible obj2, String keyToExclude) {
		// TODO Auto-generated method stub
		super.distance = 0;
	}

	/**
	* Calcultes teh distance between two ArrayLists using the Polar distance metric
	* @param set1: The first ArrayList to compare
	* @param set2: The second ArrayList to compare
	* @return: THe calculated distance
	**/
	@Override
	public double distanceMetric(ArrayList<Double> set1, ArrayList<Double> set2) {
		double distVal = 0;
		
		// Ensure each ArrayList has an even number of elements
		// Add 0.0 to the set if it has an odd number of attributes
		if ((set1.size() % 2 != 0) || (set2.size() % 2 != 0)) {
			if (set1.size() % 2 != 0) {
				ArrayList<Double> temp = new ArrayList<Double>();
				temp.addAll(set1);
				temp.add(0.0);
				set1 = temp;
			}
			
			if (set2.size() % 2 != 0) {
				ArrayList<Double> temp = new ArrayList<Double>();
				temp.addAll(set2);
				temp.add(0.0);
				set2 = temp;
			}
		}
		
		if (set1.size() <= set2.size()) {
			int i = 0;
			
			while (i < set1.size()) {
				double r1;
				double r2;
				double theta1;
				double theta2;
				
				if ((set1.get(i) != null) && (set2.get(i) != null) && (set1.get(i + 1) != null) && (set2.get(i + 1) != null)) {
					theta1 = set1.get(i);
					theta2 = set2.get(i);
						
					r1 = set1.get(i + 1);
					r2 = set2.get(i + 1);
						
					double distance = 0;
						
					distance = distance + Math.pow(r1, 2);
					distance = distance + Math.pow(r2, 2);
						
					distance = distance - 2 * r1 * r2 * Math.cos(theta2 - theta1);
						
					distVal = distVal + Math.sqrt(distance);
				} else {
					distVal = distVal + super.getNullHandler();
				}	
					
				i = i + 2;
			}
		} else {
			int i = 0;
				
			while (i < set2.size()) {
				double r1;
				double r2;
				double theta1;
				double theta2;
				
				if ((set1.get(i) != null) && (set2.get(i) != null) && (set1.get(i + 1) != null) && (set2.get(i + 1) != null)) {
					
					theta1 = set1.get(i);
					theta2 = set2.get(i);
						
					r1 = set1.get(i + 1);
					r2 = set2.get(i + 1);
						
					double distance = 0;
						
					distance = distance + Math.pow(r1, 2);
					distance = distance + Math.pow(r2, 2);
						
					distance = distance - 2 * r1 * r2 * Math.cos(theta2 - theta1);
						
					distVal = distVal + Math.sqrt(distance);
				} else {
					distVal = distVal + super.getNullHandler();
				}
					
				i = i + 2;
			}
		}

		return(distVal);
	}

}
