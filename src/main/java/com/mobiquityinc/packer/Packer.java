package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mobiquityinc.exception.APIException;
import com.rhcommunity.packShipper.pojos.Package;
import com.rhcommunity.packShipper.utils.Constants;
import com.rhcommunity.packShipper.utils.InputUtils;
import com.rhcommunity.packShipper.utils.OutputUtils;

/**
 * 
 * This is the main API to invoke for returning correct package as per provided input file
 * inputPath has to be absolute path
 * 
 * Algorithm is to first validate provided file and then use BFS approach to find out best possible package as per below constraints
 * 1. total weight of the package should be <= package limit
 * 2. total cost should be as large as possible
 * 3. prefer lighter package when cost is same
 * 
 * @author Mohd.Rahil Shaikh
 *
 */
public class Packer
{
	// logger to log required events
	private static final Logger LOG = LoggerFactory.getLogger(Packer.class);

	// entry level method to pass absolute file path
	public static String pack(String inputPath) throws APIException
	{
		return InputUtils.readInputFile(inputPath)
				.stream()
				.map(parsedLine -> preparePackageToSendBFS(parsedLine.getMaxWeight(), parsedLine.getPackages()))
				.collect(Collectors.joining("\n"));
	}

	/**
	 * Using breadth first approach to get best possible package using given criteria
	 * it works like below
	 * for eg. input 75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)
	 * 
	 * it first add empty set to subSets , then for each subsequent package adds new package to each subset and if total weight is exceeding package limit
	 * don't add it to main subset , while adding subsets it also keep track of subset having max cost , and if cost are same it considers weight of subsets
	 * []
	 * [] [2]
	 * [] [2] [3] [2,3]
	 * [] [2] [3] [2,3] [4] [2 4] [3 4] [2 3 4 ]
	 * and so on .....
	 * 
	 * @param maxWeight - package limit
	 * @param packages - total available packages
	 * @return best possible package indexes in ascending order
	 */
	private static String preparePackageToSendBFS(int maxWeight, List<Package> packages)
	{

		double currentMaxCost = Integer.MIN_VALUE;
		List<Package> currentMaxArray = new ArrayList<>();
		List<List<Package>> subSets = new ArrayList<>();
		subSets.add(new ArrayList<>());
		for (Package packg : packages)
		{
			if (packg.getWeight() > maxWeight)
				continue;
			int n = subSets.size();
			for (int i = 0; i < n; i++)
			{
				List<Package> set = new ArrayList<>(subSets.get(i));
				int currentSetTotalWeight = getWeight(set) + packg.getWeight();
				if (currentSetTotalWeight > maxWeight)
					continue;
				set.add(packg);
				double currentSetTotalCost = getCost(set);
				if (currentSetTotalCost >= currentMaxCost)
				{
					if (currentSetTotalCost == currentMaxCost)
					{
						currentMaxArray = getWeight(set) < getWeight(currentMaxArray) ? set : currentMaxArray;
					}
					else
					{
						currentMaxArray = set;
					}
					currentMaxCost = getCost(currentMaxArray);
				}
				subSets.add(set);
			}
		}
		List<Integer> indexes = new ArrayList<Integer>();
		for (Package packg : currentMaxArray)
		{
			indexes.add(packg.getIndex());
		}
		try
		{
			return OutputUtils.prepareOutput(indexes);
		}
		catch (APIException e)
		{
			LOG.debug("failed for " + indexes.toString() + " with exception : " + e);
			return Constants.HIPHEN_STRING;
		}
	}

	private static double getCost(List<Package> currentMaxArray)
	{
		return currentMaxArray.stream()
				.mapToDouble(p -> p.getCost())
				.sum();
	}

	private static int getWeight(List<Package> set)
	{
		return set.stream()
				.mapToInt(p -> p.getWeight())
				.sum();
	}

}
