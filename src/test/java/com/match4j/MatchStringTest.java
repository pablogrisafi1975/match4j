package com.match4j;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class MatchStringTest {

	@DataProvider
	public Object[][] matchStringPred_dataProvider() {
		//@formatter:off
		return new Object[][]{
				{"abc", "ends with bc"},
				{null, "is <null>"},
				{"xyz", "starts with x"},
				{"whatever", "is otherwise"},
		};
		//@formatter:on
	}

	@Test(dataProvider = "matchStringPred_dataProvider")
	public void matchStringPred(String input, Object expectedOutput) throws Exception {
		//@formatter:off
		Object actualOutput = Match4J.match(input)
				.casePred(x -> x == null, x -> "is <null>")
				.casePred(x -> x.endsWith("bc"), x -> "ends with bc")
				.casePred(x -> x.startsWith("x"), x -> "starts with x")
				.otherwise(x -> "is otherwise");
		
		//@formatter:on
		assertThat(expectedOutput).isEqualTo(actualOutput);
	}

	@DataProvider
	public Object[][] matchStringEq_dataProvider() {
		//@formatter:off
		return new Object[][]{
				{"abc", "is abc"},
				{null, "is <null>"},
				{"def", "is def"},
				{"ABC", "is otherwise"},
				{"whatever", "is otherwise"},
		};
		//@formatter:on
	}

	@Test(dataProvider = "matchStringEq_dataProvider")
	public void matchStringEq(String input, Object expectedOutput) throws Exception {
		//@formatter:off
		Object actualOutput = Match4J.match(input)
			.caseEq("abc", x -> "is abc")
			.caseEq("def", x -> "is def")
			.caseEq(null, x -> "is <null>")
			.otherwise(x -> "is otherwise");
		
		//@formatter:on
		assertThat(expectedOutput).isEqualTo(actualOutput);
	}

	@DataProvider
	public Object[][] matchStringEqIgnoreCase_dataProvider() {
		//@formatter:off
		return new Object[][]{
				{"abc", "is abc"},
				{"ABC", "is abc"},
				{null, "is <null>"},
				{"dEf", "is def"},
				{"whatever", "is otherwise"},
		};
		//@formatter:on
	}

	@Test(dataProvider = "matchStringEqIgnoreCase_dataProvider")
	public void matchStringEqIgnoreCase(String input, Object expectedOutput) throws Exception {
		//@formatter:off
		Object actualOutput = Match4J.match(input)
				.caseEqIgnoreCase("abc", x -> "is abc")
				.caseEqIgnoreCase("def", x -> "is def")
				.caseEqIgnoreCase(null, x -> "is <null>")
				.otherwise(x -> "is otherwise");
		
		//@formatter:on
		assertThat(expectedOutput).isEqualTo(actualOutput);
	}

	@DataProvider
	public Object[][] matchStringEqIgnoreCaseTrim_dataProvider() {
		//@formatter:off
		return new Object[][]{
				{"abc", "is abc"},
				{" ABC ", "is abc"},
				{null, "is <null>"},
				{"   ", "is <null>"},
				{"dEf  ", "is def"},
				{"whatever", "is otherwise"},
		};
		//@formatter:on
	}

	@Test(dataProvider = "matchStringEqIgnoreCaseTrim_dataProvider")
	public void matchStringEqIgnoreCaseTrim(String input, Object expectedOutput) throws Exception {
		//@formatter:off
		Object actualOutput = Match4J.match(input)
				.caseEqIgnoreCaseTrim(" abc", x -> "is abc")
				.caseEqIgnoreCaseTrim(" def", x -> "is def")
				.caseEqIgnoreCaseTrim(null, x -> "is <null>")
				.otherwise(x -> "is otherwise");
		
		//@formatter:on
		assertThat(expectedOutput).isEqualTo(actualOutput);
	}

	@DataProvider
	public Object[][] matchStringIsEmpty_dataProvider() {
		//@formatter:off
		return new Object[][]{
				{"abc", "is not empty"},
				{"", "is empty"},
				{null, "is empty"},
				{"  ", "is not empty"},
				{"whatever", "is not empty"},
		};
		//@formatter:on
	}

	@Test(dataProvider = "matchStringIsEmpty_dataProvider")
	public void matchStringIsEmpty(String input, Object expectedOutput) throws Exception {
		//@formatter:off
		Object actualOutput = Match4J.match(input)
				.caseIsEmpty(x -> "is empty")
				.otherwise(x -> "is not empty");
		//@formatter:on
		assertThat(expectedOutput).isEqualTo(actualOutput);
	}

	@DataProvider
	public Object[][] matchStringIsEmpty2_dataProvider() {
		//@formatter:off
		return new Object[][]{
				{"a", "is a"},
				{"abc", "is not empty"},
				{"", "is empty"},
				{null, "is empty"},
				{"  ", "is not empty"},
				{"whatever", "is not empty"},
		};
		//@formatter:on
	}

	@Test(dataProvider = "matchStringIsEmpty2_dataProvider")
	public void matchStringIsEmpty2(String input, Object expectedOutput) throws Exception {
		//@formatter:off
		Object actualOutput = Match4J.match(input)
				.caseEq("a", x -> "is a")
				.caseIsEmpty(x -> "is empty")
				.otherwise(x -> "is not empty");
		//@formatter:on
		assertThat(expectedOutput).isEqualTo(actualOutput);
	}

	@DataProvider
	public Object[][] matchStringIsBlank_dataProvider() {
		//@formatter:off
		return new Object[][]{
				{"abc", "is not blank"},
				{"", "is blank"},
				{null, "is blank"},
				{"  ", "is blank"},
				{"whatever", "is not blank"},
		};
		//@formatter:on
	}

	@Test(dataProvider = "matchStringIsBlank_dataProvider")
	public void matchStringIsBlank(String input, Object expectedOutput) throws Exception {
		//@formatter:off
		Object actualOutput = Match4J.match(input)
				.caseIsBlank(x -> "is blank")
				.otherwise(x -> "is not blank");
		//@formatter:on
		assertThat(expectedOutput).isEqualTo(actualOutput);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "No match was found")
	public void matchStringEq_throwIllegalArgumentException() throws Exception {
		//@formatter:off
		Match4J.match("xxx")
			.caseEq("abc", x -> "is abc")
			.caseEq("def", x -> "is def")
			.otherwiseThrow();
		//@formatter:on
	}

	@DataProvider
	public Object[][] matchStringIsBlank2_dataProvider() {
		//@formatter:off
		return new Object[][]{
				{"a", "is a"},
				{"abc", "is not blank"},
				{"", "is blank"},
				{null, "is blank"},
				{"  ", "is blank"},
				{"whatever", "is not blank"},
		};
		//@formatter:on
	}

	@Test(dataProvider = "matchStringIsBlank2_dataProvider")
	public void matchStringIsBlank2(String input, Object expectedOutput) throws Exception {
		//@formatter:off
		Object actualOutput = Match4J.match(input)
				.caseEq("a", x -> "is a")
				.caseIsBlank(x -> "is blank")
				.otherwise(x -> "is not blank");
		//@formatter:on
		assertThat(expectedOutput).isEqualTo(actualOutput);
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void matchStringEq_throwNullPointerException() throws Exception {
		//@formatter:off
		Match4J.match("xxx")
		.caseEq("abc", x -> "is abc")
		.caseEq("def", x -> "is def")
		.otherwiseThrow(new NullPointerException());
		//@formatter:on
	}

	@Test(expectedExceptions = IllegalStateException.class)
	public void matchStringEq_throwIllegalStateException() throws Exception {
		//@formatter:off
		Match4J.match("xxx")
			.caseEq("abc", x -> "is abc")
			.caseEq("def", x -> "is def")
			.otherwiseThrow(IllegalStateException.class);
		//@formatter:on
	}

	@DataProvider
	public Object[][] matchStringNe_dataProvider() {
		//@formatter:off
		return new Object[][]{
				{"abc", "is not def"},
				{"def", "is not abc"},
				{"whatever", "is not abc"},
				{null, "is not abc"},
		};
		//@formatter:on
	}

	@Test(dataProvider = "matchStringNe_dataProvider")
	public void matchStringNe(Object input, Object expectedOutput) throws Exception {
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

}
