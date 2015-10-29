package com.match4j.builder;

import java.util.function.Function;
import java.util.function.Predicate;

import com.match4j.main.Pattern;
import com.match4j.pattern.obj.ObjectClassEqPattern;
import com.match4j.pattern.obj.ObjectEqPattern;
import com.match4j.pattern.obj.ObjectNePattern;
import com.match4j.pattern.obj.ObjectPredicatePattern;
import com.match4j.pattern.obj.ObjectTypeIsPattern;

public class MatchingForObject<I> {
	private final I input;

	public MatchingForObject(I input) {
		this.input = input;
	}

	public <O> TypedMatchingForObject<I, O> casePred(Predicate<I> predicate, Function<I, O> function) {
		final Pattern<I, O> pattern = new ObjectPredicatePattern<>(predicate, function);
		return new TypedMatchingForObject<I, O>(input, pattern);
	}

	public <O> TypedMatchingForObject<I, O> caseEq(I value, Function<I, O> function) {
		final Pattern<I, O> pattern = new ObjectEqPattern<>(value, function);
		return new TypedMatchingForObject<I, O>(input, pattern);
	}

	public <O> TypedMatchingForObject<I, O> caseNe(I value, Function<I, O> function) {
		final Pattern<I, O> pattern = new ObjectNePattern<>(value, function);
		return new TypedMatchingForObject<I, O>(input, pattern);
	}

	public <T extends I, O> TypedMatchingForObject<I, O> caseClassEq(Class<T> clazz, Function<T, O> function) {
		final Pattern<I, O> pattern = new ObjectClassEqPattern<>(clazz, function);
		return new TypedMatchingForObject<I, O>(input, pattern);
	}

	public <T extends I, O> TypedMatchingForObject<I, O> caseTypeIs(Class<T> type, Function<T, O> function) {
		final Pattern<I, O> pattern = new ObjectTypeIsPattern<>(type, function);
		return new TypedMatchingForObject<I, O>(input, pattern);
	}

}
