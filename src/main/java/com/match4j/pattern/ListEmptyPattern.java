package com.match4j.pattern;

import java.util.List;
import java.util.function.Supplier;

import com.match4j.main.Pattern;

public class ListEmptyPattern<O> implements Pattern<O> {
	private final Supplier<O> supplier;

	public ListEmptyPattern(Supplier<O> supplier) {
		this.supplier = supplier;
	}

	@Override
	public boolean matches(Object value) {
		return value != null && ((List<?>) value).isEmpty();
	}

	@Override
	public O apply(Object value) {
		return supplier.get();
	}
}
