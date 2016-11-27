package com.bazlur.screening.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
public class ValidationUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationUtils.class);

	public static void addFieldError(String objectName, String fieldName, String fieldValue, String errorCode, BindingResult result) {
		LOGGER.debug("Adding field error object's: {} field: {} with error code: {}", objectName, fieldName, errorCode);

		FieldError error = new FieldError(
			objectName,
			fieldName,
			fieldValue,
			false,
			new String[]{errorCode},
			new Object[]{},
			errorCode
		);

		result.addError(error);
		LOGGER.debug("Added field error: {} to binding result: {}", error, result);
	}
}
