package com.rhcommunity.packShipper.pojos;

import java.util.List;

/**
 * Parsed line POJO
 * 
 * @author Mohd.Rahil Shaikh
 *
 */
public class ParsedLine
{
	private int maxWeight;
	private List<Package> packages;

	public ParsedLine(int maxWeightArg, List<Package> packagesArg)
	{
		this.maxWeight = maxWeightArg;
		this.packages = packagesArg;
	}

	public int getMaxWeight()
	{
		return maxWeight;
	}

	public void setMaxWeight(int maxWeight)
	{
		this.maxWeight = maxWeight;
	}

	public List<Package> getPackages()
	{
		return packages;
	}

	public void setPackages(List<Package> packages)
	{
		this.packages = packages;
	}

}
