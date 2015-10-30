package com.match4j.pattern.obj;

import java.util.Objects;
import java.util.function.Function;

import com.match4j.main.AbstractPattern;
import com.match4j.main.Pattern;

public class ObjectEqPattern<I, O> extends AbstractPattern<I, O> implements Pattern<I, O> {
	private final I value;

	public ObjectEqPattern(I value, Function<I, O> function) {
		super(function);
		this.value = value;
	}

	@Override
	public boolean matches(I input) {
		return Objects.equals(value, input);
	}

}
