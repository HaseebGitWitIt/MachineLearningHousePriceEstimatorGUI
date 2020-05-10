package Model;
import java.util.ArrayList;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 *
 * EuclideanDistanceCalculator Class
 * - A distance calculator that uses a Minkowski distance method
 * - Distance = (Sum of (absolute value of (xi - yi)) ^ q) ^ (1 / q)
 * 
 */
public class MinkowskiDistanceCalculator extends DistanceCalculator {
	private int q;
	
	/**
	 * Constructor
	 * @param obj1: The first object used in the distance calculation
	 * @param obj2: The second object used in the distance calculation
	 */
	public MinkowskiDistanceCalculator(knnComparible obj1, knnComparible obj2, String keyToExclude, int q) {
		super(obj1, obj2, keyToExclude);
		this.q = q;
	}
	
	/**
	 * Calculates the distance between the two given objects
	 * @param obj1: The first object for the distance calculation
	 * @param obj2: The second object for the distance calculation
	 */
	@Override
	public void calcDistance(knnComparible obj1, knnComparible obj2, String keyToExclude) {
		double distVal = 0;		
		
		if (q == 0) {
			q = 1;
		}
		
		// First get all of the standardizable values from the two objects
		// These will be used for the distance calculation
		ArrayList<Double> obj1Vals = obj1.getStandardizableValues();
		ArrayList<String> obj1Names = obj1.getAttributeNames();
		
		ArrayList<Double> obj2Vals = obj2.getStandardizableValues();
		ArrayList<String> obj2Names = obj2.getAttributeNames();
		
		//indexToExclude = obj1Vals.size() - 1;
		
		// Determine which object has more standardizable values
		// The two objects should be of the same type of object
		// If the two objects are the same type of object, then they should have the same amount of standardizable values
		if (obj1Vals.size() <= obj2Vals.size()) {
			int n = 0;
			
			// Loop through both standardizable value ArrayLists
			// Since this is a euclidean calculator, the distance uses the following calculation:
			//     distance = ((x1 - y1) ^ 2 + (x2 - y2) ^ 2 + ... + (xn - yn) ^ 2) ^ 0.5
			// The loop will add the (xn - yn) ^ 2 portions to the distance and then it will be square rooted after
			while (n < obj1Vals.size()) {
				if (!obj1Names.get(n).equals(keyToExclude)) {
					if ((obj1Vals.get(n) != null) && (obj2Vals.get(n) != null)) {
						distVal = distVal + Math.pow(obj1Vals.get(n) - obj2Vals.get(n), q);
					} else {
						distVal = distVal + super.getNullHandler();
					}
					
				}
				n = n + 1;
			}
		} else {
			int n = 0;
			
			// Same description as above
			while (n < obj2Vals.size()) {
				if (!obj2Names.get(n).equals(keyToExclude)) {
					if ((obj1Vals.get(n) != null) && (obj2Vals.get(n) != null)) {
						distVal = distVal + Math.pow(obj1Vals.get(n) - obj2Vals.get(n), q);
					} else {
						distVal = distVal + super.getNullHandler();
					}					
				}
				n = n + 1;
			}
		}
		
		double check = 1.0 / q;
		distVal = Math.pow(distVal, (1.0 / q)); // Square root the distance
		super.distance = distVal; // Set the new distance
	}

	/**
	* Calculates the distance between two ArrayLists using the Minkowski distance metric
	* @param set1: The first ArrayList to compare
	* @param set2: The second ArrayList to compare
	* @return: The calculated distance
	**/
	@Override
	public double distanceMetric(ArrayList<Double> set1, ArrayList<Double> set2) {
		double toReturn = 0;
		int i = 0;
		
		if (set1.size() >= set2.size()) {
			while (i < set2.size()) {
				if ((set1.get(i) != null) && (set2.get(i) != null)) {
					toReturn = toReturn + Math.pow(set1.get(i) - set2.get(i), q);
				} else {
					toReturn = toReturn + super.getNullHandler();
				}				
				i = i + 1;
			}
		} else {
			while (i < set1.size()) {
				if ((set1.get(i) != null) && (set2.get(i) != null)) {
					toReturn = toReturn + Math.pow(set1.get(i) - set2.get(i), q);
				} else {
					toReturn = toReturn + super.getNullHandler();
				}
				i = i + 1;
			}
		}
		
		return(toReturn);
	}

}
