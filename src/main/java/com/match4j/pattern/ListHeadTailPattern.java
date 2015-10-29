package com.match4j.pattern;

import java.util.List;
import java.util.function.BiFunction;

import com.match4j.main.Pattern;

public class ListHeadTailPattern<E, O> implements Pattern<List<E>, O> {

	private final BiFunction<E, List<E>, O> bifunction;

	public ListHeadTailPattern(BiFunction<E, List<E>, O> bifunction) {
		this.bifunction = bifunction;
	}

	@Override
	public boolean matches(List<E> value) {
		return value != null && !value.isEmpty();
	}

	@Override
	public O apply(List<E> value) {
		E head = value.get(0);
		List<E> tail = value.subList(0, value.size() - 1);
		return bifunction.apply(head, tail);
	}

}
