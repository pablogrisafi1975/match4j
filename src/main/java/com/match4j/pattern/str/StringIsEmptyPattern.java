package com.match4j.pattern.str;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class StringIsEmptyPattern<O> implements Pattern<O> {
	private final Function<Object, O> function;

	@SuppressWarnings("unchecked")
	public StringIsEmptyPattern(Function<String, O> function) {
		this.function = (Function<Object, O>) (Function<?, O>) function;
	}

	@Override
	public boolean matches(Object input) {
		if (input instanceof String) {
			String str = (String) input;
			return str.length() == 0;
		}
		return input == null;

	}

	@Override
	public O apply(Object input) {
		return function.apply(input);
	}

}
