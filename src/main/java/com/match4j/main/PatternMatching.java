package com.match4j.main;

import java.util.List;
import java.util.function.Function;

public class PatternMatching<I, O> implements Function<I, O> {
	private final List<Pattern<I, O>> patterns;

	public PatternMatching(List<Pattern<I, O>> patterns) {
		this.patterns = patterns;
	}

	public O apply(I value) {
		for (Pattern<I, O> pattern : patterns)
			if (pattern.matches(value))
				return pattern.apply(value);

		throw new IllegalArgumentException("cannot match " + value);
	}

}
