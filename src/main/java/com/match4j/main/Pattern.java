package com.match4j.main;

import java.util.function.Function;

public interface Pattern<O> extends Function<Object, O> {
	boolean matches(Object value);

	O apply(Object value);
}