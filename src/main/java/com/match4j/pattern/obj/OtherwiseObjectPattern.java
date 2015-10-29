package com.match4j.pattern.obj;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class OtherwiseObjectPattern<I, O> implements Pattern<I, O> {

	private Function<I, O> function;

	public OtherwiseObjectPattern(Function<I, O> function) {
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
