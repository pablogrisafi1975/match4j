package com.match4j.pattern.str;

import java.util.function.Function;

import com.match4j.main.Pattern;

public class StringIsBlankPattern<O> implements Pattern<String, O> {
	private final Function<String, O> function;

	public StringIsBlankPattern(Function<String, O> function) {
		this.function = function;
	}

	@Override
	public boolean matches(String input) {
		if (input == null) {
			return true;
		}
		int strLen = input.length();
		if (strLen == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(input.charAt(i)) == false) {
				return false;
			}
		}
		return true;

	}

	@Override
	public O apply(String input) {
		return function.apply(input);
	}

}
