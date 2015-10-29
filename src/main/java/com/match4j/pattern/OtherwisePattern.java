package com.match4j.pattern;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class OtherwisePattern<I, O> implements Pattern<I, O> {
	private final Function<Object, O> function;

	public OtherwisePattern(Function<Object, O> function) {
		this.function = function;
	}

	@Override
	public boolean matches(I value) {
		return true;
	}

	@Override
	public O apply(I value) {
		return function.apply(value);
	}

}
