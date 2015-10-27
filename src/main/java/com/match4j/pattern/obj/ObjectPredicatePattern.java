package com.match4j.pattern.obj;

import java.util.function.Function;
import java.util.function.Predicate;

import com.match4j.main.Pattern;

public class ObjectPredicatePattern<O> implements Pattern<O> {
	private final Predicate<Object> predicate;
	private final Function<Object, O> function;

	public ObjectPredicatePattern(Predicate<Object> predicate, Function<Object, O> function) {
		this.predicate = predicate;
		this.function = function;
	}

	@Override
	public boolean matches(Object input) {
		return predicate.test(input);
	}

	@Override
	public O apply(Object input) {
		return function.apply(input);
	}

}
