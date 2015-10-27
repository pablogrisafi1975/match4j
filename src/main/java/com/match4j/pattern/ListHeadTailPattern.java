package com.match4j.pattern;

import java.util.List;
import java.util.function.BiFunction;

import com.match4j.main.Pattern;

public class ListHeadTailPattern<E, O> implements Pattern<O> {

	private final BiFunction<E, List<E>, O> bifunction;

	public ListHeadTailPattern(BiFunction<E, List<E>, O> bifunction) {
		this.bifunction = bifunction;
	}

	@Override
	public boolean matches(Object value) {
		return value != null && !((List<?>) value).isEmpty();
	}

	@Override
	public O apply(Object value) {
		@SuppressWarnings("unchecked")
		List<E> list = (List<E>) value;
		E head = list.get(0);
		List<E> tail = list.subList(0, list.size() - 1);
		return bifunction.apply(head, tail);
	}

}
