package com.infinityrefactoring.lightvalidator;

import java.util.Map;
import java.util.Set;

public interface Validator<T> {

	public static <T> ValidatorBuilder<T> build(Class<T> c) {
		return new ValidatorBuilder<>();
	}

	public Map<String, Set<String>> validate(T value);

}
