package com.example;

import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

		Person person = new Person();

		System.out.println("Case 1:\n");

		Map<String, Set<String>> constraintViolations1 = Person.VALIDATOR.validate(person);
		constraintViolations1.forEach((key, value) -> {
			System.out.println(key);
			value.forEach(m -> System.out.printf("    %s\n", m));
		});

		person.setAge(5);

		System.out.println("\nCase 2:\n");

		Map<String, Set<String>> constraintViolations2 = Person.VALIDATOR.validate(person);
		constraintViolations2.forEach((key, value) -> {
			System.out.println(key);
			value.forEach(m -> System.out.printf("    %s\n", m));
		});
	}

}
