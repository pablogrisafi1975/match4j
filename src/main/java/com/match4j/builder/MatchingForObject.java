package com.match4j.builder;

import java.util.function.Function;
import java.util.function.Predicate;

import com.match4j.main.Pattern;
import com.match4j.pattern.obj.ObjectClassEqPattern;
import com.match4j.pattern.obj.ObjectEqPattern;
import com.match4j.pattern.obj.ObjectNePattern;
import com.match4j.pattern.obj.ObjectPredicatePattern;
import com.match4j.pattern.obj.ObjectTypeIsPattern;

public class MatchingForObject {
	private final Object input;

	public MatchingForObject(Object input) {
		this.input = input;
	}

	public <O> TypedMatchingForObject<O> casePred(Predicate<Object> predicate, Function<Object, O> function) {
		final Pattern<O> pattern = new ObjectPredicatePattern<>(predicate, function);
		return new TypedMatchingForObject<O>(input, pattern);
	}

	public <O> TypedMatchingForObject<O> caseEq(Object value, Function<Object, O> function) {
		final Pattern<O> pattern = new ObjectEqPattern<>(value, function);
		return new TypedMatchingForObject<O>(input, pattern);
	}

	public <O> TypedMatchingForObject<O> caseNe(Object value, Function<Object, O> function) {
		final Pattern<O> pattern = new ObjectNePattern<>(value, function);
		return new TypedMatchingForObject<O>(input, pattern);
	}

	public <T, O> TypedMatchingForObject<O> caseClassEq(Class<T> clazz, Function<T, O> function) {
		final Pattern<O> pattern = new ObjectClassEqPattern<>(clazz, function);
		return new TypedMatchingForObject<O>(input, pattern);
	}

	public <T, O> TypedMatchingForObject<O> caseTypeIs(Class<T> type, Function<T, O> function) {
		final Pattern<O> pattern = new ObjectTypeIsPattern<>(type, function);
		return new TypedMatchingForObject<O>(input, pattern);
	}

}
