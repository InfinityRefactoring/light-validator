package com.infinityrefactoring.lightvalidator;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

public class DelegatedConstraintValidator<T> implements ConstraintValidator<T> {

	private String TEMPLATE_MESSAGE;
	private Predicate<T> PREDICATE;

	public DelegatedConstraintValidator(String message, Predicate<T> predicate) {
		TEMPLATE_MESSAGE = requireNonNull(message);
		PREDICATE = requireNonNull(predicate);
	}

	@Override
	public ConstraintValidator<T> and(Predicate<? super T> other) {
		return new DelegatedConstraintValidator<>(TEMPLATE_MESSAGE, PREDICATE.and(other));
	}

	@Override
	public ConstraintValidator<T> and(String message, Predicate<? super T> other) {
		return new DelegatedConstraintValidator<>(message, PREDICATE.and(other));
	}

	@Override
	public String getConstraintViolationMessage(T value) {
		return String.format(TEMPLATE_MESSAGE, value);
	}

	@Override
	public ConstraintValidator<T> negate() {
		return new DelegatedConstraintValidator<>(TEMPLATE_MESSAGE, PREDICATE.negate());
	}

	@Override
	public ConstraintValidator<T> negate(String message) {
		return new DelegatedConstraintValidator<>(message, PREDICATE.negate());
	}

	@Override
	public ConstraintValidator<T> or(Predicate<? super T> other) {
		return new DelegatedConstraintValidator<>(TEMPLATE_MESSAGE, PREDICATE.or(other));
	}

	@Override
	public ConstraintValidator<T> or(String message, Predicate<? super T> other) {
		return new DelegatedConstraintValidator<>(message, PREDICATE.or(other));
	}

	@Override
	public boolean test(T t) {
		return PREDICATE.test(t);
	}

}
