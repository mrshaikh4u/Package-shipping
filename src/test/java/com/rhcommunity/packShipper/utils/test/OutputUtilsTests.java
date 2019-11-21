package com.rhcommunity.packShipper.utils.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.mobiquityinc.exception.APIException;
import com.rhcommunity.packShipper.utils.OutputUtils;

public class OutputUtilsTests
{

	@Rule
	public ExpectedException thrown;

	@Before
	public void setUp() throws Exception
	{
		thrown = ExpectedException.none();
	}

	@Test
	public void prepareOutput_nullInput_ExceptionThrown()
	{

		thrown.expect(APIException.class);
		try
		{
			OutputUtils.prepareOutput(null);
		}
		catch (APIException e)
		{
			assertTrue(true);
		}

	}

	@Test
	public void prepareOutput_validInput_StringPrepared()
	{
		try
		{
			String output = OutputUtils.prepareOutput(Arrays.asList(1, 2, 3));
			assertEquals("1,2,3", output);
		}
		catch (APIException e)
		{
			assertFalse(true);
		}

	}

}
