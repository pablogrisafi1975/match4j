package com.match4j.builder;

import com.match4j.main.Pattern;

public class TypedMatchingForObject<I, O> extends AbstractTypedMatching<I, O, TypedMatchingForObject<I, O>> {

	public TypedMatchingForObject(I input, Pattern<I, O> pattern) {
		super(input, pattern);
	}

}
