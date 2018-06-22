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
