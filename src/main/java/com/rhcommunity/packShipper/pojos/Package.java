package com.rhcommunity.packShipper.pojos;

/**
 * Package POJO
 * 
 * @author Mohd.Rahil Shaikh
 *
 */
public class Package
{
	private int index;
	private int weight;
	private double cost;

	public Package(int index, int weight, double cost)
	{
		super();
		this.index = index;
		this.weight = weight;
		this.cost = cost;
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	public double getCost()
	{
		return cost;
	}

	public void setCost(double cost)
	{
		this.cost = cost;
	}

	@Override
	public String toString()
	{
		return "Package [index=" + index + ", weight=" + weight + ", cost=" + cost + "]";
	}

}
