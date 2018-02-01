package com.prabha.inventory.util;

/**
 * The Class StringUtil.
 */
public class StringUtil {


	/**
	 * Checks if string is empty.
	 *
	 * @param inputString the input string
	 * @return true, if is string empty
	 */
	public static boolean isStringEmpty(String inputString)
	{
		boolean isEmpty = false;
		if(inputString == null || inputString.isEmpty())
		{
			isEmpty = true;
		}
		return isEmpty;
	}
	
	/**
	 * Checks if it has expected argument length.
	 *
	 * @param inputString the input string
	 * @param expectedLength the expected length
	 * @return true, if is valid argument length
	 */
	public static boolean isValidArgumentLength(String[] inputString, 
			int expectedLength)
	{
		boolean isValid = false;
		if(inputString != null && inputString.length == expectedLength)
		{
			isValid = true;
		}
		return isValid;
	}
	
	/**
	 * Parses the string.
	 *
	 * @param inputString the input string
	 * @return the string[]
	 */
	public static String[] parseString(String inputString)
	{
		String parsedString[] = null;
		if(inputString != null)
		{
			parsedString = inputString.split(" ");
		}
		return parsedString;
	}

}
