package com.rhcommunity.packShipper.utils.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.mobiquityinc.exception.APIException;
import com.rhcommunity.packShipper.pojos.ParsedLine;
import com.rhcommunity.packShipper.utils.InputUtils;

public class InputUtilsTests
{

	@Rule
	public ExpectedException thrown;
	private final String testValidLine = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
	private final String testInValidLine = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) : (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";

	@Before
	public void setUp() throws Exception
	{
		thrown = ExpectedException.none();
	}

	@Test
	public void readInputFile_nullFilePath_throwsException()
	{
		thrown.expect(APIException.class);
		thrown.expectMessage("invalid path");
		try
		{
			InputUtils.readInputFile(null);
		}
		catch (APIException e)
		{
			assertTrue(true);
		}
	}

	@Test
	public void readInputFile_invalidFilePath_throwsException()
	{
		thrown.expect(APIException.class);
		try
		{
			InputUtils.readInputFile("invalidpath");
		}
		catch (APIException e)
		{
			assertTrue(true);
		}
	}

	@Test
	public void readInputFile_validFile_parsedObjectReturned() throws APIException
	{
		List<ParsedLine> output = InputUtils.readInputFile("C:\\Users\\moshaikh\\mobiquity_package\\package-shipper\\src\\test\\resources\\testcase_input1.txt");
		assertNotNull(output);
		assertFalse(output.isEmpty());
	}

	@Test
	public void validateAndReadLine_validLine_ObjectCreated() throws APIException
	{
		ParsedLine returnedObj = InputUtils.validateAndReadLine(testValidLine);
		assertNotNull(returnedObj);
		assertFalse(returnedObj.getPackages()
				.isEmpty());
	}

	@Test
	public void validateAndReadLine_InvalidLine_ExceptionThrown()
	{
		thrown.expect(APIException.class);
		try
		{
			InputUtils.validateAndReadLine(testInValidLine);
		}
		catch (APIException e)
		{
			assertTrue(true);
		}
	}

}
