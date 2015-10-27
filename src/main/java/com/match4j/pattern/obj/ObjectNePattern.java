package com.match4j.pattern.obj;

import java.util.Objects;
import java.util.function.Function;

import com.match4j.main.Pattern;

public class ObjectNePattern<O> implements Pattern<O> {
	private final Object value;
	private final Function<Object, O> function;

	public ObjectNePattern(Object value, Function<Object, O> function) {
		this.value = value;
		this.function = function;
	}

	@Override
	public boolean matches(Object input) {
		return !Objects.equals(value, input);
	}

	@Override
	public O apply(Object input) {
		return function.apply(input);
	}

}
