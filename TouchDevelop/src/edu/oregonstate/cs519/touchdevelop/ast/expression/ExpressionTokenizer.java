package edu.oregonstate.cs519.touchdevelop.ast.expression;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

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

	public JSONObject oneToken(String s) {
		String v = s.substring(1);
		switch (s.charAt(0)) {
		case ',':
			return createOperator(v);
		case '#':
			return createPropertyRefDeclid(v);
		case '.':
			return createPropertyRefName(uq(v));
		case '\'':
			return createStringLiteral(uq(v));
		case 'F':
		case 'T':
			return createBooleanLiteral((s.charAt(0) == 'T') + "");
		case '$':
			return createLocalRef(v);
		case ':':
			return createSingletonRef(uq(v));
		case '?':
			int cln = v.indexOf(':');
			if (cln > 0)
				return createPlaceHolderWithName((uq(v.substring(0, cln))), (uq(v.substring(cln + 1))));
			else
				return createPlaceHolder(uq(v));
		default:
			throw new Error("wrong short form: " + s);
		}
	}

	protected JSONObject createPlaceHolder(String type) {
		JSONObject obj = createCoreJSON("placeHolder");
		obj.put(type, type);

		return obj;
	}

	protected JSONObject createPlaceHolderWithName(String type, String name) {
		JSONObject obj = createPlaceHolder(type);
		obj.put("name", name);

		return obj;
	}

	protected JSONObject createSingletonRef(String v) {
		JSONObject obj = createCoreJSON("singletonRef");
		obj.put("name", v);

		return obj;
	}

	protected JSONObject createLocalRef(String v) {
		JSONObject obj = createCoreJSON("localRef");
		obj.put("localId", v);

		return obj;
	}

	protected JSONObject createBooleanLiteral(String s) {
		JSONObject obj = createCoreJSON("booleanLiteral");
		obj.put("value", s);

		return obj;
	}

	protected JSONObject createStringLiteral(String v) {
		JSONObject obj = createCoreJSON("stringLiteral");
		obj.put("value", v);

		return obj;
	}

	protected JSONObject createPropertyRefName(String v) {
		JSONObject obj = createCoreJSON("propertyRef");
		obj.put("name", v);

		return obj;
	}

	protected JSONObject createCoreJSON(String nodeType) {
		JSONObject obj = new JSONObject();
		Object put = obj.put("nodeType", nodeType);
		return obj;
	}

	protected JSONObject createPropertyRefDeclid(String v) {
		JSONObject obj = createCoreJSON("propertyRef");
		obj.put("declId", v);

		return obj;
	}

	protected JSONObject createOperator(String op) {
		JSONObject obj = createCoreJSON("operator");
		obj.put("op", op);

		return obj;
	}

	public List<JSONObject> tokenize(String shortForm) {
		if (shortForm == null || shortForm.isEmpty())
			return new ArrayList<>(); // handles "" and null; the code below is
										// incorrect for ""
		List<JSONObject> tokens = new ArrayList<>();

		for (String element : shortForm.split(" ")) {
			tokens.add(oneToken(element));
		}

		return tokens;
	}
}
