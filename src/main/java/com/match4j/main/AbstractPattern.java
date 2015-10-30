package com.match4j.main;

import java.util.function.Function;

public abstract class AbstractPattern<I, O> implements Pattern<I, O> {

	private final Function<I, O> function;

	public AbstractPattern(Function<I, O> function) {
		this.function = function;
	}

	@Override
	public O apply(I input) {
		return function.apply(input);
	}

}
