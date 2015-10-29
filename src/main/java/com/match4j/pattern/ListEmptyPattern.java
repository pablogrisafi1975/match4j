package com.match4j.pattern;

import java.util.List;
import java.util.function.Supplier;

import com.match4j.main.Pattern;

public class ListEmptyPattern<E, O> implements Pattern<List<E>, O> {
	private final Supplier<O> supplier;

	public ListEmptyPattern(Supplier<O> supplier) {
		this.supplier = supplier;
	}

	@Override
	public boolean matches(List<E> value) {
		return value != null && value.isEmpty();
	}

	@Override
	public O apply(List<E> value) {
		return supplier.get();
	}
}
