package com.match4j.pattern;

import java.util.function.Function;
import java.util.function.Predicate;

import com.match4j.main.Pattern;

public class IntegerPattern<O> implements Pattern<Integer, O> {
	private final Predicate<Integer> predicate;
	private final Function<Integer, O> function;

	public IntegerPattern(Predicate<Integer> predicate, Function<Integer, O> function) {
		this.predicate = predicate;
		this.function = function;
	}

	@Override
	public boolean matches(Integer input) {
		return predicate.test(input);
	}

	@Override
	public O apply(Integer input) {
		return function.apply(input);
	}

}