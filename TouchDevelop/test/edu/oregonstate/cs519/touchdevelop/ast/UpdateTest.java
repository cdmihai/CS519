package edu.oregonstate.cs519.touchdevelop.ast;

import static org.junit.Assert.*;

import java.util.Map;

import org.json.simple.JSONValue;
import org.junit.Before;
import org.junit.Test;

public class UpdateTest {

	private String initialProgram = "{\n"
			+ "      \"textVersion\": \"v2.2,js,ctx\",\n"
			+ "      \"jsonVersion\": \"v1.0,resolved,short\",\n"
			+ "      \"name\": \"edits-test-dumb\",\n"
			+ "      \"comment\": \"\",\n" + "      \"autoIcon\": \"Exit\",\n"
			+ "      \"autoColor\": \"#EEDC82\",\n"
			+ "      \"platform\": \"current\",\n"
			+ "      \"rootId\": \"ycXVAstFZ325M0PRsuXtUu7F\",\n"
			+ "      \"showAd\": false,\n" + "      \"isLibrary\": false,\n"
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
			+ "              \"expr\": \"\",\n"
			+ "              \"nodeType\": \"exprStmt\",\n"
			+ "              \"id\": \"x0jQd1BtQGFLL1XBIeiT9kmL\",\n"
			+ "              \"locals\": []\n" + "            }\n"
			+ "          ],\n"
			+ "          \"id\": \"SZwwuN9ffv5TLJuO8buwjifz\"\n"
			+ "        }\n" + "      ],\n" + "      \"deletedDecls\": [],\n"
			+ "      \"nodeType\": \"app\",\n" + "      \"id\": \"app\"\n"
			+ "    }";
	private ASTNode program;

	@Before
	public void setUp() {
		program = new ASTNode(initialProgram);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testChangeEmptyExpressionStatement() {
		String updateJSON = "{\n"
				+ "        \"expr\": \"'/0022Hello_World/0021/0022\"\n"
				+ "      }";
		String nodeID = "x0jQd1BtQGFLL1XBIeiT9kmL";
		Update update = new Update(
				ASTNodeManager.getInstance().getNode(nodeID),
				(Map<String, Object>) JSONValue.parse(updateJSON));
		update.apply();
		String afterJSON = program.getJSON();
		assertEquals(
				"{\"isLibrary\":false,\"jsonVersion\":\"v1.0,resolved,short\",\"platform\":\"current\",\"textVersion\":\"v2.2,js,ctx\",\"rootId\":\"ycXVAstFZ325M0PRsuXtUu7F\",\"allowExport\":false,\"id\":\"app\",\"autoColor\":\"#EEDC82\",\"deletedDecls\":[],\"name\":\"edits-test-dumb\",\"autoIcon\":\"Exit\",\"hasUniqueIds\":false,\"decls\":[{\"id\":\"SZwwuN9ffv5TLJuO8buwjifz\",\"inParameters\":[],\"body\":[{\"id\":\"x0jQd1BtQGFLL1XBIeiT9kmL\",\"locals\":[],\"expr\":\"'\\/0022Hello_World\\/0021\\/0022\",\"nodeType\":\"exprStmt\"}],\"isPrivate\":false,\"isOffloaded\":false,\"name\":\"main\",\"isAsync\":false,\"isTest\":false,\"outParameters\":[],\"nodeType\":\"action\"}],\"nodeType\":\"app\",\"comment\":\"\",\"showAd\":false}",
				afterJSON);
	}

	@Test
	public void testChangeEmtpyExpressionStatementAgain() {
		String updateJSON = "{\n"
				+ "        \"expr\": \"'/0022Hello_World/0021/0022 .post_to_wall\"\n"
				+ "      }";
		String nodeID = "x0jQd1BtQGFLL1XBIeiT9kmL";
		Update update = new Update(
				ASTNodeManager.getInstance().getNode(nodeID),
				(Map<String, Object>) JSONValue.parse(updateJSON));
		update.apply();
		String afterJSON = program.getJSON();
		assertEquals(
				"{\"isLibrary\":false,\"jsonVersion\":\"v1.0,resolved,short\",\"platform\":\"current\",\"textVersion\":\"v2.2,js,ctx\",\"rootId\":\"ycXVAstFZ325M0PRsuXtUu7F\",\"allowExport\":false,\"id\":\"app\",\"autoColor\":\"#EEDC82\",\"deletedDecls\":[],\"name\":\"edits-test-dumb\",\"autoIcon\":\"Exit\",\"hasUniqueIds\":false,\"decls\":[{\"id\":\"SZwwuN9ffv5TLJuO8buwjifz\",\"inParameters\":[],\"body\":[{\"id\":\"x0jQd1BtQGFLL1XBIeiT9kmL\",\"locals\":[],\"expr\":\"'\\/0022Hello_World\\/0021\\/0022 .post_to_wall\",\"nodeType\":\"exprStmt\"}],\"isPrivate\":false,\"isOffloaded\":false,\"name\":\"main\",\"isAsync\":false,\"isTest\":false,\"outParameters\":[],\"nodeType\":\"action\"}],\"nodeType\":\"app\",\"comment\":\"\",\"showAd\":false}",
				afterJSON);
	}
}