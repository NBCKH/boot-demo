package com.boot.demo.java8.exer.day01.test;

import com.boot.demo.java8.exer.model.Employee;

public class FilterEmployeeForSalary implements MyPredicate<Employee> {

	@Override
	public boolean test(Employee t) {
		return t.getSalary() >= 5000;
	}

}
