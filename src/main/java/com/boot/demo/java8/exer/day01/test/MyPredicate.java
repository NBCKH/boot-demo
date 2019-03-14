package com.boot.demo.java8.exer.day01.test;

@FunctionalInterface
public interface MyPredicate<T> {

	public boolean test(T t);
	
}
