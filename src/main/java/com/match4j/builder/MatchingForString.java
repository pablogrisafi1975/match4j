package com.match4j.builder;

import java.util.function.Function;
import java.util.function.Predicate;

import com.match4j.main.Pattern;
import com.match4j.pattern.obj.ObjectEqPattern;
import com.match4j.pattern.obj.ObjectNePattern;
import com.match4j.pattern.obj.ObjectPredicatePattern;
import com.match4j.pattern.str.StringEqIgnoreCasePattern;
import com.match4j.pattern.str.StringEqIgnoreCaseTrimPattern;
import com.match4j.pattern.str.StringIsBlankPattern;
import com.match4j.pattern.str.StringIsEmptyPattern;

public class MatchingForString {
	private final String input;

	public MatchingForString(String input) {
		this.input = input;
	}

	public <O> TypedMatchingForString<O> casePred(Predicate<String> predicate, Function<String, O> function) {
		final Pattern<String, O> pattern = new ObjectPredicatePattern<>(predicate, function);
		return new TypedMatchingForString<O>(input, pattern);
	}

	public <O> TypedMatchingForString<O> caseEq(String value, Function<String, O> function) {
		final Pattern<String, O> pattern = new ObjectEqPattern<>(value, function);
		return new TypedMatchingForString<O>(input, pattern);
	}

	public <O> TypedMatchingForString<O> caseNe(String value, Function<String, O> function) {
		final Pattern<String, O> pattern = new ObjectNePattern<>(value, function);
		return new TypedMatchingForString<O>(input, pattern);
	}

	public <O> TypedMatchingForString<O> caseEqIgnoreCase(String value, Function<String, O> function) {
		final Pattern<String, O> pattern = new StringEqIgnoreCasePattern<>(value, function);
		return new TypedMatchingForString<O>(input, pattern);
	}

	public <O> TypedMatchingForString<O> caseEqIgnoreCaseTrim(String value, Function<String, O> function) {
		final Pattern<String, O> pattern = new StringEqIgnoreCaseTrimPattern<>(value, function);
		return new TypedMatchingForString<O>(input, pattern);
	}

	public <O> TypedMatchingForString<O> caseIsEmpty(Function<String, O> function) {
		final Pattern<String, O> pattern = new StringIsEmptyPattern<>(function);
		return new TypedMatchingForString<O>(input, pattern);
	}

	public <O> TypedMatchingForString<O> caseIsBlank(Function<String, O> function) {
		final Pattern<String, O> pattern = new StringIsBlankPattern<>(function);
		return new TypedMatchingForString<O>(input, pattern);
	}

}
