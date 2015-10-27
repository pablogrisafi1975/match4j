package com.match4j.pattern.obj;

import com.match4j.main.Pattern;

public class OtherwiseThrowPattern<O> implements Pattern<O> {
	final RuntimeException runtimeException;

	public OtherwiseThrowPattern(RuntimeException runtimeException) {
		this.runtimeException = runtimeException;
	}

	@Override
	public boolean matches(Object value) {
		return true;
	}

	@Override
	public O apply(Object value) {
		throw runtimeException;
	}

}
