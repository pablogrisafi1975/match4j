package com.match4j.builder;

import java.util.function.Function;

import com.match4j.main.Pattern;
import com.match4j.pattern.IntegerEqPattern;

public class MatchingForInteger {
	private final Integer input;

	public MatchingForInteger(int input) {
		this.input = input;
	}

	public MatchingForInteger(Integer input) {
		this.input = input;
	}

	public <O> TypedMatchingForInteger<O> caseEq(Integer value, Function<Integer, O> function) {
		final Pattern<O> pattern = new IntegerEqPattern<>(value, function);
		return new TypedMatchingForInteger<O>(input, pattern);
	}

	public <O> TypedMatchingForInteger<O> caseEq(int value, Function<Integer, O> function) {
		final Pattern<O> pattern = new IntegerEqPattern<>(value, function);
		return new TypedMatchingForInteger<O>(input, pattern);
	}

}
