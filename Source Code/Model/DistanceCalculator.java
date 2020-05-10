package Model;
import java.util.ArrayList;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 *
 * DistanceCalculator Class
 * -The interface for distance calculators
 * 
 */
public abstract class DistanceCalculator {
	private knnComparible obj1, obj2; // The objects to calculate the distances between
	protected double distance; // The distance between obj1 and obj2
	private double[] weights; // The weights to use in the distance calculation (not currently implemented)
	private String keyToExclude;
	private double nullHandler = 1; // The value to add to the distance when a null value is encountered
	
	/**
	 * Constructor
	 * @param obj1: The first object for the distance calculation
	 * @param obj2: The second object for the distance calculation
	 */
	public DistanceCalculator(knnComparible obj1, knnComparible obj2, String keyToExclude) {
		this.obj1 = obj1;
		this.obj2 = obj2;
		this.keyToExclude = keyToExclude;
		calcDistance(obj1, obj2, keyToExclude);
	}
	
	/**
	 * Abstract function
	 * Calculates the distance between the two objects
	 * @param obj1: The first object to be used in the distance calculation
	 * @param obj2: The second object for the distance calculation
	 */
	public abstract void calcDistance(knnComparible obj1, knnComparible obj2, String keyToExclude);
	
	/**
	* Abstract function
	* Calculates the distance between two ArrayLists using this classes distance metrices
	* @param set1: The first arrayList to compare
	* @param set2: The second ArrayList to compare
	* @return: double containing the calculated distance
	**/
	public abstract double distanceMetric(ArrayList<Double> set1, ArrayList<Double> set2);
	
	/**
	 * Returns the distance between the two objects
	 * Re-runs the calculation before returning the distance to ensure it is accurate
	 * @return
	 */
	public double getDistance() {
		calcDistance(obj1, obj2, keyToExclude);
		return(distance);
	}
	
	/**
	 * Returns the first object from the distance calculation
	 * @return: knnComparible the first object from the distance calculation
	 */
	public knnComparible getObj1() {
		return(obj1);
	}
	
	/**
	 * Returns the second object from the distance calculation
	 * @return knnComparible the second object from the distance calculation
	 */
	public knnComparible getObj2() {
		return(obj2);
	}
	
	/**
	 * Sets the first object to the given object
	 * Re-runs the distance calculation after changing the object
	 * @param newObject1: The new obj1 for the calculation
	 */
	public void setObj1(knnComparible newObject1) {
		obj2 = newObject1;
		calcDistance(obj1, obj2, keyToExclude);
	}
	
	/**
	 * Sets the second object to the given object
	 * Re-runs the distance calculation after changing the object
	 * @param newObject2: The new obj2 for the calculation
	 */
	public void setObj2(knnComparible newObject2) {
		obj2 = newObject2;
		calcDistance(obj1, obj2, keyToExclude);
	}
	
	/**
	* Returns the nullHandler value
	* @return: double containing the nullHandler value
	**/
	public double getNullHandler() {
		return(nullHandler);
	}
	
	/**
	* Sets the nullHandler value
	* @param newNullHandler: The new nullHandler value
	**/
	public void setNullHandler(double newNullHandler) {
		nullHandler = newNullHandler;
	}
}
