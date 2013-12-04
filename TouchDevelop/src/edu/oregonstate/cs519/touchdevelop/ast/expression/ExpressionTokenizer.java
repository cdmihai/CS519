package edu.oregonstate.cs519.touchdevelop.ast.expression;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTokenizer {

	private String uq(String s) {
		String r = "";
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (c == '_') {
				r += " ";
			} else if (c == '/') {
				r += (char) (Integer.parseInt(s.substring(i + 1, i + 5), 16));
				i += 4;
			} else {
				r += c;
			}
		}
		return r;
	}

	private String oneToken(String s) {
		String v = s.substring(1);
		switch (s.charAt(0)) {
		case ',':
			return "{ nodeType: \"operator\", op: " + v + " }";
		case '#':
			return "{ nodeType: \"propertyRef\", declId: " + v + " }";
		case '.':
			return "{ nodeType: \"propertyRef\", name: " + uq(v) + " }";
		case '\'':
			return "{ nodeType: \"stringLiteral\", value: " + uq(v) + " }";
		case 'F':
		case 'T':
			return "{ nodeType: \"booleanLiteral\", value: (" + (s.charAt(0) == 'T') + ") }";
		case '$':
			return "{ nodeType: \"localRef\", localId: " + v + " }";
		case ':':
			return "{ nodeType: \"singletonRef\", name: " + uq(v) + " }";
		case '?':
			int cln = v.indexOf(':');
			if (cln > 0)
				return "{ nodeType: \"placeholder\", type: " + (uq(v.substring(0, cln))) + ", name: " + (uq(v.substring(cln + 1))) + " }";
			else
				return "{ nodeType: \"placeholder\", type: " + uq(v) + " }";
		default:
			throw new Error("wrong short form: " + s);
		}
	}

	public List<String> tokenize(String shortForm) {
		if (shortForm == null || shortForm.isEmpty())
			return new ArrayList<>(); // handles "" and null; the code below is
										// incorrect for ""
		List<String> tokens = new ArrayList<>();

		for (String element : shortForm.split(" ")) {
			tokens.add(oneToken(element));
		}

		return tokens;
	}
}
