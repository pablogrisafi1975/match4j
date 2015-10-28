package com.match4j.pattern.str;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class StringIsBlankPattern<O> implements Pattern<O> {
	private final Function<Object, O> function;

	@SuppressWarnings("unchecked")
	public StringIsBlankPattern(Function<String, O> function) {
		this.function = (Function<Object, O>) (Function<?, O>) function;
	}

	@Override
	public boolean matches(Object input) {
		if (input instanceof String) {
			String str = (String) input;
			int strLen = str.length();
			if (strLen == 0) {
				return true;
			}
			for (int i = 0; i < strLen; i++) {
				if (Character.isWhitespace(str.charAt(i)) == false) {
					return false;
				}
			}
			return true;
		}
		return input == null;

	}

	@Override
	public O apply(Object input) {
		return function.apply(input);
	}

}
