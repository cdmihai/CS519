package edu.oregonstate.cs519.touchdevelop.ast.expression;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExpressionTokenizerTest {

	private static ExpressionTokenizer tokenizer;

	@BeforeClass
	public static void setup() {
		tokenizer = new ExpressionTokenizer();
	}

	// @formatter:off
	@Test
	public void testArithOp() {
		assertTokens("$mNMHXmR6bIKpou2PYSrG7Dv4L0 ,:= ,2 ,/ ,3", 
				tokenizer.createLocalRef("mNMHXmR6bIKpou2PYSrG7Dv4L0"),
				tokenizer.createOperator(":="),
				tokenizer.createOperator("2"),
				tokenizer.createOperator("/"),
				tokenizer.createOperator("3"));
	}
	
	@Test
	public void testComplexArithOp() throws Exception {
		assertTokens("$xBTNp286Ucy26f9tjIaqATEqL0 ,:= ,3 ,+ $mNMHXmR6bIKpou2PYSrG7Dv4L0 ,- ,5", 
				tokenizer.createLocalRef("xBTNp286Ucy26f9tjIaqATEqL0"),
				tokenizer.createOperator(":="),
				tokenizer.createOperator("3"),
				tokenizer.createOperator("+"),
				tokenizer.createLocalRef("mNMHXmR6bIKpou2PYSrG7Dv4L0"),
				tokenizer.createOperator("-"),
				tokenizer.createOperator("5"));
	}
	
	// @formatter:on

	private void assertTokens(String inputString, JSONObject... tokens) {
		List<JSONObject> expected = new ArrayList<>();
		expected.addAll(Arrays.asList(tokens));

		List<JSONObject> actual = tokenizer.tokenize(inputString);
		assertEqualsTokens(expected, actual);
	}

	private void assertEqualsTokens(List<JSONObject> expected, List<JSONObject> actual) {

		assertEquals(expected.size(), actual.size());

		for (int i = 0; i < expected.size(); i++) {
			assertJSONEqual(expected.get(i), actual.get(i));
		}
	}

	private void assertJSONEqual(JSONObject expected, JSONObject actual) {

		assertEquals(expected.keySet(), actual.keySet());

		for (Object key : expected.keySet()) {
			assertEquals(expected.get(key), actual.get(key));
		}
	}
}