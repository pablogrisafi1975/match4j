package com.match4j.pattern.str;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class StringEqIgnoreCaseTrimPattern<O> implements Pattern<O> {
	private final String value;
	private final Function<Object, O> function;

	@SuppressWarnings("unchecked")
	public StringEqIgnoreCaseTrimPattern(String value, Function<String, O> function) {
		this.value = trimToEmpty(value);
		this.function = (Function<Object, O>) (Function<?, O>) function;
	}

	@Override
	public boolean matches(Object input) {
		return value == "" && input == null || input instanceof String
				&& trimToEmpty((String) input).equalsIgnoreCase(value);
	}

	@Override
	public O apply(Object input) {
		return function.apply(input);
	}

	private static String trimToEmpty(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

}
