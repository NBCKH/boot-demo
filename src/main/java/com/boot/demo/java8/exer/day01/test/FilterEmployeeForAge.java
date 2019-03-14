package com.boot.demo.java8.exer.day01.test;

import com.boot.demo.java8.exer.model.Employee;

public class FilterEmployeeForAge implements MyPredicate<Employee>{

	@Override
	public boolean test(Employee t) {
		return t.getAge() <= 35;
	}

}
