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
	final List<Pattern<String, O>> patterns = new LinkedList<>();

	public TypedMatchingForString(String input, Pattern<String, O> pattern) {
		this.input = input;
		patterns.add(pattern);
	}

	public TypedMatchingForString<O> casePred(Predicate<String> predicate, Function<String, O> function) {
		final Pattern<String, O> pattern = new ObjectPredicatePattern<>(predicate, function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForString<O> caseEq(String value, Function<String, O> function) {
		Pattern<String, O> pattern = new ObjectEqPattern<>(value, function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForString<O> caseEqIgnoreCase(String value, Function<String, O> function) {
		final Pattern<String, O> pattern = new StringEqIgnoreCasePattern<>(value, function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForString<O> caseEqIgnoreCaseTrim(String value, Function<String, O> function) {
		final Pattern<String, O> pattern = new StringEqIgnoreCaseTrimPattern<>(value, function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForString<O> caseIsEmpty(Function<String, O> function) {
		final Pattern<String, O> pattern = new StringIsEmptyPattern<>(function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForString<O> caseIsBlank(Function<String, O> function) {
		final Pattern<String, O> pattern = new StringIsBlankPattern<>(function);
		patterns.add(pattern);
		return this;
	}

	public TypedMatchingForString<O> caseNe(String value, Function<String, O> function) {
		Pattern<String, O> pattern = new ObjectNePattern<>(value, function);
		patterns.add(pattern);
		return this;
	}

	public O otherwise(Function<String, O> function) {
		Pattern<String, O> pattern = new OtherwiseObjectPattern<String, O>(function);
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
		Pattern<String, O> pattern = new OtherwiseThrowPattern<>(runtimeException);
		patterns.add(pattern);
		return new PatternMatching<>(patterns).apply(input);
	}

}
