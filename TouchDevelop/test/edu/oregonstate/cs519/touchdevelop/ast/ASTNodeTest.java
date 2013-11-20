package edu.oregonstate.cs519.touchdevelop.ast;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;
import org.junit.Test;

public class ASTNodeTest {

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
		List<ASTNode> decls = (List<ASTNode>) astNode.getProperty(ASTNode.DECLARATIONS);
		assertEquals(1, decls.size());
		assertEquals("gCZN28r7NjF4okBgicbUzSTA",decls.get(0).getProperty(ASTNode.ID));
	}
	
	@Test
	public void testAppWithMainAndBodyCreation() {
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
				+ "          \"body\": [\n" + "            {\n"
				+ "              \"expr\": \"\",\n"
				+ "              \"nodeType\": \"exprStmt\",\n"
				+ "              \"id\": \"xXuJlIY4hsjAK1ZU1ew8yqJt\",\n"
				+ "              \"locals\": []\n" + "            }\n"
				+ "          ],\n"
				+ "          \"id\": \"gCZN28r7NjF4okBgicbUzSTA\"\n"
				+ "        }],\n" + "      \"deletedDecls\": [],\n"
				+ "      \"nodeType\": \"app\",\n" + "      \"id\": \"app\"}";

		Map<String, Object> map = (Map<String, Object>) JSONValue.parse(json);
		ASTNode astNode = new ASTNode(map);
		List<ASTNode> decls = (List<ASTNode>) astNode.getProperty(ASTNode.DECLARATIONS);
		List<ASTNode> body = (List<ASTNode>) decls.get(0).getProperty(ASTNode.BODY);
		assertEquals(1,body.size());
		assertEquals("xXuJlIY4hsjAK1ZU1ew8yqJt", body.get(0).getProperty(ASTNode.ID));
	}


}
