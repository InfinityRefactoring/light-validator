package com.infinityrefactoring.lightvalidator;

import static com.infinityrefactoring.lightvalidator.ConstraintValidator.of;

public class Validators {

	private static final ConstraintValidator<Object> IS_NULL = of("Must be null", o -> o == null);
	private static final ConstraintValidator<Object> IS_NOT_NULL = IS_NULL.negate("Must be not null");

	public static <T extends Comparable<T>> ConstraintValidator<T> isBetweenThan(T min, T max) {
		return of("Must be between " + min + " and " + max, v -> (v.compareTo(min) >= 0) && (v.compareTo(max) <= 0));
	}

	public static <T extends Comparable<T>> ConstraintValidator<T> isEqualThan(T value) {
		return of("Must be equal than " + value, v -> v.compareTo(value) == 0);
	}

	public static <T extends Comparable<T>> ConstraintValidator<T> isGreaterOrEqualThan(T value) {
		return of("Must be greater or equal than " + value, v -> v.compareTo(value) >= 0);
	}

	public static <T extends Comparable<T>> ConstraintValidator<T> isGreaterThan(T value) {
		return of("Must be greater than " + value, v -> v.compareTo(value) > 0);
	}

	public static <T extends Comparable<T>> ConstraintValidator<T> isLessOrEqualThan(T value) {
		return of("Must be less or equal than " + value, v -> v.compareTo(value) <= 0);
	}

	public static <T extends Comparable<T>> ConstraintValidator<T> isLessThan(T value) {
		return of("Must be less than " + value, v -> v.compareTo(value) < 0);
	}

	public static ConstraintValidator<Object> isNotNull() {
		return IS_NOT_NULL;
	}

	public static ConstraintValidator<Object> isNull() {
		return IS_NULL;
	}

}
