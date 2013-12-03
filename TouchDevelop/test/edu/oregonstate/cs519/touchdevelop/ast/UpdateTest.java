package edu.oregonstate.cs519.touchdevelop.ast;

import static org.junit.Assert.*;

import java.util.Map;

import org.json.simple.JSONValue;
import org.junit.Before;
import org.junit.Test;

public class UpdateTest {

	private static final String TEST_USER = "test-user";
	private String initialProgram;
	private ASTNode program;

	@Before
	public void setUp() {
		initialProgram = "{\n" + "      \"textVersion\": \"v2.2,js,ctx\",\n"
				+ "      \"jsonVersion\": \"v1.0,resolved,short\",\n"
				+ "      \"name\": \"edits-test-dumb\",\n"
				+ "      \"comment\": \"\",\n"
				+ "      \"autoIcon\": \"Exit\",\n"
				+ "      \"autoColor\": \"#EEDC82\",\n"
				+ "      \"platform\": \"current\",\n"
				+ "      \"rootId\": \"ycXVAstFZ325M0PRsuXtUu7F\",\n"
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
				+ "              \"expr\": \"\",\n"
				+ "              \"nodeType\": \"exprStmt\",\n"
				+ "              \"id\": \"x0jQd1BtQGFLL1XBIeiT9kmL\",\n"
				+ "              \"locals\": []\n" + "            }\n"
				+ "          ],\n"
				+ "          \"id\": \"SZwwuN9ffv5TLJuO8buwjifz\"\n"
				+ "        }\n" + "      ],\n"
				+ "      \"deletedDecls\": [],\n"
				+ "      \"nodeType\": \"app\",\n" + "      \"id\": \"app\"\n"
				+ "    }";
		program = new ASTNode(initialProgram);
	}

	@Test
	public void testChangeEmptyExpressionStatement() {
		String updateJSON = "{\n\"expr\": \"'/0022Hello_World/0021/0022\"\n}";
		String nodeID = "x0jQd1BtQGFLL1XBIeiT9kmL";
		String afterJSON = applyUpdateToProgram(updateJSON, nodeID, TEST_USER);
		assertEquals(
				"{\"isLibrary\":false,\"jsonVersion\":\"v1.0,resolved,short\",\"platform\":\"current\",\"textVersion\":\"v2.2,js,ctx\",\"rootId\":\"ycXVAstFZ325M0PRsuXtUu7F\",\"allowExport\":false,\"id\":\"app\",\"autoColor\":\"#EEDC82\",\"deletedDecls\":[],\"name\":\"edits-test-dumb\",\"autoIcon\":\"Exit\",\"hasUniqueIds\":false,\"decls\":[{\"id\":\"SZwwuN9ffv5TLJuO8buwjifz\",\"inParameters\":[],\"body\":[{\"id\":\"x0jQd1BtQGFLL1XBIeiT9kmL\",\"locals\":[],\"expr\":\"'\\/0022Hello_World\\/0021\\/0022\",\"nodeType\":\"exprStmt\"}],\"isPrivate\":false,\"isOffloaded\":false,\"name\":\"main\",\"isAsync\":false,\"isTest\":false,\"outParameters\":[],\"nodeType\":\"action\"}],\"nodeType\":\"app\",\"comment\":\"\",\"showAd\":false}",
				afterJSON);
	}

	@Test
	public void testChangeEmtpyExpressionStatementAgain() {
		String updateJSON = "{\n\"expr\": \"'/0022Hello_World/0021/0022 .post_to_wall\"\n}";
		String nodeID = "x0jQd1BtQGFLL1XBIeiT9kmL";
		String afterJSON = applyUpdateToProgram(updateJSON, nodeID, TEST_USER);
		assertEquals(
				"{\"isLibrary\":false,\"jsonVersion\":\"v1.0,resolved,short\",\"platform\":\"current\",\"textVersion\":\"v2.2,js,ctx\",\"rootId\":\"ycXVAstFZ325M0PRsuXtUu7F\",\"allowExport\":false,\"id\":\"app\",\"autoColor\":\"#EEDC82\",\"deletedDecls\":[],\"name\":\"edits-test-dumb\",\"autoIcon\":\"Exit\",\"hasUniqueIds\":false,\"decls\":[{\"id\":\"SZwwuN9ffv5TLJuO8buwjifz\",\"inParameters\":[],\"body\":[{\"id\":\"x0jQd1BtQGFLL1XBIeiT9kmL\",\"locals\":[],\"expr\":\"'\\/0022Hello_World\\/0021\\/0022 .post_to_wall\",\"nodeType\":\"exprStmt\"}],\"isPrivate\":false,\"isOffloaded\":false,\"name\":\"main\",\"isAsync\":false,\"isTest\":false,\"outParameters\":[],\"nodeType\":\"action\"}],\"nodeType\":\"app\",\"comment\":\"\",\"showAd\":false}",
				afterJSON);
	}

	@SuppressWarnings("unchecked")
	private String applyUpdateToProgram(String updateJSON, String nodeID) {
		return applyUpdateToProgram(updateJSON, nodeID, TEST_USER);
	}

	@SuppressWarnings("unchecked")
	private String applyUpdateToProgram(String updateJSON, String nodeID, String owner) {
		Update update = new Update(nodeID,
				(Map<String, Object>) JSONValue.parse(updateJSON), owner);
		update.apply();
		String afterJSON = program.getJSON();
		return afterJSON;
	}

	@Test
	public void testAddGlobalVariable() {
		String updateJSON = "{\n" + "        \"name\": \"s\",\n"
				+ "        \"comment\": \"\",\n"
				+ "        \"type\": \"String\",\n"
				+ "        \"isReadonly\": false,\n"
				+ "        \"isTransient\": true,\n"
				+ "        \"nodeType\": \"data\"\n" + "      }";
		String nodeID = "wAHj4rpF7s1v6qxVEDsWsDWL";
		String after = applyUpdateToProgram(updateJSON, nodeID, TEST_USER);
		assertEquals(
				"{\"isLibrary\":false,\"jsonVersion\":\"v1.0,resolved,short\",\"platform\":\"current\",\"textVersion\":\"v2.2,js,ctx\",\"rootId\":\"ycXVAstFZ325M0PRsuXtUu7F\",\"allowExport\":false,\"id\":\"app\",\"autoColor\":\"#EEDC82\",\"deletedDecls\":[],\"name\":\"edits-test-dumb\",\"autoIcon\":\"Exit\",\"hasUniqueIds\":false,\"decls\":[{\"id\":\"SZwwuN9ffv5TLJuO8buwjifz\",\"inParameters\":[],\"body\":[{\"id\":\"x0jQd1BtQGFLL1XBIeiT9kmL\",\"locals\":[],\"expr\":\"\",\"nodeType\":\"exprStmt\"}],\"isPrivate\":false,\"isOffloaded\":false,\"name\":\"main\",\"isAsync\":false,\"isTest\":false,\"outParameters\":[],\"nodeType\":\"action\"}],\"nodeType\":\"app\",\"comment\":\"\",\"showAd\":false}",
				after);
		after = applyUpdateToProgram("{\n" + "        \"decls\": [\n"
				+ "          \"SZwwuN9ffv5TLJuO8buwjifz\",\n"
				+ "          \"wAHj4rpF7s1v6qxVEDsWsDWL\"\n" + "        ]\n"
				+ "      }", "app", TEST_USER);
		assertEquals(
				"{\"isLibrary\":false,\"jsonVersion\":\"v1.0,resolved,short\",\"platform\":\"current\",\"textVersion\":\"v2.2,js,ctx\",\"rootId\":\"ycXVAstFZ325M0PRsuXtUu7F\",\"allowExport\":false,\"id\":\"app\",\"autoColor\":\"#EEDC82\",\"deletedDecls\":[],\"name\":\"edits-test-dumb\",\"autoIcon\":\"Exit\",\"hasUniqueIds\":false,\"decls\":[{\"id\":\"SZwwuN9ffv5TLJuO8buwjifz\",\"inParameters\":[],\"body\":[{\"id\":\"x0jQd1BtQGFLL1XBIeiT9kmL\",\"locals\":[],\"expr\":\"\",\"nodeType\":\"exprStmt\"}],\"isPrivate\":false,\"isOffloaded\":false,\"name\":\"main\",\"isAsync\":false,\"isTest\":false,\"outParameters\":[],\"nodeType\":\"action\"},{\"id\":\"wAHj4rpF7s1v6qxVEDsWsDWL\",\"isTransient\":true,\"name\":\"s\",\"isReadonly\":false,\"nodeType\":\"data\",\"type\":\"String\",\"comment\":\"\"}],\"nodeType\":\"app\",\"comment\":\"\",\"showAd\":false}",
				after);
	}

	@Test
	public void testUseGlobalVariable() {
		initialProgram = "{\n"
				+ "  \"isLibrary\": false,\n"
				+ "  \"jsonVersion\": \"v1.0,resolved,short\",\n"
				+ "  \"platform\": \"current\",\n"
				+ "  \"textVersion\": \"v2.2,js,ctx\",\n"
				+ "  \"rootId\": \"ycXVAstFZ325M0PRsuXtUu7F\",\n"
				+ "  \"allowExport\": false,\n"
				+ "  \"id\": \"app\",\n"
				+ "  \"autoColor\": \"#EEDC82\",\n"
				+ "  \"deletedDecls\": [\n"
				+ "    \n"
				+ "  ],\n"
				+ "  \"name\": \"edits-test-dumb\",\n"
				+ "  \"autoIcon\": \"Exit\",\n"
				+ "  \"hasUniqueIds\": false,\n"
				+ "  \"decls\": [\n"
				+ "    {\n"
				+ "      \"id\": \"SZwwuN9ffv5TLJuO8buwjifz\",\n"
				+ "      \"inParameters\": [\n"
				+ "        \n"
				+ "      ],\n"
				+ "      \"body\": [\n"
				+ "        {\n"
				+ "          \"id\": \"x0jQd1BtQGFLL1XBIeiT9kmL\",\n"
				+ "          \"locals\": [\n"
				+ "            \n"
				+ "          ],\n"
				+ "          \"expr\": \"'/0022Hello_World/0021/0022 .post_to_wall\",\n"
				+ "          \"nodeType\": \"exprStmt\"\n" + "        }\n"
				+ "      ],\n" + "      \"isPrivate\": false,\n"
				+ "      \"isOffloaded\": false,\n"
				+ "      \"name\": \"main\",\n" + "      \"isAsync\": false,\n"
				+ "      \"isTest\": false,\n" + "      \"outParameters\": [\n"
				+ "        \n" + "      ],\n"
				+ "      \"nodeType\": \"action\"\n" + "    },\n" + "    {\n"
				+ "      \"id\": \"wAHj4rpF7s1v6qxVEDsWsDWL\",\n"
				+ "      \"name\": \"s\",\n" + "      \"isTransient\": true,\n"
				+ "      \"isReadonly\": false,\n"
				+ "      \"comment\": \"\",\n"
				+ "      \"type\": \"String\",\n"
				+ "      \"nodeType\": \"data\"\n" + "    }\n" + "  ],\n"
				+ "  \"nodeType\": \"app\",\n" + "  \"comment\": \"\",\n"
				+ "  \"showAd\": false\n" + "}";
		program = new ASTNode(initialProgram);
		String after = applyUpdateToProgram(
				"{\n"
						+ "        \"expr\": \":data #wAHj4rpF7s1v6qxVEDsWsDWL ,:= 'How/0027s_it_going/003f\"\n,\n"
						+ "        \"nodeType\": \"exprStmt\",\n"
						+ "        \"locals\": []" + "}",
				"w7Xl21sA24EA0Lpp43qMgHGH", TEST_USER);
		after = applyUpdateToProgram("{\n" + "        \"body\": [\n"
				+ "          \"x0jQd1BtQGFLL1XBIeiT9kmL\",\n"
				+ "          \"w7Xl21sA24EA0Lpp43qMgHGH\"\n" + "        ]\n"
				+ "      }", "SZwwuN9ffv5TLJuO8buwjifz", TEST_USER);
		assertEquals("{\"isLibrary\":false,\"jsonVersion\":\"v1.0,resolved,short\",\"platform\":\"current\",\"textVersion\":\"v2.2,js,ctx\",\"rootId\":\"ycXVAstFZ325M0PRsuXtUu7F\",\"allowExport\":false,\"id\":\"app\",\"autoColor\":\"#EEDC82\",\"deletedDecls\":[],\"name\":\"edits-test-dumb\",\"autoIcon\":\"Exit\",\"hasUniqueIds\":false,\"decls\":[{\"inParameters\":[],\"id\":\"SZwwuN9ffv5TLJuO8buwjifz\",\"body\":[{\"id\":\"x0jQd1BtQGFLL1XBIeiT9kmL\",\"locals\":[],\"expr\":\"'\\/0022Hello_World\\/0021\\/0022 .post_to_wall\",\"nodeType\":\"exprStmt\"},{\"id\":\"w7Xl21sA24EA0Lpp43qMgHGH\",\"locals\":[],\"expr\":\":data #wAHj4rpF7s1v6qxVEDsWsDWL ,:= 'How\\/0027s_it_going\\/003f\",\"nodeType\":\"exprStmt\"}],\"isPrivate\":false,\"name\":\"main\",\"isOffloaded\":false,\"isAsync\":false,\"isTest\":false,\"outParameters\":[],\"nodeType\":\"action\"},{\"id\":\"wAHj4rpF7s1v6qxVEDsWsDWL\",\"isTransient\":true,\"name\":\"s\",\"isReadonly\":false,\"nodeType\":\"data\",\"type\":\"String\",\"comment\":\"\"}],\"comment\":\"\",\"nodeType\":\"app\",\"showAd\":false}", after);
	}
	
	@Test
	public void testDeleteNode() {
		String after = applyUpdateToProgram("null","x0jQd1BtQGFLL1XBIeiT9kmL", TEST_USER);
		assertEquals("{\"isLibrary\":false,\"jsonVersion\":\"v1.0,resolved,short\",\"platform\":\"current\",\"textVersion\":\"v2.2,js,ctx\",\"rootId\":\"ycXVAstFZ325M0PRsuXtUu7F\",\"allowExport\":false,\"id\":\"app\",\"autoColor\":\"#EEDC82\",\"deletedDecls\":[],\"name\":\"edits-test-dumb\",\"autoIcon\":\"Exit\",\"hasUniqueIds\":false,\"decls\":[{\"id\":\"SZwwuN9ffv5TLJuO8buwjifz\",\"inParameters\":[],\"body\":[],\"isPrivate\":false,\"isOffloaded\":false,\"name\":\"main\",\"isAsync\":false,\"isTest\":false,\"outParameters\":[],\"nodeType\":\"action\"}],\"nodeType\":\"app\",\"comment\":\"\",\"showAd\":false}",after);
	}
	
	@Test
	public void testChangeNodeWithChangeOwner() {
		String after = applyUpdateToProgram("{\n\"expr\": \"'/0022Hello_World/0021/0022\"\n}","x0jQd1BtQGFLL1XBIeiT9kmL", TEST_USER);
		ASTNode nodeChanged = ASTNodeManager.getInstance().getNode("x0jQd1BtQGFLL1XBIeiT9kmL");
		assertEquals(TEST_USER, nodeChanged.getOwner(ASTNode.EXPRESSION));
	}
	
	@Test
	public void testAddNodeWithChangeOwner() {
		String after = applyUpdateToProgram("{\n" + "        \"name\": \"s\",\n"
				+ "        \"comment\": \"\",\n"
				+ "        \"type\": \"String\",\n"
				+ "        \"isReadonly\": false,\n"
				+ "        \"isTransient\": true,\n"
				+ "        \"nodeType\": \"data\"\n" + "      }","wAHj4rpF7s1v6qxVEDsWsDWL", "Somebody else");
		ASTNode node = ASTNodeManager.getInstance().getNode("wAHj4rpF7s1v6qxVEDsWsDWL");
		assertEquals("Somebody else",node.getOwner("name"));
	}
	
	@Test
	public void testNonConflicting() {
		String anotherUser = "Somebody else";
		
		applyUpdateToProgram("{\n\"expr\": \"'/0022Hello_World/0021/0022\"\n}","x0jQd1BtQGFLL1XBIeiT9kmL", TEST_USER);
		applyUpdateToProgram("{\n" + "        \"name\": \"s\",\n"
				+ "        \"comment\": \"\",\n"
				+ "        \"type\": \"String\",\n"
				+ "        \"isReadonly\": false,\n"
				+ "        \"isTransient\": true,\n"
				+ "        \"nodeType\": \"data\"\n" + "      }","wAHj4rpF7s1v6qxVEDsWsDWL", anotherUser);
		applyUpdateToProgram("{\n" + "        \"decls\": [\n"
				+ "          \"SZwwuN9ffv5TLJuO8buwjifz\",\n"
				+ "          \"wAHj4rpF7s1v6qxVEDsWsDWL\"\n" + "        ]\n"
				+ "      }","app",anotherUser);
		
		ASTNode app = ASTNodeManager.getInstance().getNode("app");
		assertEquals(anotherUser,app.getOwner(ASTNode.DECLARATIONS));
		ASTNode globalVariable = ASTNodeManager.getInstance().getNode("wAHj4rpF7s1v6qxVEDsWsDWL");
		assertEquals(anotherUser,globalVariable.getOwner("name"));
	}
}
