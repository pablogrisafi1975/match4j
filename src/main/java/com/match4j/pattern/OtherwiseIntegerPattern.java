package com.match4j.pattern;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class OtherwiseIntegerPattern<O> implements Pattern<O> {

	private Function<Integer, O> function;

	public OtherwiseIntegerPattern(Function<Integer, O> function) {
		this.function = function;
	}

	@Override
	public boolean matches(Object value) {
		return true;
	}

	@Override
	public O apply(Object value) {
		return function.apply((Integer) value);
	}

}
