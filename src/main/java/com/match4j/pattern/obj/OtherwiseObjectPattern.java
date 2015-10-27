package com.match4j.pattern.obj;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class OtherwiseObjectPattern<O> implements Pattern<O> {

	private Function<Object, O> function;

	public OtherwiseObjectPattern(Function<Object, O> function) {
		this.function = function;
	}

	@Override
	public boolean matches(Object value) {
		return true;
	}

	@Override
	public O apply(Object value) {
		return function.apply(value);
	}

}
