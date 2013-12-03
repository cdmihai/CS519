package edu.oregonstate.cs519.touchdevelop.ast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unchecked")
public class ASTNodeTest {

	private String initialProgramWithOneEmptyStatement = "{\n" + "      \"textVersion\": \"v2.2,js,ctx\",\n"
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
	private ASTNode root;
	
	@Before
	public void setUp() {
		root = new ASTNode(initialProgramWithOneEmptyStatement);
	}

	@Test
	public void testEmptyAppCreation() {
		String json = "{\"textVersion\": \"v2.2,js,ctx\",\n"
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
				+ "      \"hasUniqueIds\": false,\n" + "      \"decls\": [],\n"
				+ "      \"deletedDecls\": [],\n"
				+ "      \"nodeType\": \"app\",\n" + "      \"id\": \"app\"}";

		Map<String, Object> map = (Map<String, Object>) JSONValue.parse(json);
		ASTNode astNode = new ASTNode(map);
		String id = (String) astNode.getProperty(ASTNode.ID);
		assertEquals("app", id);
	}

	@Test
	public void testAppWithMainCreation() {
		String json = "{\"textVersion\": \"v2.2,js,ctx\",\n"
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
				+ "      \"hasUniqueIds\": false,\n" + "      \"decls\": [{\n"
				+ "          \"name\": \"main\",\n"
				+ "          \"inParameters\": [],\n"
				+ "          \"outParameters\": [],\n"
				+ "          \"isPrivate\": false,\n"
				+ "          \"isOffloaded\": false,\n"
				+ "          \"isTest\": false,\n"
				+ "          \"isAsync\": false,\n"
				+ "          \"nodeType\": \"action\",\n"
				+ "          \"body\": [],\n"
				+ "          \"id\": \"gCZN28r7NjF4okBgicbUzSTA\"\n"
				+ "        }],\n" + "      \"deletedDecls\": [],\n"
				+ "      \"nodeType\": \"app\",\n" + "      \"id\": \"app\"}";

		Map<String, Object> map = (Map<String, Object>) JSONValue.parse(json);
		ASTNode astNode = new ASTNode(map);
		List<ASTNode> decls = (List<ASTNode>) astNode
				.getProperty(ASTNode.DECLARATIONS);
		assertEquals(1, decls.size());
		assertEquals("gCZN28r7NjF4okBgicbUzSTA",
				decls.get(0).getProperty(ASTNode.ID));
	}

	@Test
	public void testAppWithMainAndBodyCreation() {

		Map<String, Object> map = (Map<String, Object>) JSONValue.parse(initialProgramWithOneEmptyStatement);
		ASTNode astNode = new ASTNode(map);
		List<ASTNode> decls = (List<ASTNode>) astNode
				.getProperty(ASTNode.DECLARATIONS);
		List<ASTNode> body = (List<ASTNode>) decls.get(0).getProperty(
				ASTNode.BODY);
		assertEquals(1, body.size());
		assertEquals("x0jQd1BtQGFLL1XBIeiT9kmL",
				body.get(0).getProperty(ASTNode.ID));
	}

	@Test
	public void testUnpackAndPack() {
		packAndUnpackAndAssert(initialProgramWithOneEmptyStatement);
	}

	@SuppressWarnings("rawtypes")
	public void packAndUnpackAndAssert(String json) {
		Map<String, Object> map = (Map<String, Object>) JSONValue.parse(json);
		ASTNode astNode = new ASTNode(map);
		((ASTNode) ((List) astNode.getProperty(ASTNode.DECLARATIONS)).get(0))
				.getProperty(ASTNode.BODY);
		String outputJSON = astNode.getJSON();
		Map<String, Object> expected = (Map<String, Object>) JSONValue.parse(json);
		Map actual = (Map) JSONValue.parse(outputJSON);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSimplePackAndUnpack() {
		String json = "{\"textVersion\": \"v2.2,js,ctx\",\n"
				+ "      \"decls\": [{\n"
				+ "          \"id\": \"gCZN28r7NjF4okBgicbUzSTA\"\n"
				+ "        }],\n" + "      \"deletedDecls\": [],\n"
				+ "      \"nodeType\": \"app\",\n" + "      \"id\": \"app\"}";
		
		packAndUnpackAndAssert(json);
	}
	
	@Test
	public void testParentNode() {
		assertNull(root.getParent());
		ASTNode expr = ASTNodeManager.getInstance().getNode("x0jQd1BtQGFLL1XBIeiT9kmL");
		assertNotNull(expr.getParent());
		assertEquals(expr.getParent().getProperty(ASTNode.ID),"SZwwuN9ffv5TLJuO8buwjifz");
	}
	
	@Test
	public void testDelete() {
		ASTNodeManager.getInstance().getNode("x0jQd1BtQGFLL1XBIeiT9kmL").delete();
		assertEquals("{\"isLibrary\":false,\"jsonVersion\":\"v1.0,resolved,short\",\"platform\":\"current\",\"textVersion\":\"v2.2,js,ctx\",\"rootId\":\"ycXVAstFZ325M0PRsuXtUu7F\",\"allowExport\":false,\"id\":\"app\",\"autoColor\":\"#EEDC82\",\"deletedDecls\":[],\"name\":\"edits-test-dumb\",\"autoIcon\":\"Exit\",\"hasUniqueIds\":false,\"decls\":[{\"id\":\"SZwwuN9ffv5TLJuO8buwjifz\",\"inParameters\":[],\"body\":[],\"isPrivate\":false,\"isOffloaded\":false,\"name\":\"main\",\"isAsync\":false,\"isTest\":false,\"outParameters\":[],\"nodeType\":\"action\"}],\"nodeType\":\"app\",\"comment\":\"\",\"showAd\":false}",root.getJSON());
		assertNull(ASTNodeManager.getInstance().getNode("x0jQd1BtQGFLL1XBIeiT9kmL"));
	}
	
	@Test
	public void testSimpleMarkupOfNode() throws Exception {
		root.updateProperty(ASTNode.ID, "test1");
		assertTrue(root.isNodeChanged());
	}

	@Test
	public void testSimpleMarkupOfProperty() throws Exception {
		root.updateProperty(ASTNode.ID, "test1");
		assertTrue(root.isPropertyChanged(ASTNode.ID));
		assertFalse(root.isPropertyChanged(ASTNode.DECLARATIONS));
	}
	
	@Test
	public void testSimpleMarkupAndOwnership() throws Exception {
		root.updateProperty(ASTNode.ID, "test1");
		assertEquals(root.getOwner(ASTNode.ID),ASTNode.DEFAULT_OWNER);
	}
	
	@Test
	public void testNoChangeBaseOwnership() {
		assertEquals(root.getOwner(ASTNode.ID),ASTNode.BASE_OWNER);
	}
	
}