package com.rhcommunity.packShipper.utils;

/**
 * This is constant class to declare all constants used in project
 * 
 * @author Mohd.Rahil Shaikh
 *
 */
public final class Constants
{
	public static final String EMPTY_STRING = "";
	public static final String HIPHEN_STRING = "-";
	public static final String COLON_STRING = ":";
	public static int ZERO_INT = 0;
	public static char EMPTY_CHAR = ' ';
	public static int TWO_INT = 2;
	public static final int MAX_ITEMS_IN_LINE = 15;
	public static final int MAX_WEIGHT = 100 * 100;
	public static final int MAX_COST = 100 * 100;
	public static final String IDX = "index";
	public static final String WEIGHT = "weight";
	public static final String COST = "cost";
	public static final String PACKAGE_FORMAT = "\\((?<" + IDX + ">\\d+)\\,(?<" + WEIGHT + ">\\d+(\\.\\d{1,2})?)\\,€(?<" + COST + ">\\d+(\\.\\d{1,2})?)\\)";

}
