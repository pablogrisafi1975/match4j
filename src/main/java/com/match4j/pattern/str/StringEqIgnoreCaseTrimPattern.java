package com.match4j.pattern.str;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class StringEqIgnoreCaseTrimPattern<O> implements Pattern<String, O> {
	private final String value;
	private final Function<String, O> function;

	public StringEqIgnoreCaseTrimPattern(String value, Function<String, O> function) {
		this.value = trimToEmpty(value);
		this.function = function;
	}

	@Override
	public boolean matches(String input) {
		return value == "" && input == null || trimToEmpty((String) input).equalsIgnoreCase(value);
	}

	@Override
	public O apply(String input) {
		return function.apply(input);
	}

	private static String trimToEmpty(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

}
