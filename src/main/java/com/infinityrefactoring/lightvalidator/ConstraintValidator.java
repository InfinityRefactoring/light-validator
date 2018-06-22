package com.infinityrefactoring.lightvalidator;

import java.util.function.Predicate;

public interface ConstraintValidator<T> extends Predicate<T> {

	public static <T> DelegatedConstraintValidator<T> of(String message, Predicate<T> predicate) {
		return new DelegatedConstraintValidator<>(message, predicate);
	}

	@Override
	public ConstraintValidator<T> and(Predicate<? super T> other);

	public ConstraintValidator<T> and(String message, Predicate<? super T> other);

	public String getConstraintViolationMessage(T value);

	@Override
	public ConstraintValidator<T> negate();

	public ConstraintValidator<T> negate(String message);

	@Override
	public ConstraintValidator<T> or(Predicate<? super T> other);

	public ConstraintValidator<T> or(String message, Predicate<? super T> other);

}
