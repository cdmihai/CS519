package edu.oregonstate.cs519.touchdevelop.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;

import edu.oregonstate.cs519.touchdevelop.ast.ASTNode;
import edu.oregonstate.cs519.touchdevelop.ast.Update;

public class Main {

	public static void main(String[] args) throws Exception {

		String initialProgram = "{\n"
				+ "      \"textVersion\": \"v2.2,js,ctx\",\n"
				+ "      \"jsonVersion\": \"v1.0,resolved,short\",\n"
				+ "      \"name\": \"demo-replay\",\n"
				+ "      \"comment\": \"\",\n"
				+ "      \"autoIcon\": \"ArrowMoving\",\n"
				+ "      \"autoColor\": \"#FF00FF\",\n"
				+ "      \"platform\": \"current\",\n"
				+ "      \"rootId\": \"xzF9z5DyvKsXiOHtbujRquTs\",\n"
				+ "      \"showAd\": false,\n"
				+ "      \"isLibrary\": false,\n"
				+ "      \"allowExport\": false,\n"
				+ "      \"hasUniqueIds\": false,\n" + "      \"decls\": [\n"
				+ "        {\n" + "          \"name\": \"main\",\n"
				+ "          \"inParameters\": [],\n"
				+ "          \"outParameters\": [],\n"
				+ "          \"isPrivate\": false,\n"
				+ "          \"isOffloaded\": false,\n"
				+ "          \"isTest\": false,\n"
				+ "          \"isAsync\": false,\n"
				+ "          \"nodeType\": \"action\",\n"
				+ "          \"body\": [\n" + "            {\n"
				+ "              \"expr\": \"'Hello_World/0021\",\n"
				+ "              \"nodeType\": \"exprStmt\",\n"
				+ "              \"id\": \"VNSY7fBGBXK4iK14sI1EXy6t\",\n"
				+ "              \"locals\": []\n" + "            }\n"
				+ "          ],\n"
				+ "          \"id\": \"xG9ceyZsEfSX0XLuIfNpdy92\"\n"
				+ "        }\n" + "      ],\n"
				+ "      \"deletedDecls\": [],\n"
				+ "      \"nodeType\": \"app\",\n" + "      \"id\": \"app\"\n"
				+ "    }\n";

		ASTNode program = new ASTNode(initialProgram);
		List<Update> update = new ArrayList<Update>();

		// add method call
		update.add(new Update(
				"VNSY7fBGBXK4iK14sI1EXy6t",
				(Map<String, Object>) JSONValue
						.parse("{\n"
								+ "\"expr\": \"'/0022Hello_World/0021/0022 .post_to_wall\"\n"
								+ "}")));

		// add global variable named s
		update.add(new Update("wAHj4rpF7s1v6qxVEDsWsDWL",
				(Map<String, Object>) JSONValue.parse("{\n"
						+ "\"name\": \"s\",\n"
						+ "\"comment\": \"\",\n"
						+ "\"type\": \"String\",\n"
						+ "\"isReadonly\": false,\n"
						+ "\"isTransient\": true,\n"
						+ "\"nodeType\": \"data\"\n" + "      }")));
		update.add(new Update("app", (Map<String, Object>) JSONValue
				.parse("{\n" + "\"decls\": [\n"
						+ "\"xG9ceyZsEfSX0XLuIfNpdy92\",\n"
						+ "\"wAHj4rpF7s1v6qxVEDsWsDWL\"\n"
						+ "]\n" + "}")));

		// assign to that global variable
		update.add(new Update(
				"w7Xl21sA24EA0Lpp43qMgHGH",
				(Map<String, Object>) JSONValue
						.parse("{\n"
								+ "\"expr\": \":data #wAHj4rpF7s1v6qxVEDsWsDWL ,:= 'How/0027s_it_going/003f\""
								+ "\"nodeType\": \"exprStmt\",\n"
								+ "\"locals\": []\n" + "}")));
		update.add(new Update("xG9ceyZsEfSX0XLuIfNpdy92",
				(Map<String, Object>) JSONValue.parse("{\n"
						+ "\"body\": [\n"
						+ "\"VNSY7fBGBXK4iK14sI1EXy6t\",\n"
						+ "\"w7Xl21sA24EA0Lpp43qMgHGH\"\n"
						+ "]\n" + "}")));

		for (Update u : update) {
			u.apply();
		}

		System.out.println(program.getJSON());
	}

}
