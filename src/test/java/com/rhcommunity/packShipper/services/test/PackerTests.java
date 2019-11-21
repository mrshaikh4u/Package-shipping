package com.rhcommunity.packShipper.services.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;
import com.rhcommunity.packShipper.utils.InputUtils;

public class PackerTests
{

	@Rule
	public ExpectedException thrown;
	private static final String correctPath = "C:\\\\Users\\\\moshaikh\\\\mobiquity_package\\\\package-shipper\\\\src\\\\test\\\\resources\\\\testcase_input3.txt";

	@Before
	public void setUp() throws Exception
	{
		thrown = ExpectedException.none();
	}

	@Test
	public void pack_validFilePath_correctPackageAsStringReturned()
	{
		String output;
		try
		{
			output = Packer.pack(correctPath);
			assertEquals("8,9", output);
		}
		catch (APIException e)
		{
			assertFalse(true);
		}

	}

	@Test
	public void pack_InvalidFilePath_ExceptionThrown()
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

}
