package com.match4j.builder;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

import com.match4j.main.Pattern;
import com.match4j.main.PatternMatching;
import com.match4j.pattern.ListHeadTailPattern;

public class TypedMatchingForList<E, O> {
	private final List<E> input;
	private final List<Pattern<O>> patterns = new LinkedList<>();

	public TypedMatchingForList(List<E> input, Pattern<O> pattern) {
		this.input = input;
		patterns.add(pattern);
	}

	public TypedMatchingForList<E, O> caseHeadTail(BiFunction<E, List<E>, O> bifunction) {
		Pattern<O> pattern = new ListHeadTailPattern<>(bifunction);
		patterns.add(pattern);
		return this;
	}

	public O end() {
		return new PatternMatching<>(patterns).apply(input);
	}
}
