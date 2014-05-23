/**
 * Copyright 2014 Bertram André Nicolas
 */
package dk.fam_nicolas.pokedex;

/**
 * @author Bertram
 *
 */
public class Utilities
{
	/**
	 * Usefull for capitalizing names and the likes
	 * @param input a word or sentence where capitalization for each word is needed
	 * @return a string where each word has 1st letter uppercase, rest lowercase
	 */
	public static String capitalize(String input)
	{
		String outputString = "";
		String[] outputArray = input.toLowerCase().split(" ");
		
		for(String i:outputArray)
		{
			outputString += i.substring(0, 1).toUpperCase() + i.substring(1) + " ";
		}
		outputString = outputString.substring(0, outputString.length() - 1);
		
		return outputString;
	}
}
