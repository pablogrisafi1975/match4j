package com.match4j.pattern.obj;

import java.util.Objects;
import java.util.function.Function;

import com.match4j.main.Pattern;

public class ObjectNePattern<I, O> implements Pattern<I, O> {
	private final I value;
	private final Function<I, O> function;

	public ObjectNePattern(I value, Function<I, O> function) {
		this.value = value;
		this.function = function;
	}

	@Override
	public boolean matches(I input) {
		return !Objects.equals(value, input);
	}

	@Override
	public O apply(I input) {
		return function.apply(input);
	}

}
