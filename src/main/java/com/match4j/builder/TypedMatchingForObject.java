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

public class TypedMatchingForObject<O> {
	private final Object input;
	final List<Pattern<O>> patterns = new LinkedList<>();

	public TypedMatchingForObject(Object input, Pattern<O> pattern) {
		this.input = input;
		patterns.add(pattern);
	}

	public TypedMatchingForObject<O> casePred(Predicate<Object> predicate, Function<Object, O> function) {
		final Pattern<O> pattern = new ObjectPredicatePattern<>(predicate, function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForObject<O> caseEq(Object value, Function<Object, O> function) {
		Pattern<O> pattern = new ObjectEqPattern<>(value, function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForObject<O> caseNe(Object value, Function<Object, O> function) {
		Pattern<O> pattern = new ObjectNePattern<>(value, function);
		patterns.add(pattern);
		return this;
	}

	public <T> TypedMatchingForObject<O> caseClassEq(Class<T> clazz, Function<T, O> function) {
		final Pattern<O> pattern = new ObjectClassEqPattern<>(clazz, function);
		patterns.add(pattern);
		return this;
	}

	public <T> TypedMatchingForObject<O> caseTypeIs(Class<T> type, Function<T, O> function) {
		final Pattern<O> pattern = new ObjectTypeIsPattern<>(type, function);
		patterns.add(pattern);
		return this;
	}

	public O otherwise(Function<Object, O> function) {
		Pattern<O> pattern = new OtherwiseObjectPattern<>(function);
		patterns.add(pattern);
		return new PatternMatching<>(patterns).apply(input);
	}

	public Object otherwiseThrow() {
		return otherwiseThrow(new IllegalArgumentException("No match was found"));
	}

	public <E extends RuntimeException> Object otherwiseThrow(Class<E> runtimeExceptionClass) {
		E runtimeException;
		try {
			runtimeException = runtimeExceptionClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalArgumentException("Can not instantiate " + runtimeExceptionClass.getCanonicalName(), e);
		}
		return otherwiseThrow(runtimeException);
	}

	public Object otherwiseThrow(RuntimeException runtimeException) {
		Pattern<O> pattern = new OtherwiseThrowPattern<>(runtimeException);
		patterns.add(pattern);
		return new PatternMatching<>(patterns).apply(input);
	}

}
