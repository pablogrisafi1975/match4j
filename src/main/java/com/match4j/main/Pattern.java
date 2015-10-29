package com.match4j.main;

import java.util.function.Function;

public interface Pattern<I, O> extends Function<I, O> {
	boolean matches(I value);

	O apply(I value);
}