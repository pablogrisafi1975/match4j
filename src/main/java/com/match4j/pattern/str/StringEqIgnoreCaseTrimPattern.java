package com.match4j.pattern.str;

import java.util.function.Function;

import com.match4j.main.AbstractPattern;
import com.match4j.main.Pattern;

public class StringEqIgnoreCaseTrimPattern<O> extends AbstractPattern<String, O> implements Pattern<String, O> {
	private final String value;

	public StringEqIgnoreCaseTrimPattern(String value, Function<String, O> function) {
		super(function);
		this.value = trimToEmpty(value);
	}

	@Override
	public boolean matches(String input) {
		return value == "" && input == null || trimToEmpty((String) input).equalsIgnoreCase(value);
	}

	private static String trimToEmpty(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

}
