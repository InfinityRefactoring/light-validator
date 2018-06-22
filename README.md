# Light Validator

## What is it?

An lightweight utility library for validate objects.

## Requirements for use

* Java 8, or newer

## Use example:

This example show how validate instances of the Person class:

```java
package com.example;

import com.infinityrefactoring.lightvalidator.Validator;
import com.infinityrefactoring.lightvalidator.Validators;

public class Person {

	public static final Validator<Person> VALIDATOR = Validator.build(Person.class)
			.test("age", Person::getAge, Validators.isBetweenThan(1, 10))
			.test("name", Person::getName, Validators.isNotNull())
			.build();

	private int age;

	private String name;

	public Integer getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

}

```

Running the validation:

```java
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

```

## Licensing

**InfinityRefactoring/light-validator** is provided and distributed under the [Apache Software License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

Refer to *LICENSE* for more information.
