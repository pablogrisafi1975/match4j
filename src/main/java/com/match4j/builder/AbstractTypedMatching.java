package com.match4j.builder;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import com.match4j.main.Pattern;
import com.match4j.main.PatternMatching;
import com.match4j.pattern.obj.ObjectClassEqPattern;
import com.match4j.pattern.obj.ObjectEqPattern;
import com.match4j.pattern.obj.ObjectNePattern;
import com.match4j.pattern.obj.ObjectPredicatePattern;
import com.match4j.pattern.obj.ObjectTypeIsPattern;
import com.match4j.pattern.obj.OtherwiseObjectPattern;
import com.match4j.pattern.obj.OtherwiseThrowPattern;

public abstract class AbstractTypedMatching<I, O, SELF extends AbstractTypedMatching<I, O, SELF>> {
	private final I input;
	private final List<Pattern<I, O>> patterns = new LinkedList<>();

	public AbstractTypedMatching(I input, Pattern<I, O> pattern) {
		this.input = input;
		this.getPatterns().add(pattern);
	}

	public SELF casePred(Predicate<I> predicate, Function<I, O> function) {
		final Pattern<I, O> pattern = new ObjectPredicatePattern<>(predicate, function);
		return addPattern(pattern);
	}

	public SELF caseEq(I value, Function<I, O> function) {
		Pattern<I, O> pattern = new ObjectEqPattern<>(value, function);
		return addPattern(pattern);
	}

	public SELF caseNe(I value, Function<I, O> function) {
		Pattern<I, O> pattern = new ObjectNePattern<>(value, function);
		return addPattern(pattern);
	}

	public <T extends I> SELF caseClassEq(Class<T> clazz, Function<T, O> function) {
		final Pattern<I, O> pattern = new ObjectClassEqPattern<>(clazz, function);
		return addPattern(pattern);
	}

	public <T extends I> SELF caseTypeIs(Class<T> type, Function<T, O> function) {
		final Pattern<I, O> pattern = new ObjectTypeIsPattern<>(type, function);
		return addPattern(pattern);
	}

	public O otherwise(Function<I, O> function) {
		Pattern<I, O> pattern = new OtherwiseObjectPattern<>(function);
		addPattern(pattern);
		return new PatternMatching<>(getPatterns()).apply(getInput());
	}

	public O otherwiseThrow() {
		return otherwiseThrow(new IllegalArgumentException("No match was found"));
	}

	public <E extends RuntimeException> O otherwiseThrow(Class<E> runtimeExceptionClass) {
		E runtimeException;
		try {
			runtimeException = runtimeExceptionClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalArgumentException("Can not instantiate " + runtimeExceptionClass.getCanonicalName(), e);
		}
		return otherwiseThrow(runtimeException);
	}

	public O otherwiseThrow(RuntimeException runtimeException) {
		Pattern<I, O> pattern = new OtherwiseThrowPattern<>(runtimeException);
		addPattern(pattern);
		return new PatternMatching<>(getPatterns()).apply(getInput());
	}

	@SuppressWarnings("unchecked")
	protected SELF addPattern(Pattern<I, O> pattern) {
		getPatterns().add(pattern);
		return (SELF) this;
	}

	public I getInput() {
		return input;
	}

	public List<Pattern<I, O>> getPatterns() {
		return patterns;
	}

}
