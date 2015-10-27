package com.match4j.pattern.obj;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class ObjectTypeIsPattern<T, O> implements Pattern<O> {
	private final Class<T> type;
	private final Function<T, O> function;

	public ObjectTypeIsPattern(Class<T> type, Function<T, O> function) {
		this.type = type;
		this.function = function;
	}

	@Override
	public boolean matches(Object input) {
		return input != null && type.isAssignableFrom(input.getClass());
	}

	@SuppressWarnings("unchecked")
	@Override
	public O apply(Object input) {
		return function.apply((T) input);
	}
}
