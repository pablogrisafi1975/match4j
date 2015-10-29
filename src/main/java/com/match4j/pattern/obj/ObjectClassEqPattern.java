package com.match4j.pattern.obj;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class ObjectClassEqPattern<I, T extends I, O> implements Pattern<I, O> {
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
	public boolean matches(I input) {
		return input != null && input.getClass().equals(clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public O apply(I input) {
		return function.apply((T) input);
	}
}
