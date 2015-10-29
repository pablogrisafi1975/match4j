package com.match4j.builder;

import java.util.function.Function;

import com.match4j.main.Pattern;
import com.match4j.pattern.str.StringEqIgnoreCasePattern;
import com.match4j.pattern.str.StringEqIgnoreCaseTrimPattern;
import com.match4j.pattern.str.StringIsBlankPattern;
import com.match4j.pattern.str.StringIsEmptyPattern;

public class TypedMatchingForString<O> extends AbstractTypedMatching<String, O, TypedMatchingForString<O>> {

	public TypedMatchingForString(String input, Pattern<String, O> pattern) {
		super(input, pattern);
	}

	public TypedMatchingForString<O> caseEqIgnoreCase(String value, Function<String, O> function) {
		final Pattern<String, O> pattern = new StringEqIgnoreCasePattern<>(value, function);
		return addPattern(pattern);
	}

	public TypedMatchingForString<O> caseEqIgnoreCaseTrim(String value, Function<String, O> function) {
		final Pattern<String, O> pattern = new StringEqIgnoreCaseTrimPattern<>(value, function);
		return addPattern(pattern);
	}

	public TypedMatchingForString<O> caseIsEmpty(Function<String, O> function) {
		final Pattern<String, O> pattern = new StringIsEmptyPattern<>(function);
		return addPattern(pattern);
	}

	public TypedMatchingForString<O> caseIsBlank(Function<String, O> function) {
		final Pattern<String, O> pattern = new StringIsBlankPattern<>(function);
		return addPattern(pattern);
	}

}
