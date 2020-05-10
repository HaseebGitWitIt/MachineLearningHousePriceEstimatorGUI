package Model;
import java.util.*;

/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 *
 * Algorithm Class
 * -Provides the algorithm in order to calculate the information for House/Room Estimation
 * 
 */
public class Algorithm {
	private double[] distanceWeights; // Weights used for each attribute when calculating distance	
	private String keyToExclude;
	
	/**
	 * Get the k closest houses to the house with the unknown price
	 * @param k : The number of houses to return in the array list
	 * @param houseList: The list of houses to look for the k closest houses in
	 * @param query: The house to check for distance
	 * @return: ArrayList with the k closest houses
	 */
	public ArrayList<knnComparible> knn(int k, ArrayList<knnComparible> houseList, knnComparible query) {
		// There needs to be at least 1 house to generate a price
		if (k <= 0) {
			System.out.println("K must be greater than 0");
			return null;
			//return;
		}
		
		// If there are not enough houses for the wanted k value, use all of the houses instead
		if (k > houseList.size()){
			k = houseList.size();
		}
		
		// Sort the houses by distance in the given array list using bubble sort
		int n = houseList.size();
		for (int i = 0; i < n - 1; i++){
			for (int j = 0; j < n - i - 1; j++){
				if (houseList.get(j).distance(query, distanceWeights, keyToExclude) > houseList.get(j + 1).distance(query, distanceWeights, keyToExclude)){
					knnComparible temp = houseList.get(j);
					houseList.set(j,houseList.get(j + 1));
					houseList.set(j + 1, temp);
				}
			}
		}
		
		// Return the k closest houses, sorted by distance
		ArrayList<knnComparible> kClosest = new ArrayList<knnComparible>();
		int num = 0;
		while (num < k){
			kClosest.add(houseList.get(num));
			num = num + 1;
		}
		
		return kClosest;
		
		
	}
	
	/**
	* Generates the weights used for each attribute when calculating the distance 
	* Currently, it weighs all of the attributes equally
	* @param houseList: The list of houses used to generate the number and magnitude of the weights
	* @return: double[] containing the weights for each attribute in sequential order
	*/
	public double[] generateAttributeWeights(ArrayList<knnComparible> houseList) {
		// Make an array to store the weight for each attribute
		double[] weights = new double[houseList.get(0).getStandardizableValues().size()];
		
		int n = 0;
		
		// The weights will all be equal initially
		while (n < weights.length) {
			weights[n] = 1;
			n = n + 1;
		}
		
		return(weights);
	}
	
	/**
	 * Returns the house closest to given house
	 * @param houseList: The ArrayList of the houses to check
	 * @param unknownPriceHouse: The house to check the distance from
	 * @return: knnComparible: The closest house to the query house
	 */
	public knnComparible minDistance(ArrayList<knnComparible> houseList, knnComparible unknownPriceHouse) {
		double minDistance = Double.MAX_VALUE;
		double distance;
		knnComparible closest = null;
		
		// Loop through all of the houses to find the closest
		for(knnComparible house: houseList) {
			distance = house.distance(unknownPriceHouse, distanceWeights, keyToExclude);
			if(distance < minDistance) { // House is closer than the current closest	
				minDistance = distance;
				closest = house;
			}
		}
		
		if (closest == null){
			System.out.println("Error: cannot find closest");
		}
		
		return closest;
	}
	
	/**
	 * Calculate the error between estimated price and actual price
	 * @param calcPrice: The calculated price
	 * @house: The house to compare the calculated price and actual price of
	 * @return: double: The error between the calculated price and actual price for
	 */
	public double calcError(double calcValue, double expectedValue) {
		double error = 0.0;
		
		//Using percent error = calc - theo / calc * 100
		error = (Math.abs((expectedValue - calcValue)) / Math.abs((1 + expectedValue))) * 100;
		return error;
	}
	
	/**
	* Change all attribute values to be between 0 and 1
	* @param houses: The ArrayList containing the houses to standardize the attributes of
	* @return: ArrayList<knnComparible>: An ArrayList containing the houses with the standardized values
	*/
	public ArrayList<knnComparible> standardizeValues(ArrayList<knnComparible> houses){
		// maxAndMin stores the maximum and minimum of each attribute
		// Stores them in the order min attribute 1, max attirubte 1, min attribute 2, etc.
		Double[] maxAndMin = new Double[houses.get(0).getStandardizableValues().size() * 2]; 
		ArrayList<Double> vals = houses.get(0).getStandardizableValues();
		
		int n = 0;
		int m = 0;
		
		// Initialize the min and max for each attribute to the first element in the knnComparible arraylist
		while (m < vals.size()) {
			maxAndMin[n] = vals.get(m);
			maxAndMin[n + 1] = vals.get(m);
			n = n + 2;
			m = m + 1;
		}
		
		// Get the min and max for each attribute
		for (knnComparible house: houses){
			n = 0;
			m = 0;
			vals = house.getStandardizableValues();
			
			while (m < vals.size()) {				
				if ((vals.get(m) != null) && ((maxAndMin[n] == null) || (vals.get(m) < maxAndMin[n]))) {
					// Current attribute is less than the current min
					if (maxAndMin[n] == null) {
						maxAndMin[n + 1] = vals.get(m);
					}
					maxAndMin[n] = vals.get(m);
				} else if ((vals.get(m) != null) && ((maxAndMin[n + 1] == null) || (vals.get(m) > maxAndMin[n + 1]))) {
					// Current attribute is greater than the current max
					if (maxAndMin[n + 1] == null) {
						maxAndMin[n] = vals.get(m);
					}
					maxAndMin[n + 1] = vals.get(m);
				}
				m = m + 1;
				n = n + 2;
			}
		}
		
		// Create an ArrayList for all of the houses with the standardized values
		ArrayList<knnComparible> standardizedHouseList = new ArrayList<knnComparible>();
		
		for (knnComparible house: houses) {
			// The objects are duplicated before being standardized so that the original objects
			// are unchanged. 
			standardizedHouseList.add(house.copy()); 
		}
		
		// Standardize all attributes in all of the houses in the database
		ArrayList<Double> temp = new ArrayList<Double>();
		
		for (knnComparible house: standardizedHouseList){	
			temp = new ArrayList<Double>();
			n = 0;
			m = 0;
			
			while (m < vals.size()) {
				if ((maxAndMin[n] == null) || (maxAndMin[n + 1] == null)) {
					if (house.getStandardizableValues().get(m) == null) {
						temp.add(null);
					} else {
						temp.add(1.0);
					}
				} else if ((maxAndMin[n + 1] - maxAndMin[n]) == 0) {
					// If max and min are equal, then all elements have the same value for the given attribute
					// so set the value to 1
					if (house.getStandardizableValues().get(m) == null) {
						temp.add(null);
					} else {
						temp.add(1.0);
					}
				} else {
					// Standardize the value by doing:
					// newVal = (oldVal - min) / (max - min)
					if (house.getStandardizableValues().get(m) == null) {
						temp.add(null);
					} else {
						temp.add((house.getStandardizableValues().get(m) - maxAndMin[n]) / (maxAndMin[n + 1] - maxAndMin[n]));
					}					
				}
				n = n + 2;
				m = m + 1;
			}
			
			house.setStandardizableValues(temp);
		}
		
		return(standardizedHouseList);
	}
	
	/**
	 * Get the coefficients for each house in the given ArrayList
	 * @param houseList: The ArrayList containing the houses to get all of the coefficients for
	 * @param unknownPriceHouse: The house to find the price for
	 * @return: double[] containing all of the coefficients for each house
	 */
	public double[] calculateCoefficients(ArrayList<knnComparible> houseList, knnComparible unknownPriceHouse) {
		double[] coeffs = new double[houseList.size()];
		
		if (coeffs.length > 0) {
			// There is at least one house
			coeffs[0] = 1; // Set the first house to have a coefficient of 1 initially
			
			// If there is more than 1 house, get the coefficients for all other houses
			if (coeffs.length > 1) {
				int n = 1;
				double x = 0;
				double totalFactor = 1;
				double divisor = 0;
				double remainingFactor = 0;
				
				// First, get the distance factors for all houses
				// The first house will have a factor of 1
				// For the next houses assign them factors for the relative distance they are from the previous house in the array
				// For example, if the current house is 10 times further from the query house, place a value of 10 in the coefficient array
				while (n < coeffs.length) {
					double factor = unknownPriceHouse.distance(houseList.get(n), distanceWeights, keyToExclude) / unknownPriceHouse.distance(houseList.get(n - 1), distanceWeights, keyToExclude); 
					coeffs[n] = factor;
					n = n + 1;
				}
				
				n = 0;
				
				// Next, get the total factor
				// All of the coefficients will be fractions
				// The total factor will be the numerator for the closest house
				while (n < coeffs.length) {
					totalFactor = totalFactor * coeffs[n];
					n = n + 1;
				}
				
				n = 0;
				remainingFactor = totalFactor;
				
				// Then, calculate the divisor
				// This will be the denominator for all of the coefficients
				while (n < coeffs.length) {
					divisor = divisor + remainingFactor / coeffs[n];
					remainingFactor = remainingFactor / coeffs[n];
					n = n + 1;
				}
				
				x = totalFactor / divisor;
				
				n = 1;
				coeffs[0] = x; // Get the coefficient for the closest house
				
				// Finally, get the coefficient for all of the other houses by dividing the previous coefficient
				// by the distance factor from step 1 for the current house
				while (n < coeffs.length) {
					coeffs[n] = coeffs[n - 1] / coeffs[n];
					n = n + 1;
				}
				
			}
		}
		
		return(coeffs);
	}
	
	
	/**
	 * Get the new house estimate using algorithm
	 * @param houseList: The database containing all of the known houses
	 * @param unknownPriceHouse: The house to calculate the price of
	 * @return: double: The calculated price of the unknown house
	 */
	public Double computeEstimate(ArrayList<knnComparible> houseList, TestingExample unknownPriceHouse) {
		keyToExclude = unknownPriceHouse.getKeyToCalculate();
		String keyToCalculate = unknownPriceHouse.getKeyToCalculate();
		
		Double price = null;
		
		/*NOTE:THIS IS A COMPLETE BASIC TO GIVE A PRICE IN CASE WE CANT FIND ALGORITHM
		       THAT ONLY USES SQUARE FEET
		*/
		
		//Get the price/sq feet
		//price = unknownPriceHouse.getSquareFeet() * pricePerSqFeet(closest);
		
		int k;
		
		// For k value, the square root of the total objects in the database will be used
		// as this is generally a good balance between computing time and accuracy. The only 
		// downside is that this only gives a good sample size when the number of elements is
		// above 100, becausek will then be at least 10. To fix this, if there are less than 100
		// elements, k will just be set to 10.
		if (houseList.size() >= 100){
			k = (int) Math.floor(Math.sqrt(houseList.size()));
		} else {
			k = 10;
		}
		
		ArrayList<knnComparible> temp = new ArrayList<knnComparible>();
		
		for (knnComparible house : houseList) {
			temp.add(house);
		}
		
		distanceWeights = generateAttributeWeights(temp);
		
		knnComparible standardUnknownPriceHouse;
		
		// Temporarily add the unknown house to the ArrayList so that it's attributes can be standardized
		temp.add(unknownPriceHouse);
		temp = standardizeValues(temp);
		standardUnknownPriceHouse = temp.get(temp.size() - 1);
		temp.remove(temp.size() - 1);
		
		// Get the k closest houses
		ArrayList<knnComparible> closestHouses = knn(k, temp, standardUnknownPriceHouse);
		ArrayList<knnComparible> closestHousesUnstandardized = new ArrayList<knnComparible>();
		
		for (knnComparible house : closestHouses) {
			int n = 0;
			
			while (n < houseList.size()) {
				if (houseList.get(n).getName().equals(house.getName())) {
					closestHousesUnstandardized.add(houseList.get(n));
				}
				n = n + 1;
			}
		}
		
		// Get the closest and furthest houses
		knnComparible closest = minDistance(temp, standardUnknownPriceHouse);
		knnComparible maxDistanceHouse = closestHouses.get(closestHouses.size() - 1);
		
		// Determine the distance between the closest and furthest houses
		double minimumDistance = standardUnknownPriceHouse.distance(closest, distanceWeights, keyToExclude);
		double maxDistance = standardUnknownPriceHouse.distance(maxDistanceHouse, distanceWeights, keyToExclude);
		// double distMinMax = maxDistance - minimumDistance;
		
		if (closest.distance(standardUnknownPriceHouse, distanceWeights, keyToExclude) == 0){
			// If the distance between the closest house and the given house is 0, that means
			// we already have the price for the house		
			closest = closestHousesUnstandardized.get(0);
			int index = closest.getAttributeNames().indexOf(keyToCalculate);
			Double value = closest.getStandardizedValue(index);
			
			if (value != null) {
				price = value;
			} else {
				price = null;
			}
		} else {
			int n = 0;
			// knnComparible currHouse;
			double[] coefficients = calculateCoefficients(closestHouses, standardUnknownPriceHouse);

			
			// Add the weight price of each house to the current price
			while (n < coefficients.length){
				knnComparible house = closestHousesUnstandardized.get(n);
				int index = closest.getAttributeNames().indexOf(keyToCalculate);
				Double value = house.getStandardizedValue(index);
				
				if (value != null) {
					if (price == null) {
						price = value * coefficients[n];
					} else {
						price = price + value * coefficients[n];
					}
				} 				
				
				n = n + 1;
			}
		}
		
		// double error = calcError(price, unknownPriceHouse);
		//System.out.println("Error is : " +error);				//Used to show error value
		return price;
		
	}
}
