package com.match4j.pattern.str;

import java.util.function.Function;

import com.match4j.main.AbstractPattern;
import com.match4j.main.Pattern;

public class StringEqIgnoreCasePattern<O> extends AbstractPattern<String, O> implements Pattern<String, O> {
	private final String value;

	public StringEqIgnoreCasePattern(String value, Function<String, O> function) {
		super(function);
		this.value = value;
	}

	@Override
	public boolean matches(String input) {
		return input == null && value == null || input != null && input.equalsIgnoreCase(value);
	}

}
