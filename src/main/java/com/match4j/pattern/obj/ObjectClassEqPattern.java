package com.match4j.pattern.obj;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class ObjectClassEqPattern<T, O> implements Pattern<O> {
	private final Class<T> clazz;
	private final Function<T, O> function;

	public ObjectClassEqPattern(Class<T> clazz, Function<T, O> function) {
		if (clazz.isInterface() || clazz.isAnnotation()) {
			throw new IllegalArgumentException("caseClassEq can only match classes, neither interfaces nor annotations");
		}
		this.clazz = clazz;
		this.function = function;
	}

	@Override
	public boolean matches(Object input) {
		return input != null && input.getClass().equals(clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public O apply(Object input) {
		return function.apply((T) input);
	}
}
