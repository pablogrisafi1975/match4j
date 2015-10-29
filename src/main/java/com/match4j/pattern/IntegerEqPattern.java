package com.match4j.pattern;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class IntegerEqPattern<O> implements Pattern<Integer, O> {
	private final Integer value;
	private final Function<Integer, O> function;

	public IntegerEqPattern(int value, Function<Integer, O> function) {
		this.value = value;
		this.function = function;
	}

	@Override
	public boolean matches(Integer input) {
		return value.equals(input);
	}

	@Override
	public O apply(Integer input) {
		return function.apply(input);
	}

}