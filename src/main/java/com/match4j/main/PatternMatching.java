package com.match4j.main;

import java.util.List;
import java.util.function.Function;

public class PatternMatching<O> implements Function<Object, O> {
	private final List<Pattern<O>> patterns;

	public PatternMatching(List<Pattern<O>> patterns) {
		this.patterns = patterns;
	}

	public O apply(Object value) {
		for (Pattern<O> pattern : patterns)
			if (pattern.matches(value))
				return pattern.apply(value);

		throw new IllegalArgumentException("cannot match " + value);
	}

}
