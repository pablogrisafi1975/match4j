package com.match4j;

import java.util.List;

import com.match4j.builder.MatchingForInteger;
import com.match4j.builder.MatchingForList;
import com.match4j.builder.MatchingForObject;
import com.match4j.builder.MatchingForString;

public class Match4J {

	public static final <I> MatchingForObject<I> match(I input) {
		return new MatchingForObject<I>(input);
	}

	public static final MatchingForString match(String input) {
		return new MatchingForString(input);
	}

	public static final MatchingForInteger match(Integer input) {
		return new MatchingForInteger(input);
	}

	public static final <E> MatchingForList<E> match(List<E> input) {
		return new MatchingForList<>(input);
	}

}
