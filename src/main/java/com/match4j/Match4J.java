package com.match4j;

import java.util.List;

import com.match4j.builder.MatchingForInteger;
import com.match4j.builder.MatchingForList;
import com.match4j.builder.MatchingForObject;

public class Match4J {

	public static final MatchingForInteger match(Integer input) {
		return new MatchingForInteger(input);
	}

	public static final <E> MatchingForList<E> match(List<E> input) {
		return new MatchingForList<>(input);
	}

	public static final MatchingForObject match(Object input) {
		return new MatchingForObject(input);
	}
}
