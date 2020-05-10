package Model;


/**
 * Team Name: HACZ 
 * Contributed Group Member: @author (Haseeb, Abraham, Calvin, Ryan)
 * 
 * A class containing all of the enums used for this program
 */
public class Enums {
	// Age enum used for house example
	public enum Age {
		NEW, OLD;
	}
	
	// SoccerActions enum used for Soccer example
	public enum SoccerActions {
		NOACTION, KICK, DASH, TURN;
	}
	
	/**
	* Returns a string array containing every enum from all enum classes
	* @return: String[] containing all of the enums
	**/
	public static String[] getValues() {
		String[] toReturn = new String[Age.values().length + SoccerActions.values().length];
		
		int j = 0;
		int i = 0;
		
		// Loop through Age and add every enum value
		while (i < Age.values().length) {
			toReturn[j] = Age.values()[i].toString();
			j = j + 1;
			i = i + 1;
		}
		
		i = 0;
		
		// Loop through SoccerActions and add every enum value
		while (i < SoccerActions.values().length) {
			toReturn[j] = SoccerActions.values()[i].toString();
			j = j + 1;
			i = i + 1;
		}
		
		return(toReturn); // Return the string array
	}
	
	/**
	* Return the enum of the given string
	* @param toFind: The String to convert to an Enum
	* @return: Enum containing the String value as an enum
	**/
	public static Enum<?> valueOf(String toFind) {
		int i = 0;
		
		// Look for the string in Age
		while (i < Age.values().length) {
			if (Age.values()[i].toString().equals(toFind)) { // Enum found
				return(Age.valueOf(toFind)); // Return the num
			}
			i = i + 1;
		}
		
		i = 0;
		
		// Look for the string in SoccerActions
		while (i < SoccerActions.values().length) {
			if (SoccerActions.values()[i].toString().equals(toFind)) { // Enum found
				return(SoccerActions.valueOf(toFind)); // Return the enum
			}
			i = i + 1;
		}
		
		return(null); // Return null if enum not found
			
	}
	
	/**
	* Return the Enum array containing the given string
	* @param toFind: The string to look for
	* @return: Enum[] containing the string
	**/
	public static Enum[] getSetContainingEnum(String toFind) {
		int i = 0;
		
		// Look for the string in Age
		while (i < Age.values().length) {
			if (Age.values()[i].toString().equals(toFind)) { // String found
				return(Age.values()); // Return the Age array
			}
			i = i + 1;
		}
		
		i = 0;
		
		// Look for the String in SoccerActions
		while (i < SoccerActions.values().length) {
			if (SoccerActions.values()[i].toString().equals(toFind)) { // String found
				return(SoccerActions.values()); // Return the SoccerActions array
			}
			i = i + 1;
		}
		
		return(null);
	}
	
	/**
	* Returns the index of the given String in its corresponging array
	* @param toFind: The String to look for
	* @return: The index of the String to look for
	**/
	public static int getIndex(String toFind) {
		Enum[] toIndex = getSetContainingEnum(toFind); // Get the enum array containing the String
		
		int i = 0;
		
		// Look for the String in the array
		while (i < toIndex.length) {
			if (toIndex[i].equals(toFind)) { // String found
				return(i);
			}
			i = i + 1;
		}
		
		return(-1); // Return -1 if the String is not found
	}
}
