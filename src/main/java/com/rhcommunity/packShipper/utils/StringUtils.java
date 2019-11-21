package com.rhcommunity.packShipper.utils;

/**
 * This is Utility class supporting common String operations
 * 
 * @author Mohd.Rahil Shaikh
 *
 */
public class StringUtils
{

	/**
	 * To check if input String is null or empty
	 * 
	 * @param input - String to check
	 * @return true if input String is null or empty else false
	 */
	public static boolean isStrNullOrEmpty(String input)
	{
		return input == null || input.length() == Constants.ZERO_INT || input.trim()
				.equalsIgnoreCase(Constants.EMPTY_STRING);
	}
}
