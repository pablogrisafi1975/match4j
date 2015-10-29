package com.match4j.builder;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import com.match4j.main.Pattern;
import com.match4j.main.PatternMatching;
import com.match4j.pattern.obj.ObjectClassEqPattern;
import com.match4j.pattern.obj.ObjectEqPattern;
import com.match4j.pattern.obj.ObjectNePattern;
import com.match4j.pattern.obj.ObjectPredicatePattern;
import com.match4j.pattern.obj.ObjectTypeIsPattern;
import com.match4j.pattern.obj.OtherwiseObjectPattern;
import com.match4j.pattern.obj.OtherwiseThrowPattern;

public class TypedMatchingForObject<I, O> {
	private final I input;
	final List<Pattern<I, O>> patterns = new LinkedList<>();

	public TypedMatchingForObject(I input, Pattern<I, O> pattern) {
		this.input = input;
		patterns.add(pattern);
	}

	public TypedMatchingForObject<I, O> casePred(Predicate<I> predicate, Function<I, O> function) {
		final Pattern<I, O> pattern = new ObjectPredicatePattern<>(predicate, function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForObject<I, O> caseEq(I value, Function<I, O> function) {
		Pattern<I, O> pattern = new ObjectEqPattern<>(value, function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForObject<I, O> caseNe(I value, Function<I, O> function) {
		Pattern<I, O> pattern = new ObjectNePattern<>(value, function);
		patterns.add(pattern);
		return this;
	}

	public <T extends I> TypedMatchingForObject<I, O> caseClassEq(Class<T> clazz, Function<T, O> function) {
		final Pattern<I, O> pattern = new ObjectClassEqPattern<>(clazz, function);
		patterns.add(pattern);
		return this;
	}

	public <T extends I> TypedMatchingForObject<I, O> caseTypeIs(Class<T> type, Function<T, O> function) {
		final Pattern<I, O> pattern = new ObjectTypeIsPattern<>(type, function);
		patterns.add(pattern);
		return this;
	}

	public O otherwise(Function<I, O> function) {
		Pattern<I, O> pattern = new OtherwiseObjectPattern<>(function);
		patterns.add(pattern);
		return new PatternMatching<>(patterns).apply(input);
	}

	public O otherwiseThrow() {
		return otherwiseThrow(new IllegalArgumentException("No match was found"));
	}

	public <E extends RuntimeException> O otherwiseThrow(Class<E> runtimeExceptionClass) {
		E runtimeException;
		try {
			runtimeException = runtimeExceptionClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalArgumentException("Can not instantiate " + runtimeExceptionClass.getCanonicalName(), e);
		}
		return otherwiseThrow(runtimeException);
	}

	public O otherwiseThrow(RuntimeException runtimeException) {
		Pattern<I, O> pattern = new OtherwiseThrowPattern<>(runtimeException);
		patterns.add(pattern);
		return new PatternMatching<>(patterns).apply(input);
	}

}
