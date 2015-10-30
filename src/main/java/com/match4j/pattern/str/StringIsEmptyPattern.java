package com.match4j.pattern.str;

import java.util.function.Function;

import com.match4j.main.AbstractPattern;
import com.match4j.main.Pattern;

public class StringIsEmptyPattern<O> extends AbstractPattern<String, O> implements Pattern<String, O> {

	public StringIsEmptyPattern(Function<String, O> function) {
		super(function);
	}

	@Override
	public boolean matches(String input) {
		return input == null || input.length() == 0;
	}

}
