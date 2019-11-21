package com.rhcommunity.packShipper.utils;

import java.util.List;

/**
 * This is utility class to handle common collections tasks
 * 
 * @author Mohd.Rahil Shaikh
 *
 */
public class CollectionUtils
{
	public static <T> boolean isListEmpty(List<T> list)
	{
		return list == null || list.isEmpty();
	}
}
