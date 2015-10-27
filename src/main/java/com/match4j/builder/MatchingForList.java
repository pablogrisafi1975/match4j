package com.match4j.builder;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import com.match4j.main.Pattern;
import com.match4j.pattern.ListEmptyPattern;
import com.match4j.pattern.ListHeadTailPattern;

public class MatchingForList<E> {
	private final List<E> input;

	public MatchingForList(List<E> input) {
		this.input = input;
	}

	public <O> TypedMatchingForList<E, O> caseEmpty(Supplier<O> supplier) {
		final Pattern<O> pattern = new ListEmptyPattern<>(supplier);
		return new TypedMatchingForList<E, O>(input, pattern);
	}

	public <O> TypedMatchingForList<E, O> caseHeadTail(BiFunction<E, List<E>, O> bifunction) {
		final Pattern<O> pattern = new ListHeadTailPattern<>(bifunction);
		return new TypedMatchingForList<E, O>(input, pattern);
	}

}
