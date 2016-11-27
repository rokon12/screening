package com.bazlur.screening.validation;

import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
public class ValidatorUtil {
	public static Object getFieldValue(Object object, String fieldName)
		throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);

		return field.get(object);
	}

	public static void addValidationError(String field, ConstraintValidatorContext context) {
		context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
			.addPropertyNode(field)
			.addConstraintViolation();
	}
}
