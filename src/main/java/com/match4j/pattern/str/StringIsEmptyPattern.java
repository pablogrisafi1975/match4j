package com.match4j.pattern.str;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class StringIsEmptyPattern<O> implements Pattern<String, O> {
	private final Function<String, O> function;

	public StringIsEmptyPattern(Function<String, O> function) {
		this.function = function;
	}

	@Override
	public boolean matches(String input) {
		return input == null || input.length() == 0;

	}

	@Override
	public O apply(String input) {
		return function.apply(input);
	}

}
