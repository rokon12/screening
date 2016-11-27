package com.bazlur.screening.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
public class StringUtilsTest {

	@Test
	public void generateRandomString() throws Exception {
		String randomString = StringUtils.generateRandomString(16);
		Assert.assertNotNull(randomString);
		Assert.assertEquals(16, randomString.length());
	}

	@Test
	public void isEmpty() throws Exception {
		String aString = null;
		Assert.assertTrue(StringUtils.isEmpty(aString));

		aString = "";
		Assert.assertTrue(StringUtils.isEmpty(aString));
	}

	@Test
	public void isNotEmpty() throws Exception {
		String aString = "Hello";
		Assert.assertTrue(StringUtils.isNotEmpty(aString));
	}
}