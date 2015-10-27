package com.match4j.builder;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import com.match4j.main.Pattern;
import com.match4j.main.PatternMatching;
import com.match4j.pattern.IntegerEqPattern;
import com.match4j.pattern.IntegerPattern;
import com.match4j.pattern.OtherwiseIntegerPattern;

public class TypedMatchingForInteger<O> {
	private final Integer input;
	final List<Pattern<O>> patterns = new LinkedList<>();

	public TypedMatchingForInteger(Integer input, Pattern<O> pattern) {
		this.input = input;
		patterns.add(pattern);
	}

	public TypedMatchingForInteger<O> caseEq(Integer value, Function<Integer, O> function) {
		Pattern<O> pattern = new IntegerEqPattern<>(value, function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForInteger<O> caseEq(int value, Function<Integer, O> function) {
		Pattern<O> pattern = new IntegerEqPattern<>(value, function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForInteger<O> caseGt(int value, Function<Integer, O> function) {
		Pattern<O> pattern = new IntegerPattern<>(o -> o instanceof Integer && (Integer) o > value, function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForInteger<O> caseLt(int value, Function<Integer, O> function) {
		Pattern<O> pattern = new IntegerPattern<>(o -> o instanceof Integer && (Integer) o < value, function);
		patterns.add(pattern);
		return this;
	}

	public O otherwise(Function<Integer, O> function) {
		Pattern<O> pattern = new OtherwiseIntegerPattern<>(function);
		patterns.add(pattern);
		return new PatternMatching<>(patterns).apply(input);
	}

	public TypedMatchingForInteger<O> casePred(Predicate<Integer> predicate, Function<Integer, O> function) {
		Pattern<O> pattern = new IntegerPattern<>(predicate, function);
		patterns.add(pattern);
		return this;
	}

}
