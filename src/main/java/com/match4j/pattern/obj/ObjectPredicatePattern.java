package com.match4j.pattern.obj;

import java.util.function.Function;
import java.util.function.Predicate;

import com.match4j.main.Pattern;

public class ObjectPredicatePattern<I, O> implements Pattern<I, O> {
	private final Predicate<I> predicate;
	private final Function<I, O> function;

	public ObjectPredicatePattern(Predicate<I> predicate, Function<I, O> function) {
		this.predicate = predicate;
		this.function = function;
	}

	@Override
	public boolean matches(I input) {
		return predicate.test(input);
	}

	@Override
	public O apply(I input) {
		return function.apply(input);
	}

}
