package com.match4j.pattern;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class OtherwiseIntegerPattern<O> implements Pattern<Integer, O> {

	private Function<Integer, O> function;

	public OtherwiseIntegerPattern(Function<Integer, O> function) {
		this.function = function;
	}

	@Override
	public boolean matches(Integer value) {
		return true;
	}

	@Override
	public O apply(Integer value) {
		return function.apply(value);
	}

}
