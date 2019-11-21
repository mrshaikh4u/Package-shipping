package com.rhcommunity.packShipper.utils;

import static com.rhcommunity.packShipper.utils.Constants.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatConversionException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.mobiquityinc.exception.APIException;
import com.rhcommunity.packShipper.pojos.Package;
import com.rhcommunity.packShipper.pojos.ParsedLine;

/**
 * This is utility class to handle inputs
 * 
 * @author Mohd.Rahil Shaikh
 *
 */
public class InputUtils
{

	/**
	 * Reads lines from input file and prepares List of parsed objects
	 * 
	 * @param inputPath -- absolute file path
	 * @return -- List of parsedLine
	 * @throws APIException
	 */
	public static List<ParsedLine> readInputFile(String inputPath) throws APIException
	{
		if (inputPath == null || StringUtils.isStrNullOrEmpty(inputPath))
			throw new APIException("invalid path");
		List<ParsedLine> parsedLines = new ArrayList<>();
		try (FileInputStream inputStream = new FileInputStream(inputPath))
		{
			try (Scanner scanner = new Scanner(inputStream))
			{
				while (scanner.hasNext())
				{
					parsedLines.add(validateAndReadLine(scanner.nextLine()));
				}
			}
		}
		catch (IOException e)
		{
			throw new APIException(e);
		}

		return parsedLines;
	}

	/**
	 * This is helper method to validate read line against expected pattern and prepare object out of input line
	 * 
	 * @param line -- line read from file
	 * @return - object prepared out of line read from input
	 * @throws APIException
	 */
	public static ParsedLine validateAndReadLine(String line) throws APIException
	{
		if (StringUtils.isStrNullOrEmpty(line))
		{
			throw new APIException("invalid input line");
		}
		String[] tokenized = line.split(COLON_STRING);
		if (tokenized.length != TWO_INT)
		{
			throw new APIException("Line " + line + " contains more than one `:`");
		}
		final int maxWeight;
		try
		{
			maxWeight = (int) (Double.parseDouble(tokenized[ZERO_INT]) * 100);
		}
		catch (NumberFormatException e)
		{
			throw new APIException("Invalid weight value for line " + line);
		}
		Pattern pattern = Pattern.compile(PACKAGE_FORMAT);
		Matcher matcher = pattern.matcher(tokenized[1]);
		int lastEnd = ZERO_INT;
		List<Package> packages = new ArrayList<>();

		while (matcher.find())
		{
			if (matcher.start() != lastEnd + 1 || tokenized[1].charAt(lastEnd) != EMPTY_CHAR)
			{
				throw new APIException(String.format("Right side of `:` must be in the following pattern (%s) separated by space", PACKAGE_FORMAT), line);
			}
			try
			{
				Integer index = Integer.valueOf(matcher.group(IDX));
				int weight = (int) (Double.valueOf(matcher.group(WEIGHT)) * 100);
				Double cost = Double.valueOf(matcher.group(COST));

				if (index > MAX_ITEMS_IN_LINE || index < 0)
				{
					throw new APIException(String.format("expected index range (1, %d)", MAX_ITEMS_IN_LINE), line);
				}

				if (weight > MAX_WEIGHT || weight < 0)
				{
					throw new APIException(String.format("expected weight range (0, %f)", MAX_WEIGHT), line);
				}

				if (cost > MAX_COST || cost < 0)
				{
					throw new APIException(String.format("expected cost range (0, %f)", MAX_COST), line);
				}

				packages.add(new Package(index, weight, cost));
			}
			catch (NumberFormatException | IllegalFormatConversionException e)
			{
				throw new APIException(e, line);
			}

			lastEnd = matcher.end();
		}

		if (lastEnd != tokenized[1].length())
		{
			throw new APIException("unexpected characters in the end of the line", line);
		}

		long[] indexes = packages.stream()
				.mapToLong(Package::getIndex)
				.toArray();

		for (int i = 0; i < indexes.length; i++)
		{
			if (indexes[i] != i + 1)
			{
				throw new APIException("The indexes in not order well, or some index is missing", line);
			}
		}
		return new ParsedLine(maxWeight, packages);
	}
}
