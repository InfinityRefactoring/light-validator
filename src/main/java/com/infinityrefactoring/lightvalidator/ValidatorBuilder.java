package com.infinityrefactoring.lightvalidator;

import static java.util.Objects.requireNonNull;
import static java.util.function.Function.identity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

public class ValidatorBuilder<T> {

	private final List<Validation<T, ?>> VALIDATIONS;

	public ValidatorBuilder() {
		VALIDATIONS = new ArrayList<>();
	}

	public Validator<T> build() {
		return new DefaultValidator<>(VALIDATIONS);
	}

	public ValidatorBuilder<T> test(ConstraintValidator<? super T> validator) {
		return test("", identity(), validator);
	}

	public <R> ValidatorBuilder<T> test(String path, Function<? super T, R> getter, ConstraintValidator<? super R> validator) {
		VALIDATIONS.add(new Validation<>(path, getter, validator));
		return this;
	}

	public static class DefaultValidator<T> implements Validator<T> {

		private final List<Validation<T, ?>> VALIDATIONS;

		public DefaultValidator(List<Validation<T, ?>> validations) {
			VALIDATIONS = requireNonNull(validations);
		}

		@Override
		public Map<String, Set<String>> validate(T value) {
			Map<String, Set<String>> constraintViolations = new LinkedHashMap<>();
			for (Validation<T, ?> validation : VALIDATIONS) {
				validation.validate(value, constraintViolations);
			}
			return constraintViolations;
		}

	}

	private static class Validation<T, R> {

		private final String PATH;
		private final Function<? super T, R> GETTER;
		private final ConstraintValidator<? super R> VALIDATOR;

		public Validation(String path, Function<? super T, R> getter, ConstraintValidator<? super R> validator) {
			PATH = path;
			GETTER = getter;
			VALIDATOR = validator;
		}

		public boolean validate(T value, Map<String, Set<String>> constraintViolations) {
			R v = GETTER.apply(value);
			if (VALIDATOR.test(v)) {
				return true;
			}
			String message = VALIDATOR.getConstraintViolationMessage(v);
			constraintViolations.computeIfAbsent(PATH, k -> new TreeSet<>()).add(message);
			return false;
		}

	}

}
