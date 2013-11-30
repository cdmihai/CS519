package edu.oregonstate.cs519.touchdevelop.ast;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OperationTest {

	@Test
	public void testCreateAST() {
		String operationJSON = "  {\n"
				+ "    \"time\": 1384813540,\n"
				+ "    \"seqNo\": 1,\n"
				+ "    \"scriptId\": null,\n"
				+ "    \"historyId\": \"2520174872591440045349a9cc8-4836-4543-8bf0-bf7fab9c78e9\",\n"
				+ "    \"updateSize\": 591,\n" + "    \"ast\": {\n"
				+ "      \"textVersion\": \"v2.2,js,ctx\",\n"
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
				+ "              \"id\": \"a9vsqHIOdhPUYO8444rZV07a\",\n"
				+ "              \"locals\": []\n" + "            }\n"
				+ "          ],\n"
				+ "          \"id\": \"fTJdDeXA1eAMLaJKf4SR8Aoi\"\n"
				+ "        }\n" + "      ],\n"
				+ "      \"deletedDecls\": [],\n"
				+ "      \"nodeType\": \"app\",\n" + "      \"id\": \"app\"\n"
				+ "    }\n" + "  }";
		Operation operation = new Operation(operationJSON);
		ASTNode program = operation.apply();
		assertEquals("{\"isLibrary\":false,\"jsonVersion\":\"v1.0,resolved,short\",\"platform\":\"current\",\"textVersion\":\"v2.2,js,ctx\",\"rootId\":\"ycXVAstFZ325M0PRsuXtUu7F\",\"allowExport\":false,\"id\":\"app\",\"autoColor\":\"#EEDC82\",\"deletedDecls\":[],\"name\":\"edits-test-dumb\",\"autoIcon\":\"Exit\",\"hasUniqueIds\":false,\"decls\":[{\"id\":\"fTJdDeXA1eAMLaJKf4SR8Aoi\",\"inParameters\":[],\"body\":[{\"id\":\"a9vsqHIOdhPUYO8444rZV07a\",\"locals\":[],\"expr\":\"\",\"nodeType\":\"exprStmt\"}],\"isPrivate\":false,\"isOffloaded\":false,\"name\":\"main\",\"isAsync\":false,\"isTest\":false,\"outParameters\":[],\"nodeType\":\"action\"}],\"nodeType\":\"app\",\"comment\":\"\",\"showAd\":false}", program.getJSON());
	}
	
	@Test
	public void testApplyOperation() {
		ASTNode initialProgram = new ASTNode("{\"textVersion\": \"v2.2,js,ctx\",\n"
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
				+ "              \"id\": \"a9vsqHIOdhPUYO8444rZV07a\",\n"
				+ "              \"locals\": []\n" + "            }\n"
				+ "          ],\n"
				+ "          \"id\": \"fTJdDeXA1eAMLaJKf4SR8Aoi\"\n"
				+ "        }\n" + "      ],\n"
				+ "      \"deletedDecls\": [],\n"
				+ "      \"nodeType\": \"app\",\n" + "      \"id\": \"app\"\n"
				+ "}");
		String operationJSONString = "{\n" + 
				"    \"time\": 1384813654,\n" + 
				"    \"seqNo\": 2,\n" + 
				"    \"scriptId\": null,\n" + 
				"    \"historyId\": \"252017487145170404008ac4b91-05c6-4524-9212-0fbd950d5bfe\",\n" + 
				"    \"updateSize\": 67,\n" + 
				"    \"updates\": {\n" + 
				"      \"a9vsqHIOdhPUYO8444rZV07a\": {\n" + 
				"        \"expr\": \"'/0022Hello_World/0021/0022\"\n" + 
				"      }\n" + 
				"    }\n" + 
				"  }";
		Operation operation = new Operation(operationJSONString);
		ASTNode program = operation.apply(initialProgram);
		assertEquals("{\"isLibrary\":false,\"jsonVersion\":\"v1.0,resolved,short\",\"platform\":\"current\",\"textVersion\":\"v2.2,js,ctx\",\"rootId\":\"ycXVAstFZ325M0PRsuXtUu7F\",\"allowExport\":false,\"id\":\"app\",\"autoColor\":\"#EEDC82\",\"deletedDecls\":[],\"name\":\"edits-test-dumb\",\"autoIcon\":\"Exit\",\"hasUniqueIds\":false,\"decls\":[{\"id\":\"fTJdDeXA1eAMLaJKf4SR8Aoi\",\"inParameters\":[],\"body\":[{\"id\":\"a9vsqHIOdhPUYO8444rZV07a\",\"locals\":[],\"expr\":\"'\\/0022Hello_World\\/0021\\/0022\",\"nodeType\":\"exprStmt\"}],\"isPrivate\":false,\"isOffloaded\":false,\"name\":\"main\",\"isAsync\":false,\"isTest\":false,\"outParameters\":[],\"nodeType\":\"action\"}],\"nodeType\":\"app\",\"comment\":\"\",\"showAd\":false}", program.getJSON());
	}
}
