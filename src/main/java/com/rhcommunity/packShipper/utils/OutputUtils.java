package com.rhcommunity.packShipper.utils;

import java.util.List;
import java.util.stream.Collectors;
import com.mobiquityinc.exception.APIException;

/**
 * Utility class to handle output
 * 
 * @author Mohd.Rahil Shaikh
 *
 */
public class OutputUtils
{
	/**
	 * prepare , separated list and uses - to indicate empty list
	 * 
	 * @param indexes to format
	 * @return String representing output in desired format
	 * @throws APIException
	 */
	public static String prepareOutput(List<Integer> indexes) throws APIException
	{
		if (CollectionUtils.isListEmpty(indexes))
			throw new APIException("invalid output");
		String output = indexes.stream()
				.mapToInt(i -> i)
				.sorted()
				.mapToObj(index -> Integer.toString(index))
				.collect(Collectors.joining(","));
		return output.isEmpty() ? "-" : output;
	}
}
