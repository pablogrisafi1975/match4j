package com.match4j.pattern.obj;

import java.util.function.Function;

import com.match4j.main.AbstractPattern;
import com.match4j.main.Pattern;

public class OtherwiseObjectPattern<I, O> extends AbstractPattern<I, O> implements Pattern<I, O> {

	public OtherwiseObjectPattern(Function<I, O> function) {
		super(function);
	}

	@Override
	public boolean matches(I value) {
		return true;
	}

}
