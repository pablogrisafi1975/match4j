package com.match4j.builder;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import com.match4j.main.Pattern;
import com.match4j.main.PatternMatching;
import com.match4j.pattern.obj.ObjectEqPattern;
import com.match4j.pattern.obj.ObjectNePattern;
import com.match4j.pattern.obj.ObjectPredicatePattern;
import com.match4j.pattern.obj.OtherwiseObjectPattern;
import com.match4j.pattern.obj.OtherwiseThrowPattern;
import com.match4j.pattern.str.StringEqIgnoreCasePattern;
import com.match4j.pattern.str.StringEqIgnoreCaseTrimPattern;
import com.match4j.pattern.str.StringIsBlankPattern;
import com.match4j.pattern.str.StringIsEmptyPattern;

public class TypedMatchingForString<O> {
	private final String input;
	final List<Pattern<O>> patterns = new LinkedList<>();

	public TypedMatchingForString(String input, Pattern<O> pattern) {
		this.input = input;
		patterns.add(pattern);
	}

	public TypedMatchingForString<O> casePred(Predicate<String> predicate, Function<String, O> function) {
		@SuppressWarnings("unchecked")
		Predicate<Object> objPredicate = (Predicate<Object>) (Predicate<?>) predicate;
		@SuppressWarnings("unchecked")
		Function<Object, O> objFunction = (Function<Object, O>) (Function<?, O>) function;
		final Pattern<O> pattern = new ObjectPredicatePattern<>(objPredicate, objFunction);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForString<O> caseEq(Object value, Function<Object, O> function) {
		Pattern<O> pattern = new ObjectEqPattern<>(value, function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForString<O> caseEqIgnoreCase(String value, Function<String, O> function) {
		final Pattern<O> pattern = new StringEqIgnoreCasePattern<>(value, function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForString<O> caseEqIgnoreCaseTrim(String value, Function<String, O> function) {
		final Pattern<O> pattern = new StringEqIgnoreCaseTrimPattern<>(value, function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForString<O> caseIsEmpty(Function<String, O> function) {
		final Pattern<O> pattern = new StringIsEmptyPattern<>(function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForString<O> caseIsBlank(Function<String, O> function) {
		final Pattern<O> pattern = new StringIsBlankPattern<>(function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForString<O> caseNe(Object value, Function<Object, O> function) {
		Pattern<O> pattern = new ObjectNePattern<>(value, function);
		patterns.add(pattern);
		return this;
	}

	public O otherwise(Function<String, O> function) {
		@SuppressWarnings("unchecked")
		Function<Object, O> objFunction = (Function<Object, O>) (Function<?, O>) function;
		Pattern<O> pattern = new OtherwiseObjectPattern<>(objFunction);
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
