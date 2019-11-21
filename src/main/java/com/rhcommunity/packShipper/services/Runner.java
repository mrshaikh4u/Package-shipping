package com.rhcommunity.packShipper.services;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;

/**
 * Class to run as standalone application make sure to update filepath
 * 
 * @author Mohd.Rahil Shaikh
 *
 */
public class Runner
{
	public static void main(String[] args) throws APIException
	{
		String filePath = "C:\\Users\\moshaikh\\mobiquity_package\\package-shipper\\src\\test\\resources\\input.txt";
		System.out.println(Packer.pack(filePath));
	}
}
