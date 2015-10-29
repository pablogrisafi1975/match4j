package com.match4j.pattern.obj;

import com.match4j.main.Pattern;

public class OtherwiseThrowPattern<I, O> implements Pattern<I, O> {
	final RuntimeException runtimeException;

	public OtherwiseThrowPattern(RuntimeException runtimeException) {
		this.runtimeException = runtimeException;
	}

	@Override
	public boolean matches(I value) {
		return true;
	}

	@Override
	public O apply(I value) {
		throw runtimeException;
	}

}
