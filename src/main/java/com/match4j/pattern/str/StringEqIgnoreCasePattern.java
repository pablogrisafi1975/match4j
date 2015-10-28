package com.match4j.pattern.str;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class StringEqIgnoreCasePattern<O> implements Pattern<O> {
	private final String value;
	private final Function<Object, O> function;

	@SuppressWarnings("unchecked")
	public StringEqIgnoreCasePattern(String value, Function<String, O> function) {
		this.value = value;
		this.function = (Function<Object, O>) (Function<?, O>) function;
	}

	@Override
	public boolean matches(Object input) {
		return input == null && value == null || input instanceof String && ((String) input).equalsIgnoreCase(value);
	}

	@Override
	public O apply(Object input) {
		return function.apply(input);
	}

}
