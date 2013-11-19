package edu.oregonstate.cs519.touchdevelop.ast;

import java.util.HashMap;

public class ASTNodeManager {
	
	private static ASTNodeManager instance = null;
	private HashMap<String, ASTNode> mapOfNodes;

	private ASTNodeManager() {
		mapOfNodes = new HashMap<String, ASTNode>();
	}
	
	public static ASTNodeManager getInstance() {
		if (instance == null)
			instance = new ASTNodeManager();
		
		return instance;
	}
	
	public ASTNode getNode(String id) {
		return mapOfNodes.get(id);
	}
	
	public void addNode(ASTNode node) {
		mapOfNodes.put((String) node.getProperty(ASTNode.ID), node);
	}
}
