package com.match4j;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.match4j.model.Animal;
import com.match4j.model.Bat;
import com.match4j.model.Bird;
import com.match4j.model.Cat;
import com.match4j.model.Dog;
import com.match4j.model.Flying;

@Test
public class MatchObjectTest {

	@DataProvider
	public Object[][] matchObjectPred_dataProvider() {
		//@formatter:off
		return new Object[][]{
				{"abc", "ends with bc"},
				{null, "is <null>"},
				{Integer.valueOf(44), "is even"},
				{44, "is even"},
				{"whatever", "is otherwise"},
		};
		//@formatter:on
	}

	@Test(dataProvider = "matchObjectPred_dataProvider")
	public void matchObjectPred(Object input, Object expectedOutput) throws Exception {
		//@formatter:off
		Object actualOutput = Match4J.match(input)
				.casePred(x -> x instanceof String && ((String)x).endsWith("bc"), x -> "ends with bc")
				.casePred(x -> x == null, x -> "is <null>")
				.casePred(x -> x instanceof Integer && ((Integer)x) % 2 == 0, x -> "is even")
				.otherwise(x -> "is otherwise");
		
		//@formatter:on
		assertThat(expectedOutput).isEqualTo(actualOutput);
	}

	@DataProvider
	public Object[][] matchObjectEq_dataProvider() {
		//@formatter:off
		return new Object[][]{
				{"abc", "is abc"},
				{null, "is <null>"},
				{"def", "is def"},
				{"whatever", "is otherwise"},
		};
		//@formatter:on
	}

	@Test(dataProvider = "matchObjectEq_dataProvider")
	public void matchObjectEq(Object input, Object expectedOutput) throws Exception {
		//@formatter:off
		Object actualOutput = Match4J.match(input)
			.caseEq("abc", x -> "is abc")
			.caseEq("def", x -> "is def")
			.caseEq(null, x -> "is <null>")
			.otherwise(x -> "is otherwise");
		
		//@formatter:on
		assertThat(expectedOutput).isEqualTo(actualOutput);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "No match was found")
	public void matchObjectEq_throwIllegalArgumentException() throws Exception {
		//@formatter:off
		Match4J.match("xxx")
			.caseEq("abc", x -> "is abc")
			.caseEq("def", x -> "is def")
			.otherwiseThrow();
		//@formatter:on
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void matchObjectEq_throwNullPointerException() throws Exception {
		//@formatter:off
		Match4J.match("xxx")
		.caseEq("abc", x -> "is abc")
		.caseEq("def", x -> "is def")
		.otherwiseThrow(new NullPointerException());
		//@formatter:on
	}

	@Test(expectedExceptions = IllegalStateException.class)
	public void matchObjectEq_throwIllegalStateException() throws Exception {
		//@formatter:off
		Match4J.match("xxx")
		.caseEq("abc", x -> "is abc")
		.caseEq("def", x -> "is def")
		.otherwiseThrow(IllegalStateException.class);
		//@formatter:on
	}

	@DataProvider
	public Object[][] matchObjectNe_dataProvider() {
		//@formatter:off
		return new Object[][]{
				{"abc", "is not def"},
				{"def", "is not abc"},
				{"whatever", "is not abc"},
				{null, "is not abc"},
		};
		//@formatter:on
	}

	@Test(dataProvider = "matchObjectNe_dataProvider")
	public void matchObjectNe(Object input, Object expectedOutput) throws Exception {
		//@formatter:off
		Object actualOutput = Match4J.match(input)
				.caseNe("abc", x -> "is not abc")
				.caseNe("def", x -> "is not def")
				.otherwise(x -> "is otherwise");
		
		//@formatter:on
		assertThat(expectedOutput).isEqualTo(actualOutput);
	}

	// TODO: anyOf... it is complicated because varargs can only happen at the
	// end of the parameter list

	@DataProvider
	public Object[][] matchObjectClassEq_dataProvider() {
		//@formatter:off
		return new Object[][]{
				{new Dog(), "I'm a dog and I bark"},
				{new Cat(), "I'm a cat and I mew"},
				{((Animal)new Cat()), "I'm a cat and I mew"},
				{new Bird(), "I'm a flying bird"},
				{"abc", "is abc"},
				{new Bat(), "is otherwise"}, //Bat does not match as Animal, because Animal is super class and not class
				{null, "is otherwise"},
				{"whatever", "is otherwise"},
		};
		//@formatter:on
	}

	@Test(dataProvider = "matchObjectClassEq_dataProvider")
	public void matchObjectClassEq(Object input, Object expectedOutput) throws Exception {
		//@formatter:off
		Object actualOutput = Match4J.match(input)
			.caseClassEq(Dog.class, dog -> "I'm a dog and I " + dog.bark())
			.caseClassEq(Cat.class, cat -> "I'm a cat and I " + cat.mew())
			.caseClassEq(Bird.class, bird -> "I'm a " + bird.fly())
			.caseClassEq(Animal.class, bird -> "I'm an animal")
			.caseEq("abc", x -> "is abc")
			.otherwise(x -> "is otherwise");
		
		//@formatter:on
		assertThat(expectedOutput).isEqualTo(actualOutput);
	}

	/*- maybe this was fixed by type system 
	 @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "caseClassEq can only match classes, neither interfaces nor annotations")
	 public void matchObjectClassEqInterface() throws Exception {
	 //@formatter:off
	 Match4J.match(new Animal())
	 .caseClassEq(Flying.class, x -> "")
	 .otherwise(x -> "is otherwise");
	
	 //@formatter:on
	 }

	 @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "caseClassEq can only match classes, neither interfaces nor annotations")
	 public void matchObjectClassEqAnnotations() throws Exception {
	 //@formatter:off
	 Match4J.match(new Animal())
	 .caseClassEq(FlyingAnnotation.class, x -> "")
	 .otherwise(x -> "is otherwise");
	
	 //@formatter:on
	 }
	 */
	@DataProvider
	public Object[][] matchObjectTypeIs_dataProvider() {
		//@formatter:off
		return new Object[][]{
				{new Dog(), "I'm a dog and I bark"},
				{new Cat(), "I'm a cat and I mew"},
				{((Animal)new Cat()), "I'm a cat and I mew"},
				{new Bird(), "I'm a flying bird"},
				{"abc", "is abc"},
				{new Bat(), "I'm a flying bat"},
				{null, "is otherwise"},
				{"whatever", "is otherwise"},
		};
		//@formatter:on
	}

	@Test(dataProvider = "matchObjectTypeIs_dataProvider")
	public void matchObjectTypeIs(Object input, Object expectedOutput) throws Exception {
		//@formatter:off
		Object actualOutput = Match4J.match(input)
			.caseTypeIs(Dog.class, dog -> "I'm a dog and I " + dog.bark())
			.caseTypeIs(Cat.class, cat -> "I'm a cat and I " + cat.mew())
			.caseTypeIs(Bird.class, bird -> "I'm a " + bird.fly())
			.caseTypeIs(Flying.class, f -> "I'm a " + f.fly())
			.caseTypeIs(Animal.class, bird -> "I'm an animal")
			.caseEq("abc", x -> "is abc")
			.otherwise(x -> "is otherwise");
		
		//@formatter:on
		assertThat(expectedOutput).isEqualTo(actualOutput);
	}

	// TODO: alternative syntax for typeIs alla cyclops

	public void streamCompatibility() {
		//@formatter:off
		List<String> sounds = Arrays.asList(new Cat(), new Dog(), new Bird())
		.stream()
		.map(a -> Match4J.match(a)
				.caseTypeIs(Cat.class, c -> c.mew())
				.caseTypeIs(Dog.class, d -> d.bark())
				.caseTypeIs(Bird.class, b -> b.fly())
				.otherwiseThrow()
				)
		.collect(Collectors.toList());
		assertThat(Arrays.asList("mew", "bark", "flying bird")).isEqualTo(sounds);
		//@formatter:on
	}

}
