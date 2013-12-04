package edu.oregonstate.cs519.touchdevelop.ast;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HistoryTest {
	
	private static final String TEST_USER = "test-user";
	
	String historyJSON = "[\n" + 
			"  {\n" + 
			"    \"time\": 1384813540,\n" + 
			"    \"seqNo\": 1,\n" + 
			"    \"scriptId\": null,\n" + 
			"    \"historyId\": \"2520174872591440045349a9cc8-4836-4543-8bf0-bf7fab9c78e9\",\n" + 
			"    \"updateSize\": 591,\n" + 
			"    \"ast\": {\n" + 
			"      \"textVersion\": \"v2.2,js,ctx\",\n" + 
			"      \"jsonVersion\": \"v1.0,resolved,short\",\n" + 
			"      \"name\": \"edits-test-dumb\",\n" + 
			"      \"comment\": \"\",\n" + 
			"      \"autoIcon\": \"Exit\",\n" + 
			"      \"autoColor\": \"#EEDC82\",\n" + 
			"      \"platform\": \"current\",\n" + 
			"      \"rootId\": \"ycXVAstFZ325M0PRsuXtUu7F\",\n" + 
			"      \"showAd\": false,\n" + 
			"      \"isLibrary\": false,\n" + 
			"      \"allowExport\": false,\n" + 
			"      \"hasUniqueIds\": false,\n" + 
			"      \"decls\": [\n" + 
			"        {\n" + 
			"          \"name\": \"main\",\n" + 
			"          \"inParameters\": [],\n" + 
			"          \"outParameters\": [],\n" + 
			"          \"isPrivate\": false,\n" + 
			"          \"isOffloaded\": false,\n" + 
			"          \"isTest\": false,\n" + 
			"          \"isAsync\": false,\n" + 
			"          \"nodeType\": \"action\",\n" + 
			"          \"body\": [\n" + 
			"            {\n" + 
			"              \"expr\": \"\",\n" + 
			"              \"nodeType\": \"exprStmt\",\n" + 
			"              \"id\": \"JOF2BhU1xtg02wXfidtW0SWv\",\n" + 
			"              \"locals\": []\n" + 
			"            }\n" + 
			"          ],\n" + 
			"          \"id\": \"z2Iug4rSoCRK0hlSjOJqGYCN\"\n" + 
			"        }\n" + 
			"      ],\n" + 
			"      \"deletedDecls\": [],\n" + 
			"      \"nodeType\": \"app\",\n" + 
			"      \"id\": \"app\"\n" + 
			"    }\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"time\": 1384813561,\n" + 
			"    \"seqNo\": 2,\n" + 
			"    \"scriptId\": null,\n" + 
			"    \"historyId\": \"2520174872385050905c7fd9556-ae8f-4804-a38d-1c9d76fe5e57\",\n" + 
			"    \"updateSize\": 2,\n" + 
			"    \"updates\": {}\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"time\": 1384813654,\n" + 
			"    \"seqNo\": 3,\n" + 
			"    \"scriptId\": null,\n" + 
			"    \"historyId\": \"252017487145170404008ac4b91-05c6-4524-9212-0fbd950d5bfe\",\n" + 
			"    \"updateSize\": 67,\n" + 
			"    \"updates\": {\n" + 
			"      \"JOF2BhU1xtg02wXfidtW0SWv\": {\n" + 
			"        \"expr\": \"'/0022Hello_World/0021/0022\"\n" + 
			"      }\n" + 
			"    }\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"time\": 1384814003,\n" + 
			"    \"seqNo\": 4,\n" + 
			"    \"scriptId\": null,\n" + 
			"    \"historyId\": \"2520174867968868511f8fd98e4-85f9-4cc9-83c8-eae848269af9\",\n" + 
			"    \"updateSize\": 81,\n" + 
			"    \"updates\": {\n" + 
			"      \"JOF2BhU1xtg02wXfidtW0SWv\": {\n" + 
			"        \"expr\": \"'/0022Hello_World/0021/0022 .post_to_wall\"\n" + 
			"      }\n" + 
			"    }\n" + 
			"  }\n" + 
			"]";
	private History history;
	
	@Before
	public void setUp() {
		history = new History(historyJSON, TEST_USER);
	}

	@Test
	public void testGetInitialAST() {
		ASTNode program = history.getProgram();
		assertEquals("{\"isLibrary\":false,\"jsonVersion\":\"v1.0,resolved,short\",\"platform\":\"current\",\"textVersion\":\"v2.2,js,ctx\",\"rootId\":\"ycXVAstFZ325M0PRsuXtUu7F\",\"allowExport\":false,\"id\":\"app\",\"autoColor\":\"#EEDC82\",\"deletedDecls\":[],\"name\":\"edits-test-dumb\",\"autoIcon\":\"Exit\",\"hasUniqueIds\":false,\"decls\":[{\"id\":\"z2Iug4rSoCRK0hlSjOJqGYCN\",\"inParameters\":[],\"body\":[{\"id\":\"JOF2BhU1xtg02wXfidtW0SWv\",\"locals\":[],\"expr\":\"\",\"nodeType\":\"exprStmt\"}],\"isPrivate\":false,\"isOffloaded\":false,\"name\":\"main\",\"isAsync\":false,\"isTest\":false,\"outParameters\":[],\"nodeType\":\"action\"}],\"nodeType\":\"app\",\"comment\":\"\",\"showAd\":false}",program.getJSON());
	}
	
	@Test
	public void testApplyOperations() {
		ASTNode program = history.apply();
		assertEquals("{\"isLibrary\":false,\"jsonVersion\":\"v1.0,resolved,short\",\"platform\":\"current\",\"textVersion\":\"v2.2,js,ctx\",\"rootId\":\"ycXVAstFZ325M0PRsuXtUu7F\",\"allowExport\":false,\"id\":\"app\",\"autoColor\":\"#EEDC82\",\"deletedDecls\":[],\"name\":\"edits-test-dumb\",\"autoIcon\":\"Exit\",\"hasUniqueIds\":false,\"decls\":[{\"id\":\"z2Iug4rSoCRK0hlSjOJqGYCN\",\"inParameters\":[],\"body\":[{\"id\":\"JOF2BhU1xtg02wXfidtW0SWv\",\"locals\":[],\"expr\":\"'\\/0022Hello_World\\/0021\\/0022 .post_to_wall\",\"nodeType\":\"exprStmt\"}],\"isPrivate\":false,\"isOffloaded\":false,\"name\":\"main\",\"isAsync\":false,\"isTest\":false,\"outParameters\":[],\"nodeType\":\"action\"}],\"nodeType\":\"app\",\"comment\":\"\",\"showAd\":false}", program.getJSON());
	}
	
	@Test
	public void testApplyOperationsWithOwnerInfo() {
		ASTNode program = history.apply();
		ASTNode changedNode = ASTNodeManager.getInstance().getNode("JOF2BhU1xtg02wXfidtW0SWv");
		assertEquals(TEST_USER,changedNode.getOwner(ASTNode.EXPRESSION));
		assertEquals(ASTNode.BASE_OWNER,changedNode.getOwner(ASTNode.ID));
	}
	
	@Test
	public void testApplyHistoryWithConflict() {
		ASTNode program = history.apply();
		assertEquals(0,history.getConflicts().size());
		history = new History(program,"[\n" + 
				"  {\n" + 
				"    \"time\": 1384813540,\n" + 
				"    \"seqNo\": 1,\n" + 
				"    \"scriptId\": null,\n" + 
				"    \"historyId\": \"2520174872591440045349a9cc8-4836-4543-8bf0-bf7fab9c78e9\",\n" + 
				"    \"updateSize\": 591,\n" + 
				"    \"ast\": {\n" + 
				"      \"textVersion\": \"v2.2,js,ctx\",\n" + 
				"      \"jsonVersion\": \"v1.0,resolved,short\",\n" + 
				"      \"name\": \"edits-test-dumb\",\n" + 
				"      \"comment\": \"\",\n" + 
				"      \"autoIcon\": \"Exit\",\n" + 
				"      \"autoColor\": \"#EEDC82\",\n" + 
				"      \"platform\": \"current\",\n" + 
				"      \"rootId\": \"ycXVAstFZ325M0PRsuXtUu7F\",\n" + 
				"      \"showAd\": false,\n" + 
				"      \"isLibrary\": false,\n" + 
				"      \"allowExport\": false,\n" + 
				"      \"hasUniqueIds\": false,\n" + 
				"      \"decls\": [\n" + 
				"        {\n" + 
				"          \"name\": \"main\",\n" + 
				"          \"inParameters\": [],\n" + 
				"          \"outParameters\": [],\n" + 
				"          \"isPrivate\": false,\n" + 
				"          \"isOffloaded\": false,\n" + 
				"          \"isTest\": false,\n" + 
				"          \"isAsync\": false,\n" + 
				"          \"nodeType\": \"action\",\n" + 
				"          \"body\": [\n" + 
				"            {\n" + 
				"              \"expr\": \"\",\n" + 
				"              \"nodeType\": \"exprStmt\",\n" + 
				"              \"id\": \"JOF2BhU1xtg02wXfidtW0SWv\",\n" + 
				"              \"locals\": []\n" + 
				"            }\n" + 
				"          ],\n" + 
				"          \"id\": \"z2Iug4rSoCRK0hlSjOJqGYCN\"\n" + 
				"        }\n" + 
				"      ],\n" + 
				"      \"deletedDecls\": [],\n" + 
				"      \"nodeType\": \"app\",\n" + 
				"      \"id\": \"app\"\n" + 
				"    }\n" + 
				"  },\n" + 
				"  {\n" + 
				"    \"time\": 1384814003,\n" + 
				"    \"seqNo\": 2,\n" + 
				"    \"scriptId\": null,\n" + 
				"    \"historyId\": \"2520174867968868511f8fd98e4-85f9-4cc9-83c8-eae848269af9\",\n" + 
				"    \"updateSize\": 81,\n" + 
				"    \"updates\": {\n" + 
				"      \"JOF2BhU1xtg02wXfidtW0SWv\": {\n" + 
				"        \"expr\": \"'/0022Hello_Worldbla/0021/0022 .post_to_wall\"\n" + 
				"      }\n" + 
				"    }\n" + 
				"  }\n" + 
				"]","notthesameguy");
		program = history.apply();
		assertEquals(1,history.getConflicts().size());
	}

}
