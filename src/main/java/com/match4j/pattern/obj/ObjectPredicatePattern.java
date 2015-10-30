package com.match4j.pattern.obj;

import java.util.function.Function;
import java.util.function.Predicate;

import com.match4j.main.AbstractPattern;
import com.match4j.main.Pattern;

public class ObjectPredicatePattern<I, O> extends AbstractPattern<I, O> implements Pattern<I, O> {
	private final Predicate<I> predicate;

	public ObjectPredicatePattern(Predicate<I> predicate, Function<I, O> function) {
		super(function);
		this.predicate = predicate;
	}

	@Override
	public boolean matches(I input) {
		return predicate.test(input);
	}

}
