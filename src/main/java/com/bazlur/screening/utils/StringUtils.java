package com.bazlur.screening.utils;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
public class StringUtils {

	private static final String ELIGIBLE_CHARS = "ABDEFGHJKLMRSTUVWXYabdefhjkmnrstuvwxy23456789";

	public static String generateRandomString(int stringLength) {
		char[] chars = ELIGIBLE_CHARS.toCharArray();

		return IntStream.range(0, stringLength)
			.boxed()
			.map(operand -> {
				double randomValue = Math.random();
				int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
				Character characterToShow = chars[randomIndex];

				return characterToShow.toString();
			}).collect(Collectors.joining());
	}


	public static boolean isEmpty(String str) {

		return ((str == null) || (str.trim().length() == 0));
	}

	public static boolean isNotEmpty(String str) {

		return !isEmpty(str);
	}
}
