package com.match4j.pattern.str;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class StringEqIgnoreCasePattern<O> implements Pattern<String, O> {
	private final String value;
	private final Function<String, O> function;

	public StringEqIgnoreCasePattern(String value, Function<String, O> function) {
		this.value = value;
		this.function = function;
	}

	@Override
	public boolean matches(String input) {
		return input == null && value == null || input != null && input.equalsIgnoreCase(value);
	}

	@Override
	public O apply(String input) {
		return function.apply(input);
	}

}
