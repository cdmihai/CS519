package edu.oregonstate.cs519.touchdevelop.ast;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ASTNodeManagerTest {

	@Test
	public void testAddNode() {
		ASTNodeManager nodeManager = ASTNodeManager.getInstance();
		ASTNode node = new ASTNode("{\"id\": \"bla\",\"test\": \"123\"}");
		ASTNode theSameNode = nodeManager.getNode("bla");
		assertNotNull(theSameNode);
		assertEquals("123",theSameNode.getProperty("test"));
	}
}
